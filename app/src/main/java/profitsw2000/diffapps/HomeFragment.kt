package profitsw2000.diffapps

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import profitsw2000.diffapps.adapters.ClassesAdapter
import profitsw2000.diffapps.adapters.HomeworkAdapter
import profitsw2000.diffapps.data.lessonList
import profitsw2000.diffapps.data.taskList
import profitsw2000.diffapps.databinding.FragmentHomeBinding
import profitsw2000.diffapps.model.Lesson
import profitsw2000.diffapps.utils.RemainingTimeCalculator
import java.util.Calendar


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val classesAdapter = ClassesAdapter(lessonList)
    private val homeworkAdapter = HomeworkAdapter(taskList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home_fragment,menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        with(binding) {
            classesListRecyclerView.adapter = classesAdapter
            classesListRecyclerView.smoothScrollToPosition(getCurrentLessonNumber(lessonList))
            homeworkListRecyclerView.adapter = homeworkAdapter
            numberOfClassesTodayTitleTextView.text =
                getString(R.string.number_of_classes_today_text, lessonList.size.toString())
        }
        setTimeBeforeExam()
    }

    private fun setTimeBeforeExam() {
        val remainingTimeCalculator = RemainingTimeCalculator()
        val timeBeforeExam = remainingTimeCalculator
            .getTimeBeforeExamStringList(getString(R.string.exam_date_string),getString(R.string.exam_date_format))

        binding.countDownTimerLayout.daysDecDigitTextView.text = timeBeforeExam[0]
        binding.countDownTimerLayout.daysDigitTextView.text = timeBeforeExam[1]
        binding.countDownTimerLayout.hoursDecDigitTextView.text = timeBeforeExam[2]
        binding.countDownTimerLayout.hoursDigitTextView.text = timeBeforeExam[3]
        binding.countDownTimerLayout.minutesDecDigitTextView.text = timeBeforeExam[4]
        binding.countDownTimerLayout.minutesDigitTextView.text = timeBeforeExam[5]
    }

    private fun getCurrentLessonNumber(lessonList: List<Lesson>): Int {
        var currentLessonNumber = 0
        val calendar = Calendar.getInstance()

        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        val currentTimeInMinutes = getDayTimeInMinutes(currentHour, currentMinute)

        for ((index, lesson) in lessonList.withIndex()) {
            val lessonEndInMinutes = getDayTimeInMinutes(lesson.endHour, lesson.endMinute)
            val previousLessonEndIMinutes = if (index > 0)
                getDayTimeInMinutes(lessonList[index - 1].endHour, lessonList[index - 1].endMinute)
                else 0

            if (currentTimeInMinutes in (previousLessonEndIMinutes + 1) until lessonEndInMinutes) {
                currentLessonNumber = index
            }
        }
        return currentLessonNumber
    }

    private fun getDayTimeInMinutes(hour: Int, minute: Int) : Int {
        return  hour*60 + minute
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
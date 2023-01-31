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
        binding.classesListRecyclerView.adapter = classesAdapter
        binding.classesListRecyclerView.smoothScrollToPosition(getCurrentLessonNumber(lessonList))
        binding.homeworkListRecyclerView.adapter = homeworkAdapter
        setTimeBeforeExam()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home_fragment,menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTimeBeforeExam() {
        val remainingTimeCalculator = RemainingTimeCalculator()
        val timeBeforeExam = remainingTimeCalculator
            .getTimeBeforeExamStringList("2023-02-10 11-00","yyyy-MM-dd HH-mm")

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

        Log.d("VVV", currentHour.toString())
        Log.d("VVV", currentMinute.toString())
        Log.d("VVV", currentTimeInMinutes.toString())

        for ((index, lesson) in lessonList.withIndex()) {
            val lessonBeginInMinutes = getDayTimeInMinutes(lesson.startHour, lesson.startMinute)
            val lessonEndInMinutes = getDayTimeInMinutes(lesson.endHour, lesson.endMinute)

            if (currentTimeInMinutes in (lessonBeginInMinutes + 1) until lessonEndInMinutes) {
                currentLessonNumber = index
            }
        }
        Log.d("VVV", currentLessonNumber.toString())
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
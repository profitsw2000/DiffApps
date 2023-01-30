package profitsw2000.diffapps

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import profitsw2000.diffapps.adapters.ClassesAdapter
import profitsw2000.diffapps.adapters.HomeworkAdapter
import profitsw2000.diffapps.data.lessonList
import profitsw2000.diffapps.data.taskList
import profitsw2000.diffapps.databinding.FragmentHomeBinding
import profitsw2000.diffapps.utils.RemainingTimeCalculator
import java.text.SimpleDateFormat
import java.util.*


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
        binding.homeworkListRecyclerView.adapter = homeworkAdapter
        setTimeBeforeExam()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home_fragment,menu)
    }
/*    private fun getScheduleForWeekDay() : List<Lesson> {
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_WEEK)

        return when (day) {
            Calendar.SUNDAY ->
            Calendar.MONDAY -> {}
            Calendar.TUESDAY -> {}
            Calendar.WEDNESDAY -> {}
            Calendar.THURSDAY -> {}
            Calendar.FRIDAY -> {}
            Calendar.SATURDAY -> {}
            else ->
        }
    }*/

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

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
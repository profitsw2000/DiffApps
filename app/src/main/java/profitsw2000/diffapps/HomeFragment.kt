package profitsw2000.diffapps

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import profitsw2000.diffapps.adapters.ClassesAdapter
import profitsw2000.diffapps.adapters.HomeworkAdapter
import profitsw2000.diffapps.data.lessonList
import profitsw2000.diffapps.data.taskList
import profitsw2000.diffapps.databinding.FragmentHomeBinding
import profitsw2000.diffapps.model.Lesson
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
        activity?.actionBar?.setTitle("sdsa")
        binding.classesListRecyclerView.adapter = classesAdapter
        binding.homeworkListRecyclerView.adapter = homeworkAdapter
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

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
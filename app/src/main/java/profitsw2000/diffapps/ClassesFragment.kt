package profitsw2000.diffapps

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import profitsw2000.diffapps.adapters.ClassesAdapter
import profitsw2000.diffapps.adapters.ClassesBigAdapter
import profitsw2000.diffapps.data.lessonList
import profitsw2000.diffapps.databinding.FragmentClassesBinding
import profitsw2000.diffapps.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class ClassesFragment : Fragment() {

    private var _binding: FragmentClassesBinding? = null
    private val binding get() = _binding!!
    private val classesBigAdapter = ClassesBigAdapter(lessonList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentClassesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.classesFragmentRecyclerView.adapter = classesBigAdapter
        binding.currentDateTextView.text = getCurrentDateString()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_classes_fragment,menu)
    }

    companion object {
        @JvmStatic
        fun newInstance() =ClassesFragment()
    }

    private fun getCurrentDateString(): String {
        val sdf = SimpleDateFormat("d MMM")
        val currentDate = sdf.format(Date())
        return "Today, $currentDate"
    }
}
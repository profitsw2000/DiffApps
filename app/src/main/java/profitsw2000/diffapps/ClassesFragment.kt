package profitsw2000.diffapps

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import profitsw2000.diffapps.adapters.ClassesBigAdapter
import profitsw2000.diffapps.data.lessonList
import profitsw2000.diffapps.databinding.FragmentClassesBinding
import java.text.SimpleDateFormat
import java.util.*


class ClassesFragment : Fragment() {

    private var _binding: FragmentClassesBinding? = null
    private val binding get() = _binding!!
    private val classesBigAdapter = ClassesBigAdapter(lessonList, object : OnItemClickListener {
        override fun onItemClick() {
            launchSkype()
        }
    })

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

    private fun launchSkype() {
        val appName = getString(R.string.skype_app_name)
        val packageName = getString(R.string.skype_package_name)

        if (isAppInstalled(packageName)) {
            if (isAppEnabled(packageName)) context?.startActivity(context?.packageManager?.getLaunchIntentForPackage(packageName))
            else Toast.makeText(context, getString(R.string.skype_not_enabled_text, appName), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, getString(R.string.skype_not_installed_text, appName), Toast.LENGTH_SHORT).show()
        }
    }

    private fun isAppInstalled(packageName: String): Boolean {
        val pm = context?.packageManager
        try {
            pm?.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            return true
        } catch (ignored: PackageManager.NameNotFoundException) {
        }
        return false
    }

    private fun isAppEnabled(packageName: String): Boolean {
        var appStatus = false
        try {
            val ai = context?.packageManager?.getApplicationInfo(packageName, 0)
            if (ai != null) {
                appStatus = ai.enabled
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return appStatus
    }

    private fun getCurrentDateString(): String {
        val sdf = SimpleDateFormat("d MMM")
        val currentDate = sdf.format(Date())
        return "Today, $currentDate"
    }

    companion object {
        @JvmStatic
        fun newInstance() =ClassesFragment()
    }
}
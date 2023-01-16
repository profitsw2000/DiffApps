package profitsw2000.diffapps.presentation.fragments.map.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import profitsw2000.diffapps.R


class MarkerListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marker_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MarkerListFragment()
    }
}
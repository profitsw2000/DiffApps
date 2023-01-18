package profitsw2000.diffapps.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.databinding.FragmentMarkerListBinding
import profitsw2000.diffapps.presentation.view.adapters.MarkerListAdapter
import profitsw2000.diffapps.presentation.viewmodel.MapViewModel


class MarkerListFragment : Fragment() {

    private var _binding: FragmentMarkerListBinding? = null
    private val binding get() = _binding!!
    private val mapViewModel: MapViewModel by viewModel()
    private var adapter = MarkerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarkerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapViewModel.getMarkerList().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        binding.markerListRecyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = MarkerListFragment()
    }
}
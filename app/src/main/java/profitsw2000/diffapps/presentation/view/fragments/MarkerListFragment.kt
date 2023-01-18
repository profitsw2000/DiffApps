package profitsw2000.diffapps.presentation.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.FragmentMarkerListBinding
import profitsw2000.diffapps.presentation.view.adapters.MarkerListAdapter
import profitsw2000.diffapps.presentation.view.adapters.OnItemClickListener
import profitsw2000.diffapps.presentation.viewmodel.MapViewModel
import java.text.FieldPosition


class MarkerListFragment : Fragment() {

    private var _binding: FragmentMarkerListBinding? = null
    private val binding get() = _binding!!
    private val mapViewModel: MapViewModel by viewModel()
    private var adapter: MarkerListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = MarkerListAdapter(object : OnItemClickListener{
            override fun onDeleteClick(position: Int) {
                mapViewModel.deleteMarker(position)
            }

            override fun onEditClick(position: Int) {
                showEditDialog(position)
            }
        })
    }

    private fun showEditDialog(position: Int) {
        val view: View = layoutInflater.inflate(R.layout.edit_marker_layout, null)
        requireActivity().let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.edit_marker_text))
                .setPositiveButton("OK")  { _, _ ->
                    mapViewModel.editMarkerTitle(position,
                        view.findViewById<EditText>(R.id.new_marker_title_edit_text).text.toString())
                }
                .setNegativeButton(getString(R.string.close_dialog_button_text)) { dialog, _ -> dialog.dismiss() }
                .setView(view)
                .create()
                .show()
        }
    }

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
            adapter?.setData(it)
        }
        binding.markerListRecyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = MarkerListFragment()
    }
}
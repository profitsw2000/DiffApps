package profitsw2000.diffapps.presentation.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.FragmentMainBinding
import profitsw2000.diffapps.model.AppState
import profitsw2000.diffapps.presentation.view.adapters.FilmListAdapter
import profitsw2000.diffapps.presentation.view.adapters.OnItemClickListener
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()
    private val adapter by lazy {
        FilmListAdapter(object : OnItemClickListener {
            override fun onItemClick(id: Int) {
/*                val bundle = Bundle().apply {
                    id?.let { putInt(INFO_EXTRA, it) }
                    putString(SOCKET_EXTRA, socket.id.toString())
                }
                fullStationInfoViewModel.navigateToSocketInfoScreen(bundle)*/
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filmListRecyclerView.adapter = adapter

        val observer = Observer<AppState> {renderData(it)}

    }

    private fun renderData(appState: AppState) {

    }

    interface Controller {
        fun openDetailsFragment(filmId: String)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
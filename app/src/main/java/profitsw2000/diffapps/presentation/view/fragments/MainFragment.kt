package profitsw2000.diffapps.presentation.view.fragments

import Docs
import TopFilms
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
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
    private lateinit var docsList: ArrayList<Docs>
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
        mainViewModel.stateLiveData.observe(viewLifecycleOwner, observer)
        mainViewModel.getTopFilmsList()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {updateViews(appState.topFilms)}
            is AppState.Error -> {handleError(appState.message)}
            is AppState.Loading -> {showLoading()}
            else -> {}
        }
    }

    private fun updateViews(topFilms: TopFilms) {
        binding.progressBar.hide()
        adapter.setData(topFilms.docs as ArrayList<Docs>)
    }

    private fun handleError(message: String) {
        binding.mainConstraintLayout.showSnackBar(
            message,
        "Reload",
            {mainViewModel.getTopFilmsList()},
            Snackbar.LENGTH_INDEFINITE
        )
        binding.progressBar.hide()
    }

    private fun showLoading() {
        binding.progressBar.show()
    }

    private fun View.showSnackBar (
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    private fun View.show() : View {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
        return this
    }

    private fun View.hide() : View {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
        return this
    }

    interface Controller {
        fun openDetailsFragment(filmId: String)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
package profitsw2000.diffapps.presentation.view.fragments

import android.content.Context
import profitsw2000.diffapps.entity.topfilms.Docs
import profitsw2000.diffapps.entity.topfilms.TopFilms
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.databinding.FragmentMainBinding
import profitsw2000.diffapps.model.AppState
import profitsw2000.diffapps.presentation.view.adapters.FilmListAdapter
import profitsw2000.diffapps.presentation.view.adapters.OnItemClickListener
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var isNotLastPage = true
    private var page = 1
    private val mainViewModel: MainViewModel by viewModel()
    private var docsList: ArrayList<Docs> = arrayListOf()
    private val controller by lazy { activity as Controller }

    private var adapter = FilmListAdapter(docsList, object : OnItemClickListener {
            override fun onItemClick(id: Int) {
                controller.openDetailsFragment(id)
            }
    })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is Controller) {
            throw IllegalStateException("Activity должна наследоваться от Controller")
        }
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
        binding.filmListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.filmListRecyclerView.canScrollVertically(1) && isNotLastPage) {
                    mainViewModel.getTopFilmsList(page)
                }
            }
        })

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
        docsList.addAll(topFilms.docs)
        adapter.notifyDataSetChanged()
        with(topFilms) {
            isNotLastPage = (this.page < this.pages)
            this@MainFragment.page = this.page + 1
        }
    }

    private fun handleError(message: String) {
        binding.mainConstraintLayout.showSnackBar(
            message,
        "Reload",
            {mainViewModel.getTopFilmsList(page)},
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
        fun openDetailsFragment(id: Int)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
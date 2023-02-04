package profitsw2000.diffapps.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.databinding.ActivityMainBinding
import profitsw2000.diffapps.model.AppState
import profitsw2000.diffapps.presentation.view.adapter.RedditPostsPagedListAdapter
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RedditPostsPagedListAdapter()
        with(binding) {
            topPostsRecyclerview.setHasFixedSize(false)
            topPostsRecyclerview.adapter = adapter
        }

        mainViewModel.postPagedList.observe(this, Observer {
            adapter.submitList(it)
        })

        mainViewModel.appState.observe(this, Observer {
            when(it) {
                is AppState.Loading -> binding.progressbar.visibility = View.VISIBLE
                is AppState.Loaded -> {
                    binding.progressbar.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                }
                is AppState.Error -> {
                    binding.progressbar.visibility = View.GONE
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorTextView.text = it.message
                }
                else -> {}
            }
        })
    }
}
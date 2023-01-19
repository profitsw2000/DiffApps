package profitsw2000.diffapps.presentation.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.FragmentMainBinding
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    interface Controller {
        fun openDetailsFragment(filmId: String)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
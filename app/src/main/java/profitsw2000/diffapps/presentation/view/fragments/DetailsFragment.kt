package profitsw2000.diffapps.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.FragmentDetailsBinding
import profitsw2000.diffapps.entity.filmdetails.FilmDetails
import profitsw2000.diffapps.entity.filmdetails.Person
import profitsw2000.diffapps.presentation.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by viewModel()
    private var filmId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmId = arguments.let { it!!.getInt(BUNDLE_EXTRA) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAllViews()
        binding.progressBar.show()
        observeData()
    }

    private fun observeData() {
        val detailsObserver = Observer<FilmDetails> { showFilmDetails(it) }
        val errorObserver = Observer<String> { showError(it) }

        detailsViewModel.stateLiveData.observe(viewLifecycleOwner, detailsObserver)
        detailsViewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        detailsViewModel.getFilmDetails(filmId)
    }

    private fun showFilmDetails(filmDetails: FilmDetails) {
        with(binding) {
            progressBar.hide()
            Picasso.get().load(filmDetails.poster.url).into(filmPosterImageView)
            filmTitleTextView.text = filmDetails.name
            releaseYearTextView.text = resources.getString(
                R.string.release_year_text,
                filmDetails.year.toString()
            )
            durationTextView.text = resources.getString(
                R.string.film_duration_text,
                filmDetails.movieLength.toString()
            )
            genresTextView.text = resources.getString(
                R.string.film_genres_text,
                filmDetails.genres[0].name
            )
            ratingTextView.text = resources.getString(
                R.string.film_rating_text,
                filmDetails.rating.kp.toString()
            )
            descriptionTextView.text = filmDetails.description
            actorsListTextView.text = getActorsList(filmDetails.persons)
            showAllViews()
        }
    }

    private fun getActorsList(personList: List<Person>) : String {
        var actors = StringBuilder()

        for(person in personList) {
            actors.append("${person.name}, ")
        }
        return actors.toString()
    }

    private fun showError(error: String) {
        with(binding) {
            progressBar.hide()
            mainConstraintLayout.showSnackBar(
                error,
            "Reload",
                {detailsViewModel.getFilmDetails(filmId)},
                Snackbar.LENGTH_INDEFINITE
            )
        }
    }

    private fun hideAllViews() {
        with(binding) {
            filmTitleTextView.hide()
            filmPosterImageView.hide()
            releaseYearTextView.hide()
            durationTextView.hide()
            genresTextView.hide()
            ratingTextView.hide()
            descriptionTitleTextView.hide()
            descriptionTextView.hide()
            actorsTitleTextView.hide()
            actorsListTextView.hide()
        }
    }

    private fun showAllViews() {
        with(binding) {
            filmTitleTextView.show()
            filmPosterImageView.show()
            releaseYearTextView.show()
            durationTextView.show()
            genresTextView.show()
            ratingTextView.show()
            descriptionTitleTextView.show()
            descriptionTextView.show()
            actorsTitleTextView.show()
            actorsListTextView.show()
        }
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

    companion object {

        const val BUNDLE_EXTRA = "film_id"

        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
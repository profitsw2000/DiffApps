package profitsw2000.diffapps.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.entity.filmdetails.FilmDetails
import profitsw2000.diffapps.mappers.FilmDetailsMapper

class DetailsViewModel(
    private val repository: Repository,
    private val filmDetailsMapper: FilmDetailsMapper
) : BaseViewModel() {

    private val _stateLiveData = MutableLiveData<FilmDetails>()
    val stateLiveData: LiveData<FilmDetails> by this::_stateLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> by this::_errorLiveData

    fun getFilmDetails(filmId: Int) {
        repository.getFilmDetailsById(filmId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _stateLiveData.value = filmDetailsMapper.map(it)
                },
                {
                    _errorLiveData.value = it.message.toString()
                }
            )
    }
}
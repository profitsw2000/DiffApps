package profitsw2000.diffapps.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.mappers.TopFilmsMapper
import profitsw2000.diffapps.model.AppState
import java.util.concurrent.TimeUnit

class MainViewModel (
    private val repository: Repository,
    private val topFilmsMapper: TopFilmsMapper
) : BaseViewModel() {

    private val _stateLiveData = MutableLiveData<AppState>()
    val stateLiveData: LiveData<AppState> by this::_stateLiveData

    fun getTopFilmsList() {
        _stateLiveData.value = AppState.Loading
        repository.getTopFilmsList()
            .delaySubscription(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _stateLiveData.value = AppState.Success(topFilmsMapper.map(it))
                },
                {
                    _stateLiveData.value = AppState.Error(it.message.toString())
                }
            )
    }
}
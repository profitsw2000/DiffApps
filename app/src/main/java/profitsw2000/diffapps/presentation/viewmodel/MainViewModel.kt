package profitsw2000.diffapps.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.model.DayBloodPressure

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bpMeasurementsLiveData = MutableLiveData<List<DayBloodPressure>>()
    val bpMeasurementsLiveData: LiveData<List<DayBloodPressure>> by this::_bpMeasurementsLiveData

    fun getAllBPMeasurementResults() {
        repository.getAllBPMeasurementResults()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _bpMeasurementsLiveData.value = it
                },
                {

                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
package profitsw2000.diffapps.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.model.BloodPressure
import profitsw2000.diffapps.model.DayBloodPressure

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _bpMeasurementsLiveData = MutableLiveData<List<DayBloodPressure>>()
    val bpMeasurementsLiveData: LiveData<List<DayBloodPressure>> by this::_bpMeasurementsLiveData

    private val _firebaseWriteResultLiveData = MutableLiveData<Boolean>()
    val firebaseWriteResultLiveData: LiveData<Boolean> by this::_firebaseWriteResultLiveData

    fun getAllBPMeasurementResults() {
        repository.getAllBPMeasurementResults()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _bpMeasurementsLiveData.value = it
                },
                {
                    Log.d("VVV", it.message.toString())
                }
            )
    }

    fun addBPMeasurement(bloodPressure: BloodPressure) {
        repository.addBPMeasurementResult(bloodPressure)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _firebaseWriteResultLiveData.value = true
                    getAllBPMeasurementResults()
                },
                {
                    _firebaseWriteResultLiveData.value = false
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
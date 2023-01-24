package profitsw2000.diffapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.utils.Coordinates

class MainViewModel (private val repository: Repository) : ViewModel() {

    val carCoordinatesLiveData: LiveData<Coordinates> = repository.carDriver.coordinateSource.asLiveData()

    fun startCar() {
        repository.carDriver.startJob()
    }

    fun stopCar() {
        repository.carDriver.stopJob()
    }
}
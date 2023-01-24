package profitsw2000.diffapps.data

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import profitsw2000.diffapps.utils.Coordinates

class CarDriver (private val scope: CoroutineScope) {

    private val mutableCoordinateSource: MutableStateFlow<Coordinates> = MutableStateFlow(
        Coordinates(0f, 0f, 90f)
    )
    val coordinateSource: StateFlow<Coordinates> = mutableCoordinateSource
    private var job: Job? = null
    private var counter = 0

    fun startJob() {
        job = scope.launch {
            while (isActive) {
                counter++
                mutableCoordinateSource.value = Coordinates(counter.toFloat(), 0f, 0f)
                delay(100)
            }
        }
    }

    fun stopJob() {
        job?.cancel()
        job = null
        counter = 0
    }
}
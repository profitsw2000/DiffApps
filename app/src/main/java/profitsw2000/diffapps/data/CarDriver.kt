package profitsw2000.diffapps.data

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import profitsw2000.diffapps.utils.Coordinates
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.sin

private const val angleStep = 1
private const val stepInRadian = (angleStep*(PI/180)).toFloat()
private const val distanceInRadian = (3*PI).toFloat()

class CarDriver (private val scope: CoroutineScope) {

    private var rotation = 0f
    private var x = 0f
    private var y = 0f
    private var pathAmplitude = 0
    private var pathLength = 0
    private var counter = 0
    private var increment = 1
    private var isStarted = false
    private val mutableCoordinateSource: MutableStateFlow<Coordinates> = MutableStateFlow(
        Coordinates(0f, 0f, 180f)
    )
    val coordinateSource: StateFlow<Coordinates> = mutableCoordinateSource
    private var job: Job? = null

    fun startCar() {
        if (!isStarted) {
            isStarted = !isStarted
            job = scope.launch {
                while (isActive) {
                    mutableCoordinateSource.value = getCarCoordinates()
                    delay(10)
                }
            }
        }
    }

    private fun stopJob() {
        job?.cancel()
        job = null
        increment *= -1
        isStarted = !isStarted
    }

    private fun getCarCoordinates(): Coordinates {
        val oldX = x
        val oldY = y

        counter += increment
        val yInRadian = counter*stepInRadian
        if (yInRadian < distanceInRadian && yInRadian > 0) {
            y = ((yInRadian*pathLength)/distanceInRadian)
            x = ((pathAmplitude*sin(yInRadian - (PI/2).toFloat())) + pathAmplitude)

            val direction = if (oldX <= x) 90f
            else -90f

            rotation = if(oldX != x) (((atan((oldY-y)/(oldX-x)))*180f)/(PI.toFloat())) + direction
            else 90f + direction
        } else {
            stopJob()
        }
        return Coordinates(x, y, rotation)
    }

    fun setPathAmplitudeAndLength(pathAmplitude: Int, pathLength: Int) {
        this.pathAmplitude = pathAmplitude
        this.pathLength = pathLength
    }
}
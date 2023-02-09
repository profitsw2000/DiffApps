package profitsw2000.diffapps.data.local

import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.model.BloodPressure
import profitsw2000.diffapps.model.DayBloodPressure
import java.util.*

class FakeRepository : Repository {


    private val bloodPressure = BloodPressure(
        id = "202302081034",
        date = Date().time,
        highPressure = 130,
        lowPressure = 70,
        pulse = 60
    )

    private val bloodPressureList = arrayListOf(
        bloodPressure,
        bloodPressure,
        bloodPressure,
        bloodPressure,
        bloodPressure,
        bloodPressure
    )

    private val dayBloodPressure = DayBloodPressure(
        id = "20230208",
        date = Date().time,
        bloodPressureList
    )

    private val dayBloodPressureList = arrayListOf(
        dayBloodPressure,
        dayBloodPressure,
        dayBloodPressure
    )

    override fun getAllBPMeasurementResults(): Single<List<DayBloodPressure>> {
        return Single.create { emitter -> emitter.onSuccess(dayBloodPressureList)}
    }
}
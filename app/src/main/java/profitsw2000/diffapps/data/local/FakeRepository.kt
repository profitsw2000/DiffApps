package profitsw2000.diffapps.data.local

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
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
        bloodPressure
    )

    private val dayBloodPressure = DayBloodPressure(
        id = "20230208",
        date = Date().time,
        bloodPressureList
    )

    private val dayBloodPressureList = arrayListOf(
        dayBloodPressure,
        dayBloodPressure
    )

    override fun getAllBPMeasurementResults(): Single<List<DayBloodPressure>> {
        return Single.create { emitter -> emitter.onSuccess(dayBloodPressureList)}
    }

    override fun addBPMeasurementResult(bloodPressure: BloodPressure): Completable {
        for ((index, dayBloodPressure) in dayBloodPressureList.withIndex()) {
            val id = bloodPressure.id?.take(8)
            if (dayBloodPressure.id == id) {
                val list = dayBloodPressureList[index].bloodPressureList.toMutableList()
                list.add(bloodPressure)
                dayBloodPressureList[index].bloodPressureList = list
                return Completable.create{ emitter ->
                    emitter.onComplete()
                }
            }
        }

        val dayBloodPressure = bloodPressure.date?.let {
            bloodPressure.id?.let { it1 ->
                DayBloodPressure(
                    id = it1.take(8),
                    date = it,
                    arrayListOf(bloodPressure)
                )
            }
        }
        if (dayBloodPressure != null) {
            dayBloodPressureList.add(dayBloodPressure)
        }
        return Completable.create{ emitter ->
            emitter.onComplete()
        }
    }

}
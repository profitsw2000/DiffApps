package profitsw2000.diffapps.domain

import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.model.DayBloodPressure

interface Repository {

    fun getAllBPMeasurementResults() : Single<List<DayBloodPressure>>

}
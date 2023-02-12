package profitsw2000.diffapps.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.model.BloodPressure
import profitsw2000.diffapps.model.DayBloodPressure

interface Repository {

    fun getAllBPMeasurementResults() : Single<List<DayBloodPressure>>

    fun addBPMeasurementResult(bloodPressure: BloodPressure) : Completable

}
package profitsw2000.diffapps.data.firestore

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.model.BloodPressure
import profitsw2000.diffapps.model.DayBloodPressure
import profitsw2000.diffapps.utils.bloodPressureListToDayBloodPressureList

class FirebaseRepository(private val db: FirebaseFirestore) : Repository {

    private val measurementCollectionReference = db.collection("DayBloodPressure")

    override fun getAllBPMeasurementResults(): Single<List<DayBloodPressure>> {
        return Single.create { emitter ->
            measurementCollectionReference.get()
                .addOnSuccessListener {
                    val bloodPressureList = mutableListOf<BloodPressure>()
                    for (documentSnapShot in it) {
                        bloodPressureList.add(documentSnapShot.toObject(BloodPressure::class.java))
                    }
                    emitter.onSuccess(bloodPressureListToDayBloodPressureList(bloodPressureList))
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }

    override fun addBPMeasurementResult(bloodPressure: BloodPressure): Completable {

        val batch = db.batch()
        bloodPressure.id?.let { measurementCollectionReference.document(it) }?.let { batch.set(it,bloodPressure) }

        return Completable.create { emitter ->
            batch.commit()
                .addOnSuccessListener {
                    emitter.onComplete()
                }.addOnFailureListener { e->
                    emitter.onError(e)
                }
        }
    }

/*    override fun observeBPMeasurementResults(): Observable<List<DayBloodPressure>> {
        return Observable.create { emitter ->
            measurementCollectionReference.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    emitter.onError(error)
                } else {
                    snapshot?.let { querySnapshot ->
                        val tasks = querySnapshot.toObjects(BloodPressure::class.java)
                        emitter.onNext(tasks)
                    } ?: emitter.onError(error)
                }
            }
        }
    }*/
}
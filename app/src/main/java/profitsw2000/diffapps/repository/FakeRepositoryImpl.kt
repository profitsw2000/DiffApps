package profitsw2000.diffapps.repository

import TopFilmsDTO
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class FakeRepositoryImpl : Repository {
    override fun getTopFilmsList(): Single<List<TopFilmsDTO>> {
        TODO("Not yet implemented")
    //return Flowable.just()
    }

    override fun getTopFilmsList(page: Int): Single<List<TopFilmsDTO>> {
        TODO("Not yet implemented")
    }
}
package profitsw2000.diffapps.data.local

import profitsw2000.diffapps.model.topfilms.TopFilmsDTO
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.Single.just
import profitsw2000.diffapps.domain.Repository

class FakeRepositoryImpl : Repository {
    override fun getTopFilmsList(): Single<TopFilmsDTO> {
        return just(getFakeTopFilmsList())
    }

    override fun getTopFilmsList(page: Int): Single<TopFilmsDTO> {
        return just(getFakeTopFilmsList())
    }
}
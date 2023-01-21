package profitsw2000.diffapps.data.web

import TopFilmsDTO
import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.domain.Repository

class RepositoryImpl : Repository {
    override fun getTopFilmsList(): Single<TopFilmsDTO> {
        TODO("Not yet implemented")
    }

    override fun getTopFilmsList(page: Int): Single<TopFilmsDTO> {
        TODO("Not yet implemented")
    }
}
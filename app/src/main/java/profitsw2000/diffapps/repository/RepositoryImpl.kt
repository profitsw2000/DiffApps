package profitsw2000.diffapps.repository

import TopFilmsDTO
import io.reactivex.rxjava3.core.Single

class RepositoryImpl : Repository {
    override fun getTopFilmsList(): Single<List<TopFilmsDTO>> {
        TODO("Not yet implemented")
    }

    override fun getTopFilmsList(page: Int): Single<List<TopFilmsDTO>> {
        TODO("Not yet implemented")
    }
}
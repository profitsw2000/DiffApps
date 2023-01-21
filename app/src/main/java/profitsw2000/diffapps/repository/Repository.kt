package profitsw2000.diffapps.repository

import TopFilmsDTO
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getTopFilmsList(): Single<List<TopFilmsDTO>>

    fun getTopFilmsList(page: Int): Single<List<TopFilmsDTO>>

}
package profitsw2000.diffapps.domain

import TopFilmsDTO
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getTopFilmsList(): Single<TopFilmsDTO>

    fun getTopFilmsList(page: Int): Single<TopFilmsDTO>

}
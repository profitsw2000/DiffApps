package profitsw2000.diffapps.domain

import profitsw2000.diffapps.model.topfilms.TopFilmsDTO
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getTopFilmsList(): Single<TopFilmsDTO>

    fun getTopFilmsList(page: Int): Single<TopFilmsDTO>


}
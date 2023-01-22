package profitsw2000.diffapps.domain

import profitsw2000.diffapps.model.topfilms.TopFilmsDTO
import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.entity.filmdetails.FilmDetails
import profitsw2000.diffapps.model.filmdetails.FilmDetailsDTO

interface Repository {

    fun getTopFilmsList(): Single<TopFilmsDTO>

    fun getTopFilmsList(page: Int): Single<TopFilmsDTO>

    fun getFilmDetailsById(filmId: Int): Single<FilmDetailsDTO>
}
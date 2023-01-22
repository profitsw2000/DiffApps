package profitsw2000.diffapps.data.web

import profitsw2000.diffapps.model.topfilms.TopFilmsDTO
import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.entity.filmdetails.FilmDetails
import profitsw2000.diffapps.model.filmdetails.FilmDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface KinoPoiskApi {
    @GET("movie?field=rating.kp&search=8-10&field=year&search=2022&field=typeNumber&search=2&sortField=year&sortType=1&sortField=votes.imdb&sortType=-1")
    fun getTopFilmsList(@Query("token") token: String): Single<TopFilmsDTO>

    @GET("movie?field=rating.kp&search=8-10&field=year&search=2022&field=typeNumber&search=2&sortField=year&sortType=1&sortField=votes.imdb&sortType=-1")
    fun getTopFilmsListPage(
        @Query("page") page: String,
        @Query("token") token: String): Single<TopFilmsDTO>

    @GET("movie?field=id")
    fun getFilmDetailsById(
        @Query("search") filmId: Int,
        @Query("token") token: String): Single<FilmDetailsDTO>
}
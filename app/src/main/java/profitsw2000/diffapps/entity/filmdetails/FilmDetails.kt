package profitsw2000.diffapps.entity.filmdetails

import profitsw2000.diffapps.entity.topfilms.*

data class FilmDetails(

    val description: String,
    val genres: List<Genre>,
    val movieLength: Int,
    val name: String,
    val persons: List<Person>,
    val poster: Poster,
    val rating: Rating,
    val year: Int

)

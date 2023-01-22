package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.entity.filmdetails.FilmDetails
import profitsw2000.diffapps.entity.topfilms.TopFilms
import profitsw2000.diffapps.model.filmdetails.FilmDetailsDTO
import profitsw2000.diffapps.model.topfilms.TopFilmsDTO

class FilmDetailsMapper (
    private val genreMapper: GenreMapper,
    private val personMapper: PersonMapper,
    private val posterMapper: PosterMapper,
    private val ratingMapper: RatingMapper
){
    fun map(filmDetailsDTO: FilmDetailsDTO): FilmDetails {
        return FilmDetails(
            description = filmDetailsDTO.description,
            genres = filmDetailsDTO.genres.map { genreMapper.map(it) },
            movieLength = filmDetailsDTO.movieLength,
            name = filmDetailsDTO.name,
            persons = filmDetailsDTO.persons.map { personMapper.map(it) },
            poster = posterMapper.map(filmDetailsDTO.poster),
            rating = ratingMapper.map(filmDetailsDTO.rating),
            year = filmDetailsDTO.year
        )
    }
}
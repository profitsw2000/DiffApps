package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.entity.filmdetails.Genre
import profitsw2000.diffapps.entity.topfilms.TopFilms
import profitsw2000.diffapps.model.filmdetails.GenreDTO
import profitsw2000.diffapps.model.topfilms.TopFilmsDTO

class GenreMapper (){
    fun map(genreDTO: GenreDTO): Genre {
        return Genre(
            name = genreDTO.name
        )
    }
}
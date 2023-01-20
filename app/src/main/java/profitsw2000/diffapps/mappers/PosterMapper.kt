package profitsw2000.diffapps.mappers

import PosterDTO
import profitsw2000.diffapps.entity.Poster

class PosterMapper {
    fun map(posterDTO: PosterDTO): Poster {
        return Poster(
            url = posterDTO.url
        )
    }
}
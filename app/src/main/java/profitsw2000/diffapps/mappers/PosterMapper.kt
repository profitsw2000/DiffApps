package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.model.topfilms.PosterDTO
import profitsw2000.diffapps.entity.topfilms.Poster

class PosterMapper {
    fun map(posterDTO: PosterDTO): Poster {
        return Poster(
            url = posterDTO.url
        )
    }
}
package profitsw2000.diffapps.mappers

import RatingDTO
import profitsw2000.diffapps.entity.Rating

class RatingMapper {
    fun map(ratingDTO: RatingDTO): Rating {
        return Rating(
            kp = ratingDTO.kp
        )
    }
}
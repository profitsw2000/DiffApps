package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.model.topfilms.RatingDTO
import profitsw2000.diffapps.entity.topfilms.Rating

class RatingMapper {
    fun map(ratingDTO: RatingDTO): Rating {
        return Rating(
            kp = ratingDTO.kp
        )
    }
}
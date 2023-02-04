package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.entity.PostDTO
import profitsw2000.diffapps.model.Post

class PostMapper {
    fun map(postDTO: PostDTO): Post {
        return Post(
            id = postDTO.id,
            numComments = postDTO.numComments,
            score = postDTO.score,
            title = postDTO.title
        )
    }
}
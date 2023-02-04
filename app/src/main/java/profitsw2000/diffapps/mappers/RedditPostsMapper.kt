package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.entity.RedditPostsDTO
import profitsw2000.diffapps.model.RedditPosts

class RedditPostsMapper (private val dataMapper: DataMapper) {
    fun map(redditPostsDTO: RedditPostsDTO): RedditPosts {
        return RedditPosts(
            data = dataMapper.map(redditPostsDTO.dataDTO)
        )
    }
}
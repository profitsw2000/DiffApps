package profitsw2000.diffapps.data.web

import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.entity.RedditPostsDTO
import profitsw2000.diffapps.model.RedditPosts
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    @GET("/top.json")
    fun getRedditPosts(
        @Query("after") after: String,
        @Query("limit") limit: Int
    ): Single<RedditPostsDTO>
}
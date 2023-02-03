package profitsw2000.diffapps.entity


import com.google.gson.annotations.SerializedName

data class RedditPostsDTO(
    @SerializedName("data")
    val dataDTO: DataDTO
)
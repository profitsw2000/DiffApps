package profitsw2000.diffapps.entity


import com.google.gson.annotations.SerializedName

data class PostDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("num_comments")
    val numComments: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("title")
    val title: String
)
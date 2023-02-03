package profitsw2000.diffapps.model


import com.google.gson.annotations.SerializedName

data class Post(
    val id: String,
    val numComments: Int,
    val score: Int,
    val title: String
)
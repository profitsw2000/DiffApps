package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class TrailerDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)
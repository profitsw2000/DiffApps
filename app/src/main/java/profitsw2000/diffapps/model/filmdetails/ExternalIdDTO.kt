package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class ExternalIdDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("imdb")
    val imdb: String,
    @SerializedName("kpHD")
    val kpHD: String,
    @SerializedName("tmdb")
    val tmdb: Int
)
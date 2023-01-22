package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class RatingDTO(
    @SerializedName("await")
    val await: Int,
    @SerializedName("filmCritics")
    val filmCritics: Double,
    @SerializedName("_id")
    val id: String,
    @SerializedName("imdb")
    val imdb: Double,
    @SerializedName("kp")
    val kp: Double,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Int
)
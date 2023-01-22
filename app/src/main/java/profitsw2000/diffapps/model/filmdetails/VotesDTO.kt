package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class VotesDTO(
    @SerializedName("await")
    val await: Int,
    @SerializedName("filmCritics")
    val filmCritics: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("imdb")
    val imdb: Int,
    @SerializedName("kp")
    val kp: Int,
    @SerializedName("russianFilmCritics")
    val russianFilmCritics: Int
)
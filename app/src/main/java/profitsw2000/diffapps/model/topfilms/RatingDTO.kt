package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class RatingDTO (
  @SerializedName("kp") val kp: Double,
  @SerializedName("imdb") val imdb: Double,
  @SerializedName("filmCritics") val filmCritics: Int,
  @SerializedName("russianFilmCritics") val russianFilmCritics: Double,
  @SerializedName("await") val await: Double,
  @SerializedName("_id") val Id: String
)
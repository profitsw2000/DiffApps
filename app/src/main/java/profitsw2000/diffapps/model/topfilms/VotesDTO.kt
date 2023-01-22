package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class VotesDTO (
  @SerializedName("kp") val kp: Int,
  @SerializedName("imdb") val imdb: Int,
  @SerializedName("filmCritics") val filmCritics: Int,
  @SerializedName("russianFilmCritics") val russianFilmCritics: Int,
  @SerializedName("await") val await: Double,
  @SerializedName("_id") val Id: String
)
package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class ExternalIdDTO (
  @SerializedName("kpHD") val kpHD: String,
  @SerializedName("imdb") val imdb: String,
  @SerializedName("tmdb") val tmdb: Int,
  @SerializedName("_id") val Id: String
)
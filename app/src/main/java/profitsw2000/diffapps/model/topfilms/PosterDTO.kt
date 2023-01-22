package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class PosterDTO (
  @SerializedName("_id") val Id: String,
  @SerializedName("url") val url: String,
  @SerializedName("previewUrl") val previewUrl: String
)
package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class LogoDTO (
  @SerializedName("_id") val Id: String,
  @SerializedName("url") val url: String
)
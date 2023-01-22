package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class ReleaseYearsDTO (
  @SerializedName("start") val start: Int,
  @SerializedName("end") val end: Int,
  @SerializedName("_id") val Id: String
)
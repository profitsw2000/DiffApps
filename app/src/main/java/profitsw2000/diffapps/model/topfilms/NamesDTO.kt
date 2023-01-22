package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class NamesDTO (
  @SerializedName("_id") val Id: String,
  @SerializedName("name") val name: String
)
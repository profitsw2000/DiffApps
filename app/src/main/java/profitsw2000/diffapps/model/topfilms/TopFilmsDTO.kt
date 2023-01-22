package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class TopFilmsDTO (
  @SerializedName("docs") val docs: List<DocsDTO>,
  @SerializedName("total") val total: Int,
  @SerializedName("limit") val limit: Int,
  @SerializedName("page") val page: Int,
  @SerializedName("pages") val pages: Int
)
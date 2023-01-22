package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class ProductionCompanyDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("url")
    val url: String
)
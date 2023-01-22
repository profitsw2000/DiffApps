package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class LogoDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("url")
    val url: String
)
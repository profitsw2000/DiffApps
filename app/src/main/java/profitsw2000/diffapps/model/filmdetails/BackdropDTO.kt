package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class BackdropDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("url")
    val url: String
)
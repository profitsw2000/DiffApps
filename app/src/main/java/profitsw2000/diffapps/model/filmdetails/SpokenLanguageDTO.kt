package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class SpokenLanguageDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("nameEn")
    val nameEn: String
)
package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class PremiereDTO(
    @SerializedName("bluray")
    val bluray: String,
    @SerializedName("cinema")
    val cinema: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("dvd")
    val dvd: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("russia")
    val russia: String,
    @SerializedName("world")
    val world: String
)
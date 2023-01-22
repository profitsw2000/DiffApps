package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class RussiaDTO(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("value")
    val value: Int
)
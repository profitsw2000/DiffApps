package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class FactDTO(
    @SerializedName("spoiler")
    val spoiler: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)
package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class TechnologyDTO(
    @SerializedName("has3D")
    val has3D: Boolean,
    @SerializedName("hasImax")
    val hasImax: Boolean,
    @SerializedName("_id")
    val id: String
)
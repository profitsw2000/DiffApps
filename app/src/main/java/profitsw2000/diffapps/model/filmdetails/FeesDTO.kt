package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class FeesDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("russia")
    val russia: RussiaDTO,
    @SerializedName("usa")
    val usa: UsaDTO,
    @SerializedName("world")
    val world: WorldDTO
)
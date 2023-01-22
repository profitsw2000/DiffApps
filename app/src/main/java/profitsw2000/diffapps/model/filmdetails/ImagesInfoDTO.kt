package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class ImagesInfoDTO(
    @SerializedName("framesCount")
    val framesCount: Int,
    @SerializedName("_id")
    val id: String
)
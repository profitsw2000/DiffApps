package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class ImagesDTO(
    @SerializedName("backdropsCount")
    val backdropsCount: Int,
    @SerializedName("framesCount")
    val framesCount: Int,
    @SerializedName("postersCount")
    val postersCount: Int
)
package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class VideosDTO(
    @SerializedName("_id")
    val id: String,
    @SerializedName("trailers")
    val trailers: List<TrailerDTO>
)
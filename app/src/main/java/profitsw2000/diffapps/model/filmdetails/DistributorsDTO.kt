package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class DistributorsDTO(
    @SerializedName("distributor")
    val distributor: String,
    @SerializedName("distributorRelease")
    val distributorRelease: String
)
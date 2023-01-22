package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName

data class PersonDTO(
    @SerializedName("enName")
    val enName: String,
    @SerializedName("enProfession")
    val enProfession: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String
)
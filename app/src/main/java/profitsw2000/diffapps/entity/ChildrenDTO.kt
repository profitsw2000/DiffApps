package profitsw2000.diffapps.entity


import com.google.gson.annotations.SerializedName

data class ChildrenDTO(
    @SerializedName("data")
    val postDTO: PostDTO
)
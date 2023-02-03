package profitsw2000.diffapps.entity


import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("after")
    val after: String,
    @SerializedName("before")
    val before: String,
    @SerializedName("children")
    val children: List<ChildrenDTO>
)
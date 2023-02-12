package profitsw2000.diffapps.model

data class BloodPressure(
    val id: String? = "",
    val date: Long? = 0,
    val highPressure: Int? = 0,
    val lowPressure: Int? = 0,
    val pulse: Int? = 0
)

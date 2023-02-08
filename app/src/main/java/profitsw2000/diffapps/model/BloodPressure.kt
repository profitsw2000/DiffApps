package profitsw2000.diffapps.model

data class BloodPressure(
    val id: String,
    val date: Long,
    val highPressure: Int,
    val lowPressure: Int,
    val pulse: Int
)

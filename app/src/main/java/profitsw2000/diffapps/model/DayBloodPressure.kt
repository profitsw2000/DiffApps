package profitsw2000.diffapps.model

data class DayBloodPressure(
    val id: String,
    val date: Long,
    val bloodPressureList: List<BloodPressure>
)

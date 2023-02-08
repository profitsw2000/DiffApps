package profitsw2000.diffapps.model

data class DayBloodPressure(
    val date: Long,
    val bloodPressureList: List<BloodPressure>
)

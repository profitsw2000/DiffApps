package profitsw2000.diffapps.model

data class Lesson(
    val name: String,
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int,
    val teacher: String,
    val description: String,
    val online: Boolean
)

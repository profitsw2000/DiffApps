package profitsw2000.diffapps.utils

import profitsw2000.diffapps.model.BloodPressure
import profitsw2000.diffapps.model.DayBloodPressure

fun bloodPressureListToDayBloodPressureList(bloodPressureList: List<BloodPressure>) : List<DayBloodPressure> {

    val dayBloodPressureList = mutableListOf<DayBloodPressure>()
    val byDate = bloodPressureList.groupBy {it.id?.take(8)}
    for (element in byDate) {
        val id = element.key
        val date = element.value[0].date
        val bloodPressureList = element.value

        if (id != null && date != null) {
            dayBloodPressureList.add(DayBloodPressure(id = id,date = date, bloodPressureList))
        }
    }
    return dayBloodPressureList
}
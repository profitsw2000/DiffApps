package profitsw2000.diffapps.utils

import java.text.SimpleDateFormat
import java.util.*

class RemainingTimeCalculator {

    fun getTimeBeforeExamStringList(examDate: String, format: String) : List<String> {

        val timeBeforeExamInMilliseconds =
            getTimeBeforeExamInMilliseconds(format, examDate)

        val daysLeft = timeBeforeExamInMilliseconds/(24*60*60*1000)
        val hoursLeft = timeBeforeExamInMilliseconds/(60*60*1000)
        val minutesLeft = timeBeforeExamInMilliseconds/(60*1000)

        val tenDays = daysLeft/10
        val days = daysLeft%10
        val tenHours = (hoursLeft - (daysLeft*24))/10
        val hours = (hoursLeft - (daysLeft*24))%10
        val tenMinutes = (minutesLeft - (hoursLeft*60))/10
        val minutes = (minutesLeft - (hoursLeft*60))%10

        return listOf(
            tenDays.toString(),
            days.toString(),
            tenHours.toString(),
            hours.toString(),
            tenMinutes.toString(),
            minutes.toString()
        )
    }

    private fun getTimeBeforeExamInMilliseconds(
        format: String,
        examDate: String
    ): Long {
        val sdf = SimpleDateFormat(format)
        val mDate = sdf.parse(examDate)
        val examTimeInMilliseconds = mDate.time
        val currentDate = Date().time
        val timeBeforeExamInMilliseconds =
            if (examTimeInMilliseconds > currentDate) examTimeInMilliseconds - currentDate
            else 0
        return timeBeforeExamInMilliseconds
    }
}
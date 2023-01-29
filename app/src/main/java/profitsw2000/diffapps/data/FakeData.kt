package profitsw2000.diffapps.data

import profitsw2000.diffapps.model.Classes
import profitsw2000.diffapps.model.Homework
import profitsw2000.diffapps.model.Lesson
import profitsw2000.diffapps.model.Schedule

val history1 = Lesson(
    name = "History",
    startHour = 8,
    startMinute = 0,
    endHour = 8,
    endMinute = 45,
    teacher = "Mr Fells",
    description = "",
    isOnline = true
)

val literature1 = Lesson(
    name = "Literature",
    startHour = 9,
    startMinute = 0,
    endHour = 9,
    endMinute = 45,
    teacher = "Mr Gaston",
    description = "",
    isOnline = false
)

val physicalEducation1 = Lesson(
    name = "Physical education",
    startHour = 10,
    startMinute = 0,
    endHour = 10,
    endMinute = 45,
    teacher = "Mr Fells",
    description = "Intensive preparation for The Boxing Championship of the world in Las Vegas, Nevada",
    isOnline = false
)

val phisics1 = Lesson(
    name = "Physics",
    startHour = 11,
    startMinute = 0,
    endHour = 11,
    endMinute = 45,
    teacher = "Mr Gaston",
    description = "",
    isOnline = false
)

val english1 = Lesson(
    name = "English",
    startHour = 12,
    startMinute = 0,
    endHour = 12,
    endMinute = 45,
    teacher = "Mr Fells",
    description = "",
    isOnline = false
)

val math1 = Lesson(
    name = "Mathematics",
    startHour = 13,
    startMinute = 0,
    endHour = 13,
    endMinute = 45,
    teacher = "Mr Gaston",
    description = "",
    isOnline = true
)

val lessonList = listOf(history1, literature1, physicalEducation1, phisics1, english1, math1)

val monday = Classes("Monday", lessonList)
val tuesday = Classes("Tuesday", lessonList)
val wednesday = Classes("Wednesday", lessonList)
val thursday = Classes("Thursday", lessonList)
val friday = Classes("Friday", lessonList)
val saturday = Classes("Saturday", lessonList)
val sunday = Classes("Sunday", lessonList)

val schedule = Schedule(classesList = listOf(monday, tuesday, wednesday, thursday, friday, saturday, sunday))

val literatureHomework = Homework(
    disciplineName = "Literature",
    task = "Read pages 35-110 of the Master and Margarita",
    daysLeft = 2
)

val historyHomework = Homework(
    disciplineName = "History",
    task = "Read history articles about war between Rome and Carfaghen.",
    daysLeft = 3
)

val physicsHomework = Homework(
    disciplineName = "Physics",
    task = "Learn Newtons law motion.",
    daysLeft = 5
)

val englishHomework = Homework(
    disciplineName = "English",
    task = "Write text about any english writer of your choice.",
    daysLeft = 7
)

val mathHomework = Homework(
    disciplineName = "Mathematics",
    task = "Solve task #33.",
    daysLeft = 10
)

val taskList = listOf(literatureHomework, historyHomework, physicsHomework, englishHomework, mathHomework)
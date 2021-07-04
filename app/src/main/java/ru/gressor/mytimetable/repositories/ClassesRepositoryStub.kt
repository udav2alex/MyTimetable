package ru.gressor.mytimetable.repositories

import ru.gressor.mytimetable.entities.Class
import java.text.SimpleDateFormat
import java.util.*

class ClassesRepositoryStub : ClassesRepository {
    override suspend fun getClasses(): List<Class> = listOf(
        Class("History", "Mr. Smith", getMillis("8:00"), getMillis("8:45")),
        Class(
            "Math",
            "Mr. Dorodnyy",
            getMillis("9:00"),
            getMillis("9:45"),
            false,
            "https://join.skype.com/invite/KPZoom9RebJ5"
        ),
        Class("Literature", "Mrs. Lipton", getMillis("10:00"), getMillis("10:45"), true),
        Class("Physics", "Mrs. Billow", getMillis("11:00"), getMillis("11:45")),
        Class("Geography", "Mr. Smith", getMillis("12:00"), getMillis("12:45"), true)
    )

    private fun getMillis(time: String): Long {
        val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            .parse("${df.format(Date())} $time")!!
        return dateTime.time
    }
}
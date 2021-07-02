package ru.gressor.mytimetable.repositories

import ru.gressor.mytimetable.entities.Exam
import java.util.*

class ExamsRepositoryStub : ExamsRepository {
    override suspend fun getExams() : List<Exam> = listOf(
        // hide first Exam to check ExamTimer with days count (without seconds)
        Exam("Exam 1 (in 5 hours)", Date().time + 18_000_000L),
        Exam("Exam 2 (in 5 days)", Date().time + 432_000_000L),
        Exam("Exam 3 (in 10 days)", Date().time + 864_000_000L),
        Exam("Exam 4 (in 12 days)", Date().time + 1_036_800_000L)
    )
}
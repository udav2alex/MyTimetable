package ru.gressor.mytimetable.repositories

import ru.gressor.mytimetable.entities.Exam

interface ExamsRepository {
    suspend fun getExams(): List<Exam>
}
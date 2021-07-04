package ru.gressor.mytimetable.repositories

import ru.gressor.mytimetable.entities.Class

interface ClassesRepository {
    suspend fun getClasses(): List<Class>
}
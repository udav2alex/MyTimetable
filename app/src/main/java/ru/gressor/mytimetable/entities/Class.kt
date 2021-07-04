package ru.gressor.mytimetable.entities

data class Class(
    val subject: String,
    val teacher: String,
    val beginning: Long,
    val ending: Long,
    val optional: Boolean = false,
    val skype: String = ""
)
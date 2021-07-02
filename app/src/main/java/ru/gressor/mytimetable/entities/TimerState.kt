package ru.gressor.mytimetable.entities

data class TimerState(
    val examName: String,
    val timerString: String,
    val showSeconds: Boolean
)
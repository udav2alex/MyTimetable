package ru.gressor.mytimetable.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.gressor.mytimetable.entities.TimerState
import ru.gressor.mytimetable.repositories.ExamsRepository

class ExamInteractor(
    private val examsRepository: ExamsRepository
) {
    val examTimerFlow: Flow<TimerState> = flow {
        examsRepository.getExams().minByOrNull { it.dateInMillis }
            ?.let {
                while (true) {
                    val ts = timerString(it.dateInMillis)
                    emit(TimerState(it.name, ts.first, ts.second))
                    kotlinx.coroutines.delay(SECOND_DELAY)
                }
            }
    }

    private fun timerString(timeInMillis: Long): Pair<String, Boolean> {
        var seconds = (timeInMillis - System.currentTimeMillis()) / 1000L

        val days = seconds / 3600 / 24
        seconds -= days * 3600 * 24

        val hours = seconds / 3600
        seconds -= hours * 3600

        val minutes = seconds / 60
        seconds -= minutes * 60

        return if (days == 0L) {
            "$hours : $minutes : $seconds" to true
        } else {
            "$days : $hours : $minutes" to false
        }
    }

    companion object {
        private const val SECOND_DELAY = 1000L
    }
}
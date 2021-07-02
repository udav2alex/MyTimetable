package ru.gressor.mytimetable.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.gressor.mytimetable.repositories.ExamsRepository

class ExamInteractor(
    private val examsRepository: ExamsRepository
) {
    val examTimerFlow: Flow<Pair<String, String>> = flow {
        emit("" to "")

        examsRepository.getExams().minByOrNull { it.dateInMillis }?.let {
            while (true) {
                emit(it.name to
                            "${((it.dateInMillis - System.currentTimeMillis()) / 1000L)} sec.")
                kotlinx.coroutines.delay(SECOND_DELAY)
            }
        }
    }

    companion object {
        private const val SECOND_DELAY = 1000L
    }
}
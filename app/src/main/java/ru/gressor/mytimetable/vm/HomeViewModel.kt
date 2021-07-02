package ru.gressor.mytimetable.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.gressor.mytimetable.interactors.ExamInteractor
import ru.gressor.mytimetable.repositories.ExamsRepository

class HomeViewModel(
    private val examsRepository: ExamsRepository
): ViewModel() {
    private val examInteractor = ExamInteractor(examsRepository)

    val examStateFlow = examInteractor.examTimerFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, "" to "")
}
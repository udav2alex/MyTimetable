package ru.gressor.mytimetable.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import ru.gressor.mytimetable.entities.Class
import ru.gressor.mytimetable.repositories.ClassesRepository

class ClassesViewModel(
    private val classesRepository: ClassesRepository
): ViewModel() {

    val classesFlow = flow {
        emit(classesRepository.getClasses())
    }.stateIn(viewModelScope, SharingStarted.Eagerly, listOf<Class>())
}
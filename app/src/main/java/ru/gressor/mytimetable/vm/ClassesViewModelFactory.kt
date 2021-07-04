package ru.gressor.mytimetable.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gressor.mytimetable.repositories.ClassesRepository

class ClassesViewModelFactory(
    private val classesRepository: ClassesRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            ClassesViewModel::class.java -> ClassesViewModel(classesRepository)
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
}
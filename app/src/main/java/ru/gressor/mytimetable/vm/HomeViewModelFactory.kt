package ru.gressor.mytimetable.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gressor.mytimetable.repositories.ExamsRepository

class HomeViewModelFactory(
    private val examsRepository: ExamsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            HomeViewModel::class.java -> HomeViewModel(examsRepository)
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
}
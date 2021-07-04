package ru.gressor.mytimetable.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.gressor.mytimetable.databinding.FragmentClassesBinding

class ClassesFragment: BaseFragment<FragmentClassesBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentClassesBinding.inflate(inflater, container, false)

}
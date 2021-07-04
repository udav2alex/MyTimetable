package ru.gressor.mytimetable.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.gressor.mytimetable.databinding.FragmentClassesBinding
import ru.gressor.mytimetable.repositories.ClassesRepositoryStub
import ru.gressor.mytimetable.ui.BaseFragment
import ru.gressor.mytimetable.utils.SkypeLinkListener
import ru.gressor.mytimetable.vm.ClassesViewModel
import ru.gressor.mytimetable.vm.ClassesViewModelFactory

class ClassesFragment : BaseFragment<FragmentClassesBinding>() {
    private lateinit var skypeLinkListener: SkypeLinkListener
    private val viewModel: ClassesViewModel by lazy {
        ViewModelProvider(this, ClassesViewModelFactory(ClassesRepositoryStub()))
            .get(ClassesViewModel::class.java)
    }
    private lateinit var adapter: ClassesRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        if (context is SkypeLinkListener) {
            skypeLinkListener = context as SkypeLinkListener
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentClassesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ClassesRecyclerView(skypeLinkListener)
        binding.classesRecycler.layoutManager = LinearLayoutManager(context)
        binding.classesRecycler.adapter = adapter

        lifecycleScope.launch {
            viewModel
                .classesFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    adapter.populate(it)
                }
        }
    }
}
package ru.gressor.mytimetable.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.gressor.mytimetable.databinding.FragmentHomeBinding
import ru.gressor.mytimetable.repositories.ExamsRepositoryStub
import ru.gressor.mytimetable.vm.HomeViewModel
import ru.gressor.mytimetable.vm.HomeViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(ExamsRepositoryStub()))
            .get(HomeViewModel::class.java)
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel
                .examStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    binding.timerTextView.text = it.second
                }
        }
    }
}
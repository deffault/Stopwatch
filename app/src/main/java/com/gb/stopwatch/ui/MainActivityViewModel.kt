package com.gb.stopwatch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.stopwatch.domain.StopwatchListOrchestrator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val stopwatchListOrchestrator: StopwatchListOrchestrator
) : ViewModel() {
    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state

    init {
        viewModelScope.launch {
            stopwatchListOrchestrator.ticker.collect {
                _state.emit(it)
            }
        }
    }

    fun start() {
        stopwatchListOrchestrator.start()
    }

    fun pause() {
        stopwatchListOrchestrator.pause()
    }

    fun stop() {
        stopwatchListOrchestrator.stop()
    }
}
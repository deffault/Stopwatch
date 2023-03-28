package com.gb.stopwatch

import com.gb.stopwatch.ui.StopwatchState

sealed class StopWatchState {
    data class Paused(
        val elapsedTime: Long
    ) : StopWatchState()

    data class Running(
        val startTime: Long,
        val elapsedTime: Long
    ) : StopWatchState()
}

package com.gb.stopwatch.domain

import com.gb.stopwatch.StopWatchState

class StopwatchStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
) {

    fun calculateRunningState(oldState: StopWatchState): StopWatchState.Running =
        when (oldState) {
            is StopWatchState.Running -> oldState
            is StopWatchState.Paused -> {
                StopWatchState.Running(
                    startTime = timestampProvider.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: StopWatchState): StopWatchState.Paused =
        when (oldState) {
            is StopWatchState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                StopWatchState.Paused(elapsedTime = elapsedTime)
            }
            is StopWatchState.Paused -> oldState
        }
}
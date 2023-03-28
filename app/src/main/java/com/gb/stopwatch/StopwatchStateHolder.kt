package com.gb.stopwatch

import com.gb.stopwatch.ui.ElapsedTimeCalculator
import com.gb.stopwatch.ui.TimestampMillisecondsFormatter

class StopwatchStateHolder(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter
) {

    var currentState: StopWatchState = StopWatchState.Paused(0)
        private set

    fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = StopWatchState.Paused(0)
    }

    fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopWatchState.Paused -> currentState.elapsedTime
            is StopWatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisecondsFormatter.format(elapsedTime)
    }
}
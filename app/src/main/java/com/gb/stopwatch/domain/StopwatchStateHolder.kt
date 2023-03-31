package com.gb.stopwatch.domain

import com.gb.stopwatch.StopWatchState

class StopwatchStateHolder(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter
) {

    private var currentState: StopWatchState = StopWatchState.Paused(0)

    fun start() {
        if (currentState !is StopWatchState.Running) {
            currentState = StopWatchState.Running(
                startTime = timestampProvider.getMilliseconds(),
                elapsedTime = (currentState as StopWatchState.Paused).elapsedTime
            )
        }
    }

    fun pause() {
        if (currentState !is StopWatchState.Paused) {
            val elapsedTime = elapsedTimeCalculator.calculate(currentState as StopWatchState.Running)
            currentState = StopWatchState.Paused(elapsedTime = elapsedTime)
        }
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
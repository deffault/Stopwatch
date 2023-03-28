package com.gb.stopwatch

import com.gb.stopwatch.ui.TimestampProvider

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: StopWatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}

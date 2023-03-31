package com.gb.stopwatch.di

import com.gb.stopwatch.data.CurrentMillProvider
import com.gb.stopwatch.domain.*
import com.gb.stopwatch.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<TimestampProvider> { CurrentMillProvider() }

    single<TimestampMillisecondsFormatter> { TimestampMillisecondsFormatter() }

    single<ElapsedTimeCalculator> {
        ElapsedTimeCalculator(
            timestampProvider = get()
        )
    }

    single<StopwatchStateHolder> {
        StopwatchStateHolder(
            timestampProvider = get(),
            elapsedTimeCalculator = get(),
            timestampMillisecondsFormatter = get()
        )
    }

    single<StopwatchListOrchestrator> {
        StopwatchListOrchestrator(stopwatchStateHolder = get())
    }

    viewModel<MainActivityViewModel> { MainActivityViewModel(stopwatchListOrchestrator = get()) }
}
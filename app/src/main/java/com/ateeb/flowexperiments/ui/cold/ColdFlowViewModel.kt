package com.ateeb.flowexperiments.ui.cold

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ColdFlowViewModel : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun flowOfDemo(): Flow<Int> {
        /**
         * Demonstrates flowOf, a cold Flow builder for emitting fixed values.
         * - Emits a predefined list of values without needing explicit emit() calls.
         * - Cold: Only emits when collected, restarting for each new collector.
         */
        val staticList: MutableList<Int> = mutableListOf()
        for (i in 1..60) staticList.add(i)

        return flowOf(staticList)
            .flatMapConcat { list ->
                flow {
                    list.forEach { emit(it) } // Converts list to individual emissions
                }
            }
            .map { it + 220 }
            .onEach { delay(500) } // Delay to simulate async emission
    }
}
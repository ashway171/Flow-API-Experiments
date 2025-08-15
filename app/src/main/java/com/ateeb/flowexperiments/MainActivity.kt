package com.ateeb.flowexperiments

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ateeb.flowexperiments.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hot  Flow
        // Cold Flow

        lifecycleScope.launch() {
            flowOfExample()
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun flowOfExample() {
        /**
         * - For emitting fixed values
         * - Doesn't need emit
         */

        val staticList: MutableList<Int> = mutableListOf()
        for (i in 1..60) staticList.add(i)

        flowOf(staticList)
            .flatMapConcat {
                flow {
                    emit(it)
                }
            }
            .map { Log.d(TAG,(it + 220).toString()) }
//            .collect {
//                delay(500)
//                binding.countTv.text = it.toString()
//            }
    }


}
package com.ateeb.flowexperiments.ui.cold

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ateeb.flowexperiments.databinding.ActivityColdFlowDemoBinding
import kotlinx.coroutines.launch

class ColdFlowDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityColdFlowDemoBinding
    private val viewModel: ColdFlowViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColdFlowDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Display flowOf code snippet
        binding.codeTv.text = """
            fun flowOfDemo(): Flow<Int> {
                val staticList = (1..60).toList()
                return flowOf(staticList)
                    .flatMapConcat { list -> flow { list.forEach { emit(it) } } }
                    .map { it + 220 }
                    .onEach { delay(500) }
            }
        """.trimIndent()

        // Trigger flowOf demo
        binding.btnFlowOf.setOnClickListener {
            binding.resultTv.text = "Running flowOf demo..."
            lifecycleScope.launch {
                viewModel.flowOfDemo().collect { value ->
                    binding.resultTv.text = "flowOf: $value"
                }
            }
        }

    }


}
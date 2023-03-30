package com.gb.stopwatch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gb.stopwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
                mainViewModel.state.collect {
                    binding.textTime.text = it
                }
            }
        }


        binding.buttonStart.setOnClickListener {
            mainViewModel.start()
        }
        binding.buttonPause.setOnClickListener {
            mainViewModel.pause()
        }
        binding.buttonStop.setOnClickListener {
            mainViewModel.stop()
        }

    }
}

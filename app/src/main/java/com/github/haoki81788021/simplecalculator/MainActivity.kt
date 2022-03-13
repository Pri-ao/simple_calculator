package com.github.haoki81788021.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.github.haoki81788021.simplecalculator.databinding.ActivityMainBinding
import com.github.haoki81788021.simplecalculator.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model: MainViewModel by viewModels()
        model.inputData.observe(this) {
            binding.inputResultArea.text = it
        }
        val numberButtons = listOf(
            binding.num0, binding.num1, binding.num2, binding.num3, binding.num4, binding.num5,
            binding.num6, binding.num7, binding.num8, binding.num9
        )
        numberButtons.forEach {
            val number = it.text.toString()
            it.setOnClickListener { model.numberInput(number) }
        }
    }
}
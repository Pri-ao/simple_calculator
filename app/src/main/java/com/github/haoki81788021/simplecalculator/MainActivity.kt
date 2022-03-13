package com.github.haoki81788021.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.github.haoki81788021.simplecalculator.databinding.ActivityMainBinding
import com.github.haoki81788021.simplecalculator.fragment.ResultDialog
import com.github.haoki81788021.simplecalculator.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model: MainViewModel by viewModels()

        // データ変化を検知
        setObserve(binding, model)
        // 各種ボタンのイベント設定
        setListener(binding, model)
    }

    private fun setObserve(binding: ActivityMainBinding, model: MainViewModel) {
        model.inputData.observe(this) {
            binding.inputResultArea.text = it
        }
        model.operator.observe(this) {
            binding.showOperator.text = it.rawValue
        }
        model.isCalculator.observe(this) {
            binding.equal.isEnabled = it
        }
        var toast: Toast? = null
        model.errorMessage.observe(this) {
            if (toast != null) {
                toast?.cancel()
            }
            toast = Toast.makeText(this, it, Toast.LENGTH_SHORT)
            toast?.show()
        }
    }

    private fun setListener(binding: ActivityMainBinding, model: MainViewModel) {
        // 数字ボタンのイベント設定
        val numberButtons = listOf(
            binding.num0, binding.num1, binding.num2, binding.num3, binding.num4, binding.num5,
            binding.num6, binding.num7, binding.num8, binding.num9
        )
        numberButtons.forEach {
            val number = it.text.toString()
            it.setOnClickListener { model.numberInput(number) }
        }

        // 演算子ボタンのイベント設定
        val operatorButtons = listOf(binding.div, binding.multi, binding.minus, binding.plus)
        operatorButtons.forEach {
            val operator = MainViewModel.Operator.get(it.text.toString()) ?: return
            it.setOnClickListener { model.setOperation(operator) }
        }

        binding.equal.setOnClickListener {
            model.calculator()
        }

        binding.clear.setOnClickListener {
            model.clear()
        }

        binding.details.setOnClickListener {
            val result = binding.inputResultArea.text.toString()
            ResultDialog.create(result).show(supportFragmentManager, "dialog")
        }
    }
}
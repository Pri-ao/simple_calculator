package com.github.haoki81788021.simplecalculator.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.haoki81788021.simplecalculator.R

class MainViewModel: ViewModel() {
    enum class Operator(val rawValue: String) {
        None(""), Plus("+"), Minus("-"), Multi("×"), Div("/");
        companion object {
            fun get(rawValue: String): Operator? {
                for (operator in values()) {
                    if (operator.rawValue == rawValue) {
                        return operator
                    }
                }
                return null
            }
        }
    }
    var inputData = MutableLiveData("0")
    var operator = MutableLiveData(Operator.None)
    var isCalculator = MutableLiveData(false)
    // StringResourceIdを渡すようにする
    var errorMessage: MutableLiveData<Int> = MutableLiveData()
    private var item1 = -1L
    private var isResult = false
    fun numberInput(number: String) {
        var prevNumber = inputData.value
        if (prevNumber == "0" || isResult) {
            prevNumber = ""
            isResult = false
        }
        val postNumber = prevNumber + number
        if (postNumber.length >= INPUT_LIMIT) {
            errorMessage.postValue(R.string.error_input_limit)
            return
        }
        inputData.postValue(postNumber)
        if (item1 >= 0 && postNumber != "0") {
            isCalculator.postValue(true)
        } else {
            isCalculator.postValue(false)
        }
    }

    fun setOperation(operator: Operator) {
        item1 = inputData.value?.toLong() ?: return
        this.inputData.postValue("0")
        this.operator.postValue(operator)
    }

    fun calculator() {
        val item2 = inputData.value?.toLong() ?: return
        var result = when (operator.value) {
            Operator.Plus -> {
                item1 + item2
            }
            Operator.Minus -> {
                item1 - item2
            }
            Operator.Multi -> {
                item1 * item2
            }
            Operator.Div -> {
                item1 / item2
            }
            else -> return
        }
        // オーバーフローしたなど計算結果がマイナスになったときは0に補正する
        if (result < 0) {
            errorMessage.postValue(R.string.error_calculator_minus)
            result = 0
        }
        inputData.postValue(result.toString())
        operator.postValue(Operator.None)
        isCalculator.postValue(false)
        isResult = true
    }

    fun clear() {
        item1 = -1
        inputData.postValue("0")
        operator.postValue(Operator.None)
        isCalculator.postValue(false)
    }

    companion object {
        const val INPUT_LIMIT = 16
    }
}
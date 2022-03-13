package com.github.haoki81788021.simplecalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
    private var item1 = -1L
    fun numberInput(number: String) {
        var prevNumber = inputData.value
        if (prevNumber == "0") {
            prevNumber = ""
        }
        val postNumber = prevNumber + number
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
        if (result < 0) {
            result = 0
        }
        inputData.postValue(result.toString())
        operator.postValue(Operator.None)
        isCalculator.postValue(false)
    }

    fun clear() {
        item1 = -1
        inputData.postValue("0")
        operator.postValue(Operator.None)
        isCalculator.postValue(false)
    }
}
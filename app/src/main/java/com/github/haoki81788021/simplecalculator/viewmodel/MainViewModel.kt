package com.github.haoki81788021.simplecalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    enum class Operator(val rawValue: String) {
        None(""), Plus("+"), Minus("-"), Multi("×"), Div("/");
        companion object {
            fun get(rawValue: String): Operator? {
                for (operator in Operator.values()) {
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
    private var item1 = 0
    fun numberInput(number: String) {
        var prevNumber = inputData.value
        if (prevNumber == "0") {
            prevNumber = ""
        }
        inputData.postValue(prevNumber + number)
    }

    fun setOperation(operator: Operator) {
        item1 = inputData.value?.toInt() ?: return
        this.inputData.postValue("0")
        this.operator.postValue(operator)
    }

    fun calculator() {

    }

    fun clear() {

    }
}
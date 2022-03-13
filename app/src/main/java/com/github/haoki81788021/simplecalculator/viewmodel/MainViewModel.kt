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

    fun numberInput(number: String) {
        inputData.postValue(number)
    }

    fun setOperation(operator: Operator) {
        this.operator.postValue(operator)
    }

    fun calculator() {

    }

    fun clear() {

    }
}
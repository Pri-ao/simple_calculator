package com.github.haoki81788021.simplecalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    enum class Operator(val rawValue: String) {
        None(""), Plus("+"), Minus("-"), Multi("*"), Div("/");
    }
    var inputData = MutableLiveData("0")
    var operator = Operator.None

    fun numberInput(number: String) {
        inputData.postValue(number)
    }

    fun setOperation() {

    }

    fun calculator() {

    }

    fun clear() {

    }
}
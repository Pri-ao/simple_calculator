package com.github.haoki81788021.simplecalculator.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.github.haoki81788021.simplecalculator.R

class ResultDialog: DialogFragment() {
    private var resultValue = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(RESULT_VALUE, "")?.let {
            resultValue = it
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireActivity())
            .setTitle(R.string.calc_result)
            .setMessage(resultValue)
        return dialog.create()
    }
    companion object {
        private const val RESULT_VALUE = "result_value"

        fun create(resultValue: String): ResultDialog {
            val args = Bundle().apply {
                putString(RESULT_VALUE, resultValue)
            }
            return ResultDialog().apply {
                arguments = args
            }
        }
    }
}
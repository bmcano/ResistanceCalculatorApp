package com.brandoncano.resistancecalculator.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel

/**
 * Job: ViewModel factory for the resistor ViewModels
 */
class ResistorViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass.simpleName) {
            "ResistorCtvViewModel" -> ResistorCtvViewModel(context) as T
            "ResistorVtcViewModel" -> ResistorVtcViewModel(context) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}

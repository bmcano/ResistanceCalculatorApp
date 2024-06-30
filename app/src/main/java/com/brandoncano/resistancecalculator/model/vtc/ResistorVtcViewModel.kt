package com.brandoncano.resistancecalculator.model.vtc

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Job: ViewModel for the value to color calculator
 */
class ResistorVtcViewModel(context: Context): ViewModel() {

    private val repository = ResistorVtcRepository.getInstance(context)
    private var resistor = MutableLiveData<ResistorVtc>()

    init {
        resistor.value = ResistorVtc()
    }

    override fun onCleared() {
        resistor.value = null
    }

    fun clear() {
        resistor.value = ResistorVtc()
        repository.clear()
    }

    fun getResistorLiveData(): LiveData<ResistorVtc> {
        resistor.value = repository.loadResistor()
        return resistor
    }

    fun getNavBarSelection(): Int {
        val resistor = repository.loadResistor()
        return resistor.numberOfBands - 3
    }

    fun saveNumberOfBands(number: Int) {
        val numberOfBands = number.coerceIn(3..6)
        repository.saveNumberOfBands(numberOfBands)
    }

    fun saveResistorValues(resistor: ResistorVtc) {
        repository.saveResistor(resistor)
    }
}

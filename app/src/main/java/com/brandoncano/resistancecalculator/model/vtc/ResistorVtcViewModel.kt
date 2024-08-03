package com.brandoncano.resistancecalculator.model.vtc

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResistorVtcViewModel(context: Context): ViewModel() {

    private val repository = ResistorVtcRepository.getInstance(context)
    private var resistor = MutableLiveData<ResistorVtc>().also {
        it.value = ResistorVtc()
    }

    override fun onCleared() {
        resistor.value = null
    }

    fun clear() {
        resistor.value = ResistorVtc(navBarSelection = getNavBarSelection())
        repository.clear()
    }

    fun getResistorLiveData(): LiveData<ResistorVtc> {
        resistor.value = repository.loadResistor()
        return resistor
    }

    fun updateValues(val1: String, val2: String, val3: String, val4: String) {
        resistor.value = resistor.value
            ?.copy(resistance = val1, units = val2, band5 = val3, band6 = val4)
    }

    fun getNavBarSelection(): Int {
        val resistor = repository.loadResistor()
        return resistor.navBarSelection
    }

    fun saveNavBarSelection(number: Int) {
        val navBarSelection = number.coerceIn(0..3)
        resistor.value = resistor.value?.copy(navBarSelection = navBarSelection)
        repository.saveNavBarSelection(navBarSelection)
    }

    fun saveResistorValues(resistor: ResistorVtc) {
        repository.saveResistor(resistor)
    }
}

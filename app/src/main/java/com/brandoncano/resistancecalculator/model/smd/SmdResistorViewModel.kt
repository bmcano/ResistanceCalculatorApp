package com.brandoncano.resistancecalculator.model.smd

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SmdResistorViewModel(context: Context): ViewModel() {

    private val repository = SmdResistorRepository.getInstance(context)
    private var resistor = MutableLiveData<SmdResistor>()

    init {
        resistor.value = SmdResistor()
    }

    override fun onCleared() {
        resistor.value = null
    }

    fun clear() {
        resistor.value = SmdResistor(navBarSelection = getNavBarSelection())
        repository.clear()
    }

    fun getResistorLiveData(): LiveData<SmdResistor> {
        resistor.value = repository.loadResistor()
        return resistor
    }

    fun updateCode(value: String) {
        resistor.value = resistor.value?.copy(code = value)
    }

    fun updateResistance(value: String) {
        resistor.value = resistor.value?.copy(resistance = value)
    }

    fun updateUnits(value: String) {
        resistor.value = resistor.value?.copy(units = value)
    }

    fun getNavBarSelection(): Int {
        val resistor = repository.loadResistor()
        return resistor.navBarSelection
    }

    fun saveNavBarSelection(number: Int) {
        val navBarSelection = number.coerceIn(0..2)
        resistor.value = resistor.value?.copy(navBarSelection = navBarSelection)
        repository.saveNavBarSelection(navBarSelection)
    }

    fun saveResistorValues(resistor: SmdResistor) {
        repository.saveResistor(resistor)
    }
}
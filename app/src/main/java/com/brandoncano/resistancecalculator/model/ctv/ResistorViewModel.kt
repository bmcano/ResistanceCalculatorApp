package com.brandoncano.resistancecalculator.model.ctv

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Job: ViewModel for the color to value calculator
 */
class ResistorViewModel(context: Context): ViewModel() {

    private val repository = ResistorRepository.getInstance(context)
    private var resistor = MutableLiveData<Resistor>()
    var resistance = ""

    init {
        resistor.value = Resistor()
    }

    override fun onCleared() {
        resistor.value = null
    }

    fun clear() {
        resistor.value = Resistor()
        repository.clear()
    }

    fun getResistorLiveData(): LiveData<Resistor> {
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

    fun saveResistorColors(resistor: Resistor) {
        repository.saveResistor(resistor)
    }

//    fun getResistance(): String {
//        // call function to calculate resistance text
//        return ""
//    }
}

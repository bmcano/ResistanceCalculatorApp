package com.brandoncano.resistancecalculator.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Job: ViewModel for the color to value calculator
 */
class ResistorViewModel(context: Context): ViewModel() {

    private val repository = ResistorRepository.getInstance(context)
    var resistor = Resistor()
    var resistance = ""

    override fun onCleared() {
        resistor.clear()
    }

    fun saveNumberOfBands(number: Int) {
        resistor.numberOfBands = number.coerceIn(3..6)
        repository.saveNumberOfBands(resistor.numberOfBands)
    }

    fun loadNumberOfBands(): Int {
        resistor.numberOfBands = repository.loadNumberOfBands()
        return resistor.numberOfBands
    }

    fun getNavBarSelection(): Int {
        return resistor.numberOfBands - 3
    }

    fun saveResistorColors(resistor: Resistor) {
        Log.e("BRANDON123", resistor.toString())
        repository.saveResistor(resistor)
    }

    fun loadResistorColors(): Resistor {
        resistor = repository.loadResistor()
        return resistor
    }

//    fun getResistance(): String {
//        // call function to calculate resistance text
//        return ""
//    }
}
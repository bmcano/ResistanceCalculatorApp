package com.brandoncano.resistancecalculator.model.ctv

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Job: ViewModel for the color to value calculator
 */
class ResistorCtvViewModel(context: Context): ViewModel() {

    private val repository = ResistorCtvRepository.getInstance(context)
    private var resistor = MutableLiveData<ResistorCtv>()
    var resistance = ""

    init {
        resistor.value = ResistorCtv()
    }

    override fun onCleared() {
        resistor.value = null
    }

    fun clear() {
        resistor.value = ResistorCtv(navBarSelection = getNavBarSelection())
        repository.clear()
    }

    fun getResistorLiveData(): LiveData<ResistorCtv> {
        resistor.value = repository.loadResistor()
        return resistor
    }

    fun getNavBarSelection(): Int {
        val resistor = repository.loadResistor()
        return resistor.navBarSelection
    }

    fun saveNavBarSelection(number: Int) {
        val navBarSelection = number.coerceIn(0..3)
        repository.saveNavBarSelection(navBarSelection)
    }

    fun saveResistorColors(resistor: ResistorCtv) {
        repository.saveResistor(resistor)
    }
}

package com.brandoncano.resistancecalculator.model.ctv

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResistorCtvViewModel(context: Context): ViewModel() {

    private val repository = ResistorCtvRepository.getInstance(context)
    private var resistor = MutableLiveData<ResistorCtv>().also {
        it.value = ResistorCtv()
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

    fun updateBands(b1: String, b2: String, b3: String, b4: String, b5: String, b6: String) {
        resistor.value = resistor.value
            ?.copy(band1 = b1, band2 = b2, band3 = b3, band4 = b4, band5 = b5, band6 = b6)
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

    fun saveResistorColors(resistorCtv: ResistorCtv) {
        repository.saveResistor(resistorCtv)
    }
}

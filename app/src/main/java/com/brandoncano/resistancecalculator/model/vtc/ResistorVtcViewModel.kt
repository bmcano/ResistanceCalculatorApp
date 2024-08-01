package com.brandoncano.resistancecalculator.model.vtc

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.components.BandKey

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
        resistor.value = ResistorVtc(navBarSelection = getNavBarSelection())
        repository.clear()
    }

    fun getResistorLiveData(): LiveData<ResistorVtc> {
        resistor.value = repository.loadResistor()
        return resistor
    }

    fun updateBand(bandKey: BandKey, value: String) {
        resistor.value = when (bandKey) {
            BandKey.Band5 -> resistor.value?.copy(band5 = value)
            BandKey.Band6 -> resistor.value?.copy(band6 = value)
            else -> resistor.value
        }
    }

    fun updateUnits(value: String) {
        resistor.value = resistor.value?.copy(units = value)
    }

    fun updateResistance(value: String) {
        resistor.value = resistor.value?.copy(resistance = value)
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

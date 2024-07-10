package com.brandoncano.resistancecalculator.model.ctv

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brandoncano.resistancecalculator.components.BandKey

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

    fun updateBand(bandKey: BandKey, value: String) {
        resistor.value = when (bandKey) {
            BandKey.Band1 -> resistor.value?.copy(band1 = value)
            BandKey.Band2 -> resistor.value?.copy(band2 = value)
            BandKey.Band3 -> resistor.value?.copy(band3 = value)
            BandKey.Band4 -> resistor.value?.copy(band4 = value)
            BandKey.Band5 -> resistor.value?.copy(band5 = value)
            BandKey.Band6 -> resistor.value?.copy(band6 = value)
        }
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

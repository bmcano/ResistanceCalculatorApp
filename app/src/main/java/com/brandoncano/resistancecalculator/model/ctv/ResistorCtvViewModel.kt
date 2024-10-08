package com.brandoncano.resistancecalculator.model.ctv

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResistorCtvViewModel(context: Context): ViewModel() {

    private val repository = ResistorCtvRepository.getInstance(context)

    private val _resistor = MutableStateFlow(ResistorCtv())
    val resistor: StateFlow<ResistorCtv> get() = _resistor

    private val _navBarSelection = MutableStateFlow(1)
    val navBarSelection: StateFlow<Int> get() = _navBarSelection

    init {
        viewModelScope.launch {
            val loadedResistor = repository.loadResistor()
            _resistor.value = loadedResistor
            _navBarSelection.value = loadedResistor.navBarSelection
        }
    }

    fun clear() {
        _resistor.value = ResistorCtv(navBarSelection = _navBarSelection.value)
        repository.clear()
    }

    fun updateBand(bandNumber: Int, color: String) {
        _resistor.value = when (bandNumber) {
            1 -> _resistor.value.copy(band1 = color)
            2 -> _resistor.value.copy(band2 = color)
            3 -> _resistor.value.copy(band3 = color)
            4 -> _resistor.value.copy(band4 = color)
            5 -> _resistor.value.copy(band5 = color)
            6 -> _resistor.value.copy(band6 = color)
            else -> _resistor.value
        }
        repository.saveResistor(_resistor.value)
    }

    fun saveNavBarSelection(number: Int) {
        val navBarSelection = number.coerceIn(0..3)
        _navBarSelection.value = navBarSelection
        _resistor.value = _resistor.value.copy(navBarSelection = navBarSelection)
        repository.saveNavBarSelection(navBarSelection)
    }
}

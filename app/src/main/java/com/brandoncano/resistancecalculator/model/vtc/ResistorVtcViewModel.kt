package com.brandoncano.resistancecalculator.model.vtc

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brandoncano.resistancecalculator.util.formatResistor
import com.brandoncano.resistancecalculator.util.isInputInvalid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResistorVtcViewModel(context: Context) : ViewModel() {

    private val repository = ResistorVtcRepository.getInstance(context)

    private val _resistor = MutableStateFlow(ResistorVtc())
    val resistor: StateFlow<ResistorVtc> get() = _resistor

    private val _navBarSelection = MutableStateFlow(1)
    val navBarSelection: StateFlow<Int> get() = _navBarSelection

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    init {
        viewModelScope.launch {
            val loadedResistor = repository.loadResistor()
            _resistor.value = loadedResistor
            _navBarSelection.value = loadedResistor.navBarSelection
            updateErrorState()
        }
    }

    fun clear() {
        _resistor.value = ResistorVtc(navBarSelection = _navBarSelection.value)
        _isError.value = false
        repository.clear()
    }

    fun updateValues(resistance: String, units: String, band5: String, band6: String) {
        _resistor.value = _resistor.value.copy(
            resistance = resistance,
            units = units,
            band5 = band5,
            band6 = band6
        )
        updateErrorState()
        if (!_isError.value) {
            _resistor.value.formatResistor()
            saveResistorValues()
        }
    }

    fun saveNavBarSelection(number: Int) {
        val navBarSelection = number.coerceIn(0..3)
        _navBarSelection.value = navBarSelection
        _resistor.value = _resistor.value.copy(navBarSelection = navBarSelection)
        updateErrorState()
        if (!_isError.value) {
            _resistor.value.formatResistor()
            saveResistorValues()
        }
        repository.saveNavBarSelection(navBarSelection)
    }

    private fun updateErrorState() {
        _isError.value = _resistor.value.isInputInvalid()
    }

    private fun saveResistorValues() {
        repository.saveResistor(_resistor.value)
    }
}

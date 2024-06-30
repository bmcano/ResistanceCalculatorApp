package com.brandoncano.resistancecalculator.model.vtc

import android.content.Context
import com.brandoncano.resistancecalculator.components.StateData

/**
 * Job: Repository to the value to color resistor model, handles shared preferences.
 */
class ResistorVtcRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: ResistorVtcRepository? = null
        fun getInstance(context: Context): ResistorVtcRepository = instance
            ?: synchronized(this) {
                ResistorVtcRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        StateData.USER_INPUT_VTC.clearData(application)
        StateData.UNITS_DROPDOWN_VTC.clearData(application)
        StateData.TOLERANCE_DROPDOWN_VTC.clearData(application)
        StateData.PPM_DROPDOWN_VTC.clearData(application)
        StateData.RESISTANCE_VTC.clearData(application)
    }

    fun loadResistor(): ResistorVtc {
        val input = StateData.USER_INPUT_VTC.loadData(application)
        val units = StateData.UNITS_DROPDOWN_VTC.loadData(application)
        val band5 = StateData.TOLERANCE_DROPDOWN_VTC.loadData(application)
        val band6 = StateData.PPM_DROPDOWN_VTC.loadData(application)
//        val resistance = StateData.RESISTANCE_VTC.loadData(application) // TODO - check if we need this
        val number = StateData.BUTTON_SELECTION_VTC.loadData(application)
        return ResistorVtc(input, units, band5, band6, number.toIntOrNull() ?: 4)
    }

    fun saveResistor(resistor: ResistorVtc) {
        StateData.USER_INPUT_VTC.saveData(application, resistor.resistance)
        StateData.UNITS_DROPDOWN_VTC.saveData(application, resistor.units)
        StateData.TOLERANCE_DROPDOWN_VTC.saveData(application, resistor.band5)
        StateData.PPM_DROPDOWN_VTC.saveData(application, resistor.band6)
    }

    fun saveNumberOfBands(number: Int) {
        StateData.BUTTON_SELECTION_VTC.saveData(application, "$number")
    }
}
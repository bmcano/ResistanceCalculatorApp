package com.brandoncano.resistancecalculator.model.vtc

import android.content.Context
import com.brandoncano.resistancecalculator.components.SharedPreferences

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
        SharedPreferences.USER_INPUT_VTC.clearData(application)
        SharedPreferences.UNITS_DROPDOWN_VTC.clearData(application)
        SharedPreferences.TOLERANCE_DROPDOWN_VTC.clearData(application)
        SharedPreferences.PPM_DROPDOWN_VTC.clearData(application)
    }

    fun loadResistor(): ResistorVtc {
        val input = SharedPreferences.USER_INPUT_VTC.loadData(application)
        val units = SharedPreferences.UNITS_DROPDOWN_VTC.loadData(application)
        val band5 = SharedPreferences.TOLERANCE_DROPDOWN_VTC.loadData(application)
        val band6 = SharedPreferences.PPM_DROPDOWN_VTC.loadData(application)
        val number = SharedPreferences.NAVBAR_SELECTION_VTC.loadData(application)
        return ResistorVtc(input, units, band5, band6, number.toIntOrNull() ?: 1)
    }

    fun saveResistor(resistor: ResistorVtc) {
        SharedPreferences.USER_INPUT_VTC.saveData(application, resistor.resistance)
        SharedPreferences.UNITS_DROPDOWN_VTC.saveData(application, resistor.units)
        SharedPreferences.TOLERANCE_DROPDOWN_VTC.saveData(application, resistor.band5)
        SharedPreferences.PPM_DROPDOWN_VTC.saveData(application, resistor.band6)
    }

    fun saveNavBarSelection(number: Int) {
        SharedPreferences.NAVBAR_SELECTION_VTC.saveData(application, "$number")
    }
}
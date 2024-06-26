package com.brandoncano.resistancecalculator.model

import android.content.Context
import com.brandoncano.resistancecalculator.components.StateData

class ResistorRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: ResistorRepository? = null
        fun getInstance(context: Context): ResistorRepository = instance
            ?: synchronized(this) {
                ResistorRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        StateData.SIGFIG_BAND_ONE_CTV.clearData(application)
        StateData.SIGFIG_BAND_TWO_CTV.clearData(application)
        StateData.SIGFIG_BAND_THREE_CTV.clearData(application)
        StateData.MULTIPLIER_BAND_CTV.clearData(application)
        StateData.TOLERANCE_BAND_CTV.clearData(application)
        StateData.PPM_BAND_CTV.clearData(application)
        StateData.RESISTANCE_CTV.clearData(application)
    }

    fun saveNumberOfBands(number: Int) {
        StateData.BUTTON_SELECTION_CTV.saveData(application, "$number")
    }

    fun loadNumberOfBands(): Int {
        val number = StateData.BUTTON_SELECTION_CTV.loadData(application)
        return number.toIntOrNull() ?: 4
    }

    fun saveResistor(resistor: Resistor) {
        StateData.SIGFIG_BAND_ONE_CTV.saveData(application, resistor.band1)
        StateData.SIGFIG_BAND_TWO_CTV.saveData(application, resistor.band2)
        StateData.SIGFIG_BAND_THREE_CTV.saveData(application, resistor.band3)
        StateData.MULTIPLIER_BAND_CTV.saveData(application, resistor.band4)
        StateData.TOLERANCE_BAND_CTV.saveData(application, resistor.band5)
        StateData.PPM_BAND_CTV.saveData(application, resistor.band6)
    }

    fun loadResistor(): Resistor {
        val band1 = StateData.SIGFIG_BAND_ONE_CTV.loadData(application)
        val band2 = StateData.SIGFIG_BAND_TWO_CTV.loadData(application)
        val band3 = StateData.SIGFIG_BAND_THREE_CTV.loadData(application)
        val band4 = StateData.MULTIPLIER_BAND_CTV.loadData(application)
        val band5 = StateData.TOLERANCE_BAND_CTV.loadData(application)
        val band6 = StateData.PPM_BAND_CTV.loadData(application)
        return Resistor(band1, band2, band3, band4, band5, band6)
    }
}
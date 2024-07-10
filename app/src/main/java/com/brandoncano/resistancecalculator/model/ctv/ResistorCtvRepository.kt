package com.brandoncano.resistancecalculator.model.ctv

import android.content.Context
import com.brandoncano.resistancecalculator.components.SharedPreferences

/**
 * Job: Repository to the color to value resistor model, handles shared preferences.
 */
class ResistorCtvRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: ResistorCtvRepository? = null
        fun getInstance(context: Context): ResistorCtvRepository = instance
            ?: synchronized(this) {
                ResistorCtvRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        SharedPreferences.SIGFIG_BAND_ONE_CTV.clearData(application)
        SharedPreferences.SIGFIG_BAND_TWO_CTV.clearData(application)
        SharedPreferences.SIGFIG_BAND_THREE_CTV.clearData(application)
        SharedPreferences.MULTIPLIER_BAND_CTV.clearData(application)
        SharedPreferences.TOLERANCE_BAND_CTV.clearData(application)
        SharedPreferences.PPM_BAND_CTV.clearData(application)
    }

    fun loadResistor(): ResistorCtv {
        val band1 = SharedPreferences.SIGFIG_BAND_ONE_CTV.loadData(application)
        val band2 = SharedPreferences.SIGFIG_BAND_TWO_CTV.loadData(application)
        val band3 = SharedPreferences.SIGFIG_BAND_THREE_CTV.loadData(application)
        val band4 = SharedPreferences.MULTIPLIER_BAND_CTV.loadData(application)
        val band5 = SharedPreferences.TOLERANCE_BAND_CTV.loadData(application)
        val band6 = SharedPreferences.PPM_BAND_CTV.loadData(application)
        val number = SharedPreferences.NAVBAR_SELECTION_CTV.loadData(application)
        return ResistorCtv(band1, band2, band3, band4, band5, band6, number.toIntOrNull() ?: 1)
    }

    fun saveResistor(resistor: ResistorCtv) {
        SharedPreferences.SIGFIG_BAND_ONE_CTV.saveData(application, resistor.band1)
        SharedPreferences.SIGFIG_BAND_TWO_CTV.saveData(application, resistor.band2)
        SharedPreferences.SIGFIG_BAND_THREE_CTV.saveData(application, resistor.band3)
        SharedPreferences.MULTIPLIER_BAND_CTV.saveData(application, resistor.band4)
        SharedPreferences.TOLERANCE_BAND_CTV.saveData(application, resistor.band5)
        SharedPreferences.PPM_BAND_CTV.saveData(application, resistor.band6)
    }

    fun saveNavBarSelection(number: Int) {
        SharedPreferences.NAVBAR_SELECTION_CTV.saveData(application, "$number")
    }
}

package com.brandoncano.resistancecalculator.model.smd

import android.content.Context
import com.brandoncano.resistancecalculator.components.SharedPreferences

class SmdResistorRepository(context: Context) {

    private val application = context

    companion object {
        private var instance: SmdResistorRepository? = null
        fun getInstance(context: Context): SmdResistorRepository = instance
            ?: synchronized(this) {
                SmdResistorRepository(context).also {
                    instance = it
                }
            }
    }

    fun clear() {
        SharedPreferences.CODE_INPUT_SMD.clearData(application)
        SharedPreferences.RESISTANCE_INPUT_SMD.clearData(application)
        SharedPreferences.UNITS_DROPDOWN_SMD.clearData(application)
    }

    fun loadResistor(): SmdResistor {
        val code = SharedPreferences.CODE_INPUT_SMD.loadData(application)
        val resistance = SharedPreferences.RESISTANCE_INPUT_SMD.loadData(application)
        val units = SharedPreferences.UNITS_DROPDOWN_SMD.loadData(application)
        val number = SharedPreferences.NAVBAR_SELECTION_SMD.loadData(application)
        return SmdResistor(code, resistance, units, number.toIntOrNull() ?: 0)
    }

    fun saveResistor(resistor: SmdResistor) {
        SharedPreferences.CODE_INPUT_SMD.saveData(application, resistor.code)
        SharedPreferences.RESISTANCE_INPUT_SMD.saveData(application, resistor.resistance)
        SharedPreferences.UNITS_DROPDOWN_SMD.saveData(application, resistor.units)
    }

    fun saveNavBarSelection(number: Int) {
        SharedPreferences.NAVBAR_SELECTION_SMD.saveData(application, "$number")
    }
}
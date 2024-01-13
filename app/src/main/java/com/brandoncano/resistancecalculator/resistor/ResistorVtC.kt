package com.brandoncano.resistancecalculator.resistor

import android.content.Context
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.constants.Symbols

class ResistorVtC(val context: Context) {
    var userInput = ""
    var resistance = ""
    var units = ""
    var toleranceValue = ""
    var ppmValue = ""
    var numberOfBands = 4 // this is the default value used since its the most common
    // misc band values needed
    var sigFigBandOne = ""
    var sigFigBandTwo = ""
    var sigFigBandThree = ""
    var multiplierBand = ""

    fun loadData() {
        userInput = StateData.USER_INPUT_VTC.loadData(context)
        units = StateData.UNITS_DROPDOWN_VTC.loadData(context)
        toleranceValue = StateData.TOLERANCE_DROPDOWN_VTC.loadData(context)
        ppmValue = StateData.PPM_DROPDOWN_VTC.loadData(context)
        resistance = StateData.RESISTANCE_VTC.loadData(context)
        if (resistance.isEmpty()) {
            resistance = context.getString(R.string.enter_value)
        }
    }

    fun loadNumberOfBands(): String {
        return StateData.BUTTON_SELECTION_VTC.loadData(context)
    }

    fun clear() {
        StateData.USER_INPUT_VTC.clearData(context)
        StateData.UNITS_DROPDOWN_VTC.clearData(context)
        StateData.TOLERANCE_DROPDOWN_VTC.clearData(context)
        StateData.PPM_DROPDOWN_VTC.clearData(context)
        StateData.RESISTANCE_VTC.clearData(context)
        loadData() // after clearing we want to reload the blank data
    }

    fun updateUserInput(input: String) {
        StateData.USER_INPUT_VTC.saveData(context, input)
    }

    fun updateResistance(resistance: String) {
        this.resistance = resistance
        StateData.RESISTANCE_VTC.saveData(context, resistance)
    }

    fun updateNumberOfBands(number: Int) {
        numberOfBands = number.coerceIn(3..6)
        StateData.BUTTON_SELECTION_VTC.saveData(context, "$numberOfBands")
    }

    fun getResistanceText(): String {
        var text = "$userInput $units "
        text += if (numberOfBands == 3) "${Symbols.PM}20%" else toleranceValue
        if (numberOfBands == 6) text += "\n$ppmValue".trimEnd('\n')
        return text
    }

    fun isThreeBand() = numberOfBands == 3
    fun isThreeFourBand() = numberOfBands == 3 || numberOfBands == 4
    fun isSixBand() = numberOfBands == 6
}
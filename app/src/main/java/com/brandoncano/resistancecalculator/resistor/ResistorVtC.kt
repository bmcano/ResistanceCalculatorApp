package com.brandoncano.resistancecalculator.resistor

import android.content.Context
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.util.ColorFinder

/**
 * Job: Holds the implementation of the resistor for VtC.
 */
class ResistorVtC(val context: Context) : Resistor() {
    var userInput = ""
    var units = ""
    var toleranceValue = ""
    var ppmValue = ""

    override fun loadData() {
        userInput = StateData.USER_INPUT_VTC.loadData(context)
        units = StateData.UNITS_DROPDOWN_VTC.loadData(context)
        toleranceValue = StateData.TOLERANCE_DROPDOWN_VTC.loadData(context)
        ppmValue = StateData.PPM_DROPDOWN_VTC.loadData(context)
        resistance = StateData.RESISTANCE_VTC.loadData(context)
        if (resistance.isEmpty()) {
            resistance = context.getString(R.string.enter_value)
        }
    }

    override fun loadNumberOfBands(): Int {
        numberOfBands = StateData.BUTTON_SELECTION_VTC.loadData(context).toIntOrNull() ?: 4
        return numberOfBands
    }

    override fun clear() {
        StateData.USER_INPUT_VTC.clearData(context)
        StateData.UNITS_DROPDOWN_VTC.clearData(context)
        StateData.TOLERANCE_DROPDOWN_VTC.clearData(context)
        StateData.PPM_DROPDOWN_VTC.clearData(context)
        StateData.RESISTANCE_VTC.clearData(context)
        loadData() // after clearing we want to reload the blank data
    }

    override fun isEmpty(): Boolean {
        return resistance == "NotValid" || resistance.isEmpty() || units.isEmpty()
    }

    override fun saveResistance(resistance: String) {
        this.resistance = resistance
        StateData.RESISTANCE_VTC.saveData(context, resistance)
    }

    override fun saveNumberOfBands(number: Int) {
        numberOfBands = number.coerceIn(3..6)
        StateData.BUTTON_SELECTION_VTC.saveData(context, "$numberOfBands")
    }

    override fun saveDropdownSelections() {
        StateData.UNITS_DROPDOWN_VTC.saveData(context, units)
        StateData.TOLERANCE_DROPDOWN_VTC.saveData(context, toleranceValue)
        StateData.PPM_DROPDOWN_VTC.saveData(context, ppmValue)
    }

    override fun toString(): String {
        toleranceBand = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(toleranceValue))
        ppmBand = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(ppmValue))
        return when (numberOfBands) {
            4 -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand, $toleranceBand ]"
            5 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand ]"
            6 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand ]"
        }
    }

    fun saveUserInput(input: String) {
        StateData.USER_INPUT_VTC.saveData(context, input)
    }

    fun getResistanceText(): String {
        var text = "$userInput $units "
        text += if (numberOfBands == 3) "${Symbols.PM}20%" else toleranceValue
        if (numberOfBands == 6) text += "\n$ppmValue".trimEnd('\n')
        return text
    }
}
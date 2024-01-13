package com.brandoncano.resistancecalculator.resistor

import android.content.Context
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.StateData

/**
 * Job: Holds the implementation of the resistor for CtV.
 */
class ResistorCtV(val context: Context) : Resistor() {
    var toleranceBand = ""
    var ppmBand = ""

    override fun loadData() {
        sigFigBandOne = StateData.SIGFIG_BAND_ONE_CTV.loadData(context)
        sigFigBandTwo = StateData.SIGFIG_BAND_TWO_CTV.loadData(context)
        sigFigBandThree = StateData.SIGFIG_BAND_THREE_CTV.loadData(context)
        multiplierBand = StateData.MULTIPLIER_BAND_CTV.loadData(context)
        toleranceBand = StateData.TOLERANCE_BAND_CTV.loadData(context)
        ppmBand = StateData.PPM_BAND_CTV.loadData(context)
        resistance = StateData.RESISTANCE_CTV.loadData(context)
        if (resistance.isEmpty()) {
            resistance = context.getString(R.string.default_text)
        }
    }

    override fun loadNumberOfBands(): String {
        return StateData.BUTTON_SELECTION_CTV.loadData(context)
    }

    override fun clear() {
        StateData.SIGFIG_BAND_ONE_CTV.clearData(context)
        StateData.SIGFIG_BAND_TWO_CTV.clearData(context)
        StateData.SIGFIG_BAND_THREE_CTV.clearData(context)
        StateData.MULTIPLIER_BAND_CTV.clearData(context)
        StateData.TOLERANCE_BAND_CTV.clearData(context)
        StateData.PPM_BAND_CTV.clearData(context)
        StateData.RESISTANCE_CTV.clearData(context)
        loadData() // after clearing we want to reload the blank data
    }

    override fun isEmpty(): Boolean {
        val isMissingBands = sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || multiplierBand.isEmpty()
        return (isThreeFourBand() && isMissingBands) || (!isThreeFourBand() && (isMissingBands || sigFigBandThree.isEmpty()))
    }

    override fun saveResistance(resistance: String) {
        this.resistance = resistance
        StateData.RESISTANCE_CTV.saveData(context, resistance)
    }

    override fun saveNumberOfBands(number: Int) {
        numberOfBands = number.coerceIn(3..6)
        StateData.BUTTON_SELECTION_CTV.saveData(context, "$numberOfBands")
    }

    override fun saveDropdownSelections() {
        StateData.SIGFIG_BAND_ONE_CTV.saveData(context, sigFigBandOne)
        StateData.SIGFIG_BAND_TWO_CTV.saveData(context, sigFigBandTwo)
        StateData.SIGFIG_BAND_THREE_CTV.saveData(context, sigFigBandThree)
        StateData.MULTIPLIER_BAND_CTV.saveData(context, multiplierBand)
        StateData.TOLERANCE_BAND_CTV.saveData(context, toleranceBand)
        StateData.PPM_BAND_CTV.saveData(context, ppmBand)
    }

    override fun toString(): String {
        return when (numberOfBands) {
            4 -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand, $toleranceBand ]"
            5 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand ]"
            6 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand ]"
        }
    }
}

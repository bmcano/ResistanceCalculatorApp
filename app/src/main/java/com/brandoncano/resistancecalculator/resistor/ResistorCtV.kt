package com.brandoncano.resistancecalculator.resistor

import android.content.Context
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.constants.Colors

class ResistorCtV(
    var sigFigBandOne: String = "",
    var sigFigBandTwo: String = "",
    var sigFigBandThree: String = "",
    var multiplierBand: String = "",
    var toleranceBand: String = "",
    var ppmBand: String = ""
) {
    var numberOfBands = 4 // this is the default value used since its the most common
    var resistance = ""

    fun loadData(context: Context) {
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

    fun loadNumberOfBands(context: Context): String {
        return StateData.BUTTON_SELECTION_CTV.loadData(context)
    }

    fun clear(context: Context) {
        StateData.SIGFIG_BAND_ONE_CTV.clearData(context)
        StateData.SIGFIG_BAND_TWO_CTV.clearData(context)
        StateData.SIGFIG_BAND_THREE_CTV.clearData(context)
        StateData.MULTIPLIER_BAND_CTV.clearData(context)
        StateData.TOLERANCE_BAND_CTV.clearData(context)
        StateData.PPM_BAND_CTV.clearData(context)
        StateData.RESISTANCE_CTV.clearData(context)
        loadData(context) // after clearing we want to reload the blank data
    }

    fun updateNumberOfBands(context: Context, number: Int) {
        numberOfBands = number.coerceIn(3..6)
        StateData.BUTTON_SELECTION_CTV.saveData(context, "$numberOfBands")
    }

    fun updateResistance(resistance: String, context: Context) {
        this.resistance = resistance
        StateData.RESISTANCE_CTV.saveData(context, resistance)
    }

    fun isThreeBand() = numberOfBands == 3
    fun isThreeFourBand() = numberOfBands == 3 || numberOfBands == 4
    fun isSixBand() = numberOfBands == 6

    override fun toString(): String {
        return when (numberOfBands) {
            4 -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand, $toleranceBand ]"
            5 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand ]"
            6 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand ]"
        }
    }

    fun isEmpty(): Boolean {
        val isMissingBands = sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || multiplierBand.isEmpty()
        return (isThreeFourBand() && isMissingBands) || (!isThreeFourBand() && (isMissingBands || sigFigBandThree.isEmpty()))
    }

    fun isFirstDigitZero() = sigFigBandOne == Colors.BLACK
}
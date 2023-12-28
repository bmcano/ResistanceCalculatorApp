package com.brandoncano.resistancecalculator.resistor

import android.content.Context
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.constants.Colors.BLACK
import com.brandoncano.resistancecalculator.constants.Symbols.PM
import com.brandoncano.resistancecalculator.util.ColorFinder

/**
 * Job: Holds all information and logic related to the resistor for both CtV and VtC.
 *
 * Notes:
 *   The data values in the parameters are the 6 bands of the resistor (CtV).
 *   The number of bands will determine which resistor is selected, the public attributes not in
 *   the parameters are used for the VtC section.
 */
data class Resistor(
    var sigFigBandOne:   String = "",
    var sigFigBandTwo:   String = "",
    var sigFigBandThree: String = "",
    var multiplierBand:  String = "",
    var toleranceBand:   String = "",
    var ppmBand:         String = ""
) {
    var resistance = ""
    var units = ""
    var toleranceValue = ""
    var ppmValue = ""

    private var numberOfBands = 4 // this is the default value used since its the most common

    fun loadData(context: Context) {
        sigFigBandOne = StateData.SIGFIG_BAND_ONE_CTV.loadData(context)
        sigFigBandTwo = StateData.SIGFIG_BAND_TWO_CTV.loadData(context)
        sigFigBandThree = StateData.SIGFIG_BAND_THREE_CTV.loadData(context)
        multiplierBand = StateData.MULTIPLIER_BAND_CTV.loadData(context)
        toleranceBand = StateData.TOLERANCE_BAND_CTV.loadData(context)
        ppmBand = StateData.PPM_BAND_CTV.loadData(context)
    }

    fun toColorBandString(isVtC: Boolean = false): String {
        if (isVtC) {
            toleranceBand = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(toleranceValue))
            ppmBand = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(ppmValue))
        }

        return when (numberOfBands) {
            4 -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand, $toleranceBand ]"
            5 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand ]"
            6 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand ]"
        }
    }

    fun getResistanceText(): String {
        var text = "$resistance $units "
        text += if (numberOfBands == 3) "${PM}20%" else toleranceValue
        if (numberOfBands == 6) text += "\n$ppmValue".trimEnd('\n')
        return text
    }

    fun getNumberOfBands(): Int = numberOfBands

    fun setNumberOfBands(number: Int) {
        numberOfBands = number.coerceIn(3..6)
    }

    fun isThreeBand() = numberOfBands == 3
    fun isThreeFourBand() = numberOfBands == 3 || numberOfBands == 4
    fun isSixBand() = numberOfBands == 6

    fun isEmpty(): Boolean {
        val isMissingBands = sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || multiplierBand.isEmpty()
        return (isThreeFourBand() && isMissingBands) || (!isThreeFourBand() && (isMissingBands || sigFigBandThree.isEmpty()))
    }

    fun isFirstDigitZero() = sigFigBandOne == BLACK

    fun clear() {
        sigFigBandOne = ""
        sigFigBandTwo = ""
        sigFigBandThree = ""
        multiplierBand = ""
        toleranceBand = ""
        ppmBand = ""
    }
}

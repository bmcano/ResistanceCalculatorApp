package com.brandoncano.resistancecalculator.resistor

import com.brandoncano.resistancecalculator.constants.Colors.BLACK
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

    private var numberOfBands = 4

    fun toColorBandString(isVtC: Boolean = false): String {
        if (isVtC) {
            toleranceBand = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(toleranceValue))
            ppmBand = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(ppmValue))
        }

        return when (numberOfBands) {
            4 -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand, $toleranceBand ]"
            5 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand ]"
            6 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> ""
        }
    }

    fun getResistanceText(): String {
        var text = "$resistance $units $toleranceValue"
        if (numberOfBands == 6) text += "\n$ppmValue".trimEnd('\n')
        return text
    }

    fun getNumberOfBands(): Int = numberOfBands

    fun setNumberOfBands(number: Int) {
        numberOfBands = number.coerceIn(4..6)
    }

    fun isEmpty(): Boolean {
        val isMissingBands = sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || multiplierBand.isEmpty() || toleranceBand.isEmpty()
        return (numberOfBands == 4 && isMissingBands) || (numberOfBands != 4 && (isMissingBands || sigFigBandThree.isEmpty()))
    }

    fun allDigitsZero(): Boolean {
        val fourBand = numberOfBands == 4 && sigFigBandOne == BLACK && sigFigBandTwo == BLACK
        val fiveBand = sigFigBandOne == BLACK && sigFigBandTwo == BLACK && sigFigBandThree == BLACK
        return fourBand || fiveBand
    }

    fun clear() {
        sigFigBandOne = ""
        sigFigBandTwo = ""
        sigFigBandThree = ""
        multiplierBand = ""
    }
}

package com.brandoncano.resistancecalculator.components

import com.brandoncano.resistancecalculator.constants.BLACK
import com.brandoncano.resistancecalculator.util.ColorFinder

/**
 * Job: Holds the colors for each band and performs certain task with the info.
 *
 * Notes:
 *   The data values in the parameters are the 6 bands of the resistor.
 *   The number of bands will determine which resistor is selected, the public attributes not in
 *   the parameters are specifically set for the value-to-color section.
 */
data class Resistor(
    // color to value specific attributes
    var sigFigBandOne: String = "",
    var sigFigBandTwo: String = "",
    var sigFigBandThree: String = "",
    var multiplierBand: String = "",
    var toleranceBand: String = "",
    var ppmBand: String = ""
) {
    // value to color specific attributes
    var resistance: String = ""
    var units: String = ""
    var toleranceValue: String = ""
    var ppmValue: String = ""

    private var numberOfBands: Int = 4

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
        return when (numberOfBands) {
            4, 5 -> "$resistance $units $toleranceValue"
            6 -> "$resistance $units $toleranceValue\n$ppmValue".trimEnd('\n')
            else -> ""
        }
    }

    fun getNumberOfBands(): Int {
        return numberOfBands
    }

    fun setNumberOfBands(number: Int) {
        numberOfBands = number
        if (number != 4 && number != 5 && number != 6) {
            numberOfBands = 4 // only allows values of 4, 5, or 6
        }
    }

    fun isEmpty(): Boolean {
        return if (numberOfBands == 4) {
            sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || multiplierBand.isEmpty() || toleranceBand.isEmpty()
        } else {
            sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || sigFigBandThree.isEmpty() || multiplierBand.isEmpty() || toleranceBand.isEmpty()
        }
    }

    fun allDigitsZero(): Boolean {
        val fourBand = numberOfBands == 4 && sigFigBandOne == BLACK && sigFigBandTwo == BLACK
        val fiveBand = sigFigBandOne == BLACK && sigFigBandTwo == BLACK && sigFigBandThree == BLACK
        return fourBand || fiveBand
    }
}

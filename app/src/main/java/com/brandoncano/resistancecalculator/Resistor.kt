package com.brandoncano.resistancecalculator

/**
 * @author Brandon
 *
 * Job: Holds the colors for each band and performs certain task with the info
 *
 * Notes: the data values in the parameters are the 6 bands of the resistor.
 *  the number of bands will determine which resistor is selected, the resistance and units is
 *  specifically for the value-to-color section
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

    override fun toString(): String {
        return when (numberOfBands) {
            4 -> "[ $sigFigBandOne, $sigFigBandTwo, $multiplierBand, $toleranceBand ]"
            5 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree $multiplierBand, $toleranceBand ]"
            6 -> "[ $sigFigBandOne, $sigFigBandTwo, $sigFigBandThree, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> ""
        }
    }

    fun getNumberOfBands(): Int {
        return numberOfBands
    }

    fun setNumberOfBands(number: Int) {
        numberOfBands = number
        if (number != 4 && number != 5 && number != 6) {
            numberOfBands = 4
        }
    }

    fun isEmpty(numberOfBands: Int = 4): Boolean {
        return if (numberOfBands == 4) {
            sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || multiplierBand.isEmpty() || toleranceBand.isEmpty()
        } else {
            sigFigBandOne.isEmpty() || sigFigBandTwo.isEmpty() || sigFigBandThree.isEmpty() || multiplierBand.isEmpty() || toleranceBand.isEmpty()
        }
    }

    fun allDigitsZero(numberOfBands: Int = 4): Boolean {
        if (numberOfBands == 4 && sigFigBandOne == "Black" && sigFigBandTwo == "Black") {
            return true
        }
        if (sigFigBandOne == "Black" && sigFigBandTwo == "Black" && sigFigBandThree == "Black") {
            return true
        }
        return false
    }
}

package com.brandoncano.resistancecalculator

/**
 * @author Brandon
 *
 * Job: Holds the colors for each band and performs certain task with the info
 */
data class Resistor(
    var sigFigBandOne: String = "",
    var sigFigBandTwo: String = "",
    var sigFigBandThree: String = "",
    var multiplierBand: String = "",
    var toleranceBand: String = "",
    var ppmBand: String = ""
) {

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

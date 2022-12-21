package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor

/**
 * @author: Brandon
 *
 * Job: formats the resistance based on the colors selected for the bands
 */
object ResistanceFormatter {

    private const val OMEGA: String = "Ω"
    private const val PLUS_MINUS: String = "±"
    private const val DEGREE: String = "°"
    private const val EMPTY_STRING = ""

    // works for all 4, 5, and 6 band resistors
    fun calculate(resistor: Resistor, numberOfBands: Int): String {
        if (resistor.isEmpty(numberOfBands)) {
            return "Select Colors"
        }

        // convert from color to values
        val sigFigOne = formatSigFig(resistor.sigFigBandOne)
        val sigFigTwo = formatSigFig(resistor.sigFigBandTwo)
        val sigFigThree = formatSigFig(resistor.sigFigBandThree)
        val tolerance = formatTolerance(resistor.toleranceBand)

        var multiplier = if (numberOfBands == 4) {
            formatMultiplier(resistor.multiplierBand, sigFigOne, sigFigTwo)
        } else {
            formatMultiplier(resistor.multiplierBand, sigFigOne, sigFigTwo, sigFigThree)
        }

        val ppm = if (numberOfBands == 6) {
            formatPPM(resistor.ppmBand)
        } else {
            EMPTY_STRING
        }

        // format multiplier for edge cases of leading 0s
        if (numberOfBands == 4 && resistor.sigFigBandOne == "Black") {
            multiplier = when (resistor.multiplierBand) {
                "Red" -> "${sigFigTwo}00 "
                "Green" -> "${sigFigTwo}00 k"
                "Gray" -> "${sigFigTwo}00 M"
                "Silver" -> "0.0${sigFigTwo} "
                else -> {
                    formatMultiplier(resistor.multiplierBand, EMPTY_STRING, sigFigTwo)
                }
            }
        } else if (numberOfBands != 4 && resistor.sigFigBandOne == "Black" && resistor.sigFigBandTwo == "Black") {
            multiplier = when (resistor.multiplierBand) {
                "Brown" -> "${sigFigThree}0 "
                "Red" -> "${sigFigThree}00 "
                "Yellow" -> "${sigFigThree}0 k"
                "Green" -> "${sigFigThree}00 k"
                "Violet" -> "${sigFigThree}0 M"
                "Gray" -> "${sigFigThree}00 M"
                "Gold" -> "0.${sigFigThree} "
                "Silver" -> "0.0${sigFigThree} "
                else -> {
                    formatMultiplier(
                        resistor.multiplierBand, EMPTY_STRING, EMPTY_STRING, sigFigThree
                    )
                }
            }
        } else if (numberOfBands != 4 && resistor.sigFigBandOne == "Black") {
            multiplier = when (resistor.multiplierBand) {
                "Brown" -> "${sigFigTwo}${sigFigThree}0 "
                "Yellow" -> "${sigFigTwo}${sigFigThree}0 k"
                "Violet" -> "${sigFigTwo}${sigFigThree}0 M"
                "Silver" -> "0.${sigFigTwo}${sigFigThree} "
                else -> {
                    formatMultiplier(resistor.multiplierBand, EMPTY_STRING, sigFigTwo, sigFigThree)
                }
            }
        }

        if (resistor.allDigitsZero(numberOfBands)) {
            multiplier = "0"
        }

        return "$multiplier$OMEGA $tolerance$ppm"
    }

    // gets the number from its color representation
    private fun formatSigFig(color: String): String {
        return when (color) {
            "Black" -> "0"
            "Brown" -> "1"
            "Red" -> "2"
            "Orange" -> "3"
            "Yellow" -> "4"
            "Green" -> "5"
            "Blue" -> "6"
            "Violet" -> "7"
            "Gray" -> "8"
            "White" -> "9"
            else -> "0"
        }
    }

    // four band resistor
    private fun formatMultiplier(color: String, band1: String, band2: String): String {
        return when (color) {
            "Black" -> "$band1$band2 "
            "Brown" -> "${band1}${band2}0 "
            "Red" -> "${band1}.${band2} k"
            "Orange" -> "${band1}${band2} k"
            "Yellow" -> "${band1}${band2}0 k"
            "Green" -> "${band1}.${band2} M"
            "Blue" -> "${band1}${band2} M"
            "Violet" -> "${band1}${band2}0 M"
            "Gray" -> "${band1}.${band2} G"
            "White" -> "${band1}${band2} G"
            "Gold" -> "${band1}.${band2} "
            "Silver" -> "0.${band1}${band2} "
            else -> "$band1$band2 "
        }
    }

    // five or six band resistor
    private fun formatMultiplier(color: String, band1: String, band2: String, band3: String): String {
        return when (color) {
            "Black" -> "$band1$band2$band3 "
            "Brown" -> "${band1}.${band2}${band3} k"
            "Red" -> "${band1}${band2}.${band3} k"
            "Orange" -> "${band1}${band2}${band3} k"
            "Yellow" -> "${band1}.${band2}${band3} M"
            "Green" -> "${band1}${band2}.${band3} M"
            "Blue" -> "${band1}${band2}${band3} M"
            "Violet" -> "${band1}.${band2}${band3} G"
            "Gray" -> "${band1}${band2}.${band3} G"
            "White" -> "${band1}${band2}${band3} G"
            "Gold" -> "${band1}${band2}.${band3} "
            "Silver" -> "${band1}.${band2}${band3} "
            else -> "$band1$band2$band3 "
        }
    }

    // finds the tolerance
    private fun formatTolerance(color: String): String {
        return PLUS_MINUS + when (color) {
            "Brown" -> "1%"
            "Red" -> "2%"
            "Green" -> "0.5%"
            "Blue" -> "0.25%"
            "Violet" -> "0.1%"
            "Gray" -> "0.05%"
            "Gold" -> "5%"
            "Silver" -> "10%"
            else -> "20%"
        }
    }

    // temperature coefficient - only on six band resistor
    private fun formatPPM(color: String): String {
        return when (color) {
            "Black" -> "\n250 ppm/${DEGREE}C"
            "Brown" -> "\n100 ppm/${DEGREE}C"
            "Red" -> "\n50 ppm/${DEGREE}C"
            "Orange" -> "\n15 ppm/${DEGREE}C"
            "Yellow" -> "\n25 ppm/${DEGREE}C"
            "Green" -> "\n20 ppm/${DEGREE}C"
            "Blue" -> "\n10 ppm/${DEGREE}C"
            "Violet" -> "\n5 ppm/${DEGREE}C"
            "Gray" -> "\n1 ppm/${DEGREE}C"
            else -> EMPTY_STRING
        }
    }
}

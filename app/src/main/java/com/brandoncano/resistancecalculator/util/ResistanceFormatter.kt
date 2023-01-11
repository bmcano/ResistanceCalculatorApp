package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor

/**
 * Job: Formats the resistance based on the colors selected for the bands (CtV).
 */
object ResistanceFormatter {

    private const val OMEGA: String = "Ω"
    private const val PLUS_MINUS: String = "±"
    private const val PPM_UNIT: String = "ppm/°C"

    private val colorsToNumbers = mapOf(
        "Black" to "0", "Brown" to "1", "Red" to "2", "Orange" to "3", "Yellow" to "4",
        "Green" to "5", "Blue" to "6", "Violet" to "7", "Gray" to "8", "White" to "9"
    )

    private val multiplierValues = mapOf(
        "Black" to "1 ", "Brown" to "10 ", "Red" to "100 ",
        "Orange" to "1 k", "Yellow" to "10 k", "Green" to "100 k",
        "Blue" to "1 M", "Violet" to "10 M", "Gray" to "100 M",
        "White" to "1 G", "Gold" to "0.1 ", "Silver" to "0.01 "
    )

    // works for all 4, 5, and 6 band resistors
    fun calculate(resistor: Resistor): String {
        if (resistor.isEmpty()) return "Select Colors"

        // convert from color to values
        val sigFigOne = formatSigFig(resistor.sigFigBandOne)
        val sigFigTwo = formatSigFig(resistor.sigFigBandTwo)
        val sigFigThree = formatSigFig(resistor.sigFigBandThree)
        val tolerance = formatTolerance(resistor.toleranceBand)

        val value: Int
        var multiplier = if (resistor.getNumberOfBands() == 4) {
            value = (sigFigOne + sigFigTwo).toInt()
            formatMultiplier(resistor.multiplierBand, sigFigOne, sigFigTwo)
        } else {
            value = (sigFigOne + sigFigTwo + sigFigThree).toInt()
            formatMultiplier(resistor.multiplierBand, sigFigOne, sigFigTwo, sigFigThree)
        }

        val ppm = if (resistor.getNumberOfBands() == 6) {
            formatPPM(resistor.ppmBand)
        } else {
            ""
        }

        // format multiplier for edge cases of leading 0s
        val (multiple, unit) = multiplierValues.getValue(resistor.multiplierBand).split(" ")
        if (resistor.getNumberOfBands() != 4 && resistor.sigFigBandOne == "Black" && resistor.sigFigBandTwo != "Black") {
            multiplier = when (resistor.multiplierBand) {
                "Brown" -> "${sigFigTwo}${sigFigThree}0 "
                "Yellow" -> "${sigFigTwo}${sigFigThree}0 k"
                "Violet" -> "${sigFigTwo}${sigFigThree}0 M"
                "Silver" -> "0.${sigFigTwo}${sigFigThree} "
                else -> formatMultiplier(resistor.multiplierBand, "", sigFigTwo, sigFigThree)
            }
        } else if (resistor.sigFigBandOne == "Black") {
            multiplier = (value * multiple.toDouble()).toString()
            if (multiplier.endsWith(".0")) {
                multiplier = multiplier.substring(0, multiplier.length - 2)
            }
            multiplier += " $unit"
        }

        if (resistor.allDigitsZero()) {
            multiplier = "0 "
        }

        return "$multiplier$OMEGA $tolerance$ppm"
    }

    // gets the number from its color representation
    private fun formatSigFig(color: String): String {
        return if (colorsToNumbers.containsKey(color)) colorsToNumbers.getValue(color) else "0"
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

    private fun formatPPM(color: String): String {
        return when (color) {
            "Black" -> "\n250 $PPM_UNIT"
            "Brown" -> "\n100 $PPM_UNIT"
            "Red" -> "\n50 $PPM_UNIT"
            "Orange" -> "\n15 $PPM_UNIT"
            "Yellow" -> "\n25 $PPM_UNIT"
            "Green" -> "\n20 $PPM_UNIT"
            "Blue" -> "\n10 $PPM_UNIT"
            "Violet" -> "\n5 $PPM_UNIT"
            "Gray" -> "\n1 $PPM_UNIT"
            else -> ""
        }
    }
}

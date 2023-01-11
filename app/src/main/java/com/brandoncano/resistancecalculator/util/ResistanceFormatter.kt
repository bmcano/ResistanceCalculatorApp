package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor

/**
 * Job: Formats the resistance based on the colors selected for the bands (CtV).
 */
object ResistanceFormatter {

    private const val OMEGA: String = "Ω"
    private const val PLUS_MINUS: String = "±"
    private const val PPM_UNIT: String = "ppm/°C"

    private val colorToNumber = mapOf(
        "Black" to "0", "Brown" to "1", "Red" to "2", "Orange" to "3", "Yellow" to "4",
        "Green" to "5", "Blue" to "6", "Violet" to "7", "Gray" to "8", "White" to "9"
    )

    private val colorToMultiplier = mapOf(
        "Black" to "1 ", "Brown" to "10 ", "Red" to "100 ",
        "Orange" to "1 k", "Yellow" to "10 k", "Green" to "100 k",
        "Blue" to "1 M", "Violet" to "10 M", "Gray" to "100 M",
        "White" to "1 G", "Gold" to "0.1 ", "Silver" to "0.01 "
    )

    private val colorToTolerance = mapOf(
        "Brown" to "1%", "Red" to "2%", "Green" to "0.5%", "Blue" to "0.25%",
        "Violet" to "0.1%", "Gray" to "0.05%", "Gold" to "5%", "Silver" to "10%"
    )

    private val colorToPPM = mapOf(
        "Black" to "\n250", "Brown" to "\n100", "Red" to "\n50",
        "Orange" to "\n15", "Yellow" to "\n25", "Green" to "\n20",
        "Blue" to "\n10", "Violet" to "\n5", "Gray" to "\n1"
    )

    // works for all 4, 5, and 6 band resistors
    fun calculate(resistor: Resistor): String {
        if (resistor.isEmpty()) return "Select Colors"

        val sigFigOne = formatSigFig(resistor.sigFigBandOne)
        val sigFigTwo = formatSigFig(resistor.sigFigBandTwo)
        val sigFigThree = formatSigFig(resistor.sigFigBandThree)
        val resistance = formatResistance(resistor, sigFigOne, sigFigTwo, sigFigThree)
        val tolerance = formatTolerance(resistor.toleranceBand)
        val ppm = formatPPM(resistor.ppmBand, resistor.getNumberOfBands())

        return "$resistance$OMEGA $tolerance$ppm"
    }

    // gets the number from its color representation
    private fun formatSigFig(color: String): String {
        return if (colorToNumber.containsKey(color)) colorToNumber.getValue(color) else "0"
    }

    // four band resistor
    private fun formatMultiplier(color: String, band1: String, band2: String): String {
        return when (color) {
            "Black" -> "$band1$band2 "
            "Brown" -> "$band1${band2}0 "
            "Red" -> "${band1}.${band2} k"
            "Orange" -> "$band1${band2} k"
            "Yellow" -> "$band1${band2}0 k"
            "Green" -> "${band1}.${band2} M"
            "Blue" -> "$band1${band2} M"
            "Violet" -> "$band1${band2}0 M"
            "Gray" -> "${band1}.${band2} G"
            "White" -> "$band1${band2} G"
            "Gold" -> "${band1}.${band2} "
            "Silver" -> "0.$band1${band2} "
            else -> "$band1$band2 "
        }
    }

    // five or six band resistor
    private fun formatMultiplier(color: String, band1: String, band2: String, band3: String): String {
        return band1 + when (color) {
            "Black" -> "$band2$band3 "
            "Brown" -> ".$band2$band3 k"
            "Red" -> "${band2}.$band3 k"
            "Orange" -> "$band2$band3 k"
            "Yellow" -> ".$band2$band3 M"
            "Green" -> "${band2}.$band3 M"
            "Blue" -> "$band2$band3 M"
            "Violet" -> ".$band2$band3 G"
            "Gray" -> "${band2}.$band3 G"
            "White" -> "$band2$band3 G"
            "Gold" -> "$band2.$band3 "
            "Silver" -> ".$band2$band3 "
            else -> "$band2$band3 "
        }
    }

    private fun formatTolerance(color: String): String {
        return PLUS_MINUS + if (colorToTolerance.containsKey(color)) {
            colorToTolerance.getValue(color)
        } else "20%"
    }

    private fun formatPPM(color: String, bands: Int): String {
        return if (colorToPPM.containsKey(color) && bands == 6) {
            colorToPPM.getValue(color) + " $PPM_UNIT"
        } else ""
    }

    private fun formatResistance(resistor: Resistor, sigFigOne: String, sigFigTwo:String, sigFigThree: String): String {
        if (resistor.allDigitsZero()) return "0 "

        val value: Int
        var multiplier = if (resistor.getNumberOfBands() == 4) {
            value = (sigFigOne + sigFigTwo).toInt()
            formatMultiplier(resistor.multiplierBand, sigFigOne, sigFigTwo)
        } else {
            value = (sigFigOne + sigFigTwo + sigFigThree).toInt()
            formatMultiplier(resistor.multiplierBand, sigFigOne, sigFigTwo, sigFigThree)
        }

        // format multiplier for edge cases of leading 0s
        val (multiple, unit) = colorToMultiplier.getValue(resistor.multiplierBand).split(" ")
        if (resistor.getNumberOfBands() != 4 && resistor.sigFigBandOne == "Black" && resistor.sigFigBandTwo != "Black") {
            multiplier = when (resistor.multiplierBand) {
                "Brown" -> "${sigFigTwo}${sigFigThree}0 "
                "Yellow" -> "${sigFigTwo}${sigFigThree}0 k"
                "Violet" -> "${sigFigTwo}${sigFigThree}0 M"
                "Silver" -> "0.${sigFigTwo}${sigFigThree} "
                else -> formatMultiplier(resistor.multiplierBand, "", sigFigTwo, sigFigThree)
            }
        } else if (resistor.sigFigBandOne == "Black") {
            multiplier = if (resistor.multiplierBand == "Silver") {
                String.format("%.2f", (value * multiple.toFloat()))
            } else {
                String.format("%.1f", (value * multiple.toFloat()))
            }
            if (multiplier.endsWith(".0")) {
                multiplier = multiplier.substring(0, multiplier.length - 2)
            }
            multiplier += " $unit"
        }

        return multiplier
    }
}

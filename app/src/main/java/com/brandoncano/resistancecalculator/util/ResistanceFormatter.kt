package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.constants.*

/**
 * Job: Formats the resistance based on the colors selected for the bands (CtV).
 */
object ResistanceFormatter {

    private val colorToNumber = mapOf(
        BLACK to "0", BROWN to "1", RED to "2", ORANGE to "3", YELLOW to "4",
        GREEN to "5", BLUE to "6", VIOLET to "7", GRAY to "8", WHITE to "9"
    )

    private val colorToMultiplier = mapOf(
        BLACK to "1 ", BROWN to "10 ", RED to "100 ",
        ORANGE to "1 k", YELLOW to "10 k", GREEN to "100 k",
        BLUE to "1 M", VIOLET to "10 M", GRAY to "100 M",
        WHITE to "1 G", GOLD to "0.1 ", SILVER to "0.01 "
    )

    private val colorToTolerance = mapOf(
        BROWN to "1%", RED to "2%", GREEN to "0.5%", BLUE to "0.25%",
        VIOLET to "0.1%", GRAY to "0.05%", GOLD to "5%", SILVER to "10%"
    )

    private val colorToPPM = mapOf(
        BLACK to "\n250", BROWN to "\n100", RED to "\n50", ORANGE to "\n15",
        YELLOW to "\n25", GREEN to "\n20", BLUE to "\n10", VIOLET to "\n5", GRAY to "\n1"
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

        return "$resistance${OHMS} $tolerance$ppm"
    }

    // gets the number from its color representation
    private fun formatSigFig(color: String): String {
        return if (colorToNumber.containsKey(color)) colorToNumber.getValue(color) else "0"
    }

    // four band resistor
    private fun formatMultiplier(color: String, band1: String, band2: String): String {
        return when (color) {
            BLACK -> "$band1$band2 "
            BROWN -> "$band1${band2}0 "
            RED -> "${band1}.${band2} k"
            ORANGE -> "$band1${band2} k"
            YELLOW -> "$band1${band2}0 k"
            GREEN -> "${band1}.${band2} M"
            BLUE -> "$band1${band2} M"
            VIOLET -> "$band1${band2}0 M"
            GRAY -> "${band1}.${band2} G"
            WHITE -> "$band1${band2} G"
            GOLD -> "${band1}.${band2} "
            SILVER -> "0.$band1${band2} "
            else -> "$band1$band2 "
        }
    }

    // five or six band resistor
    private fun formatMultiplier(color: String, band1: String, band2: String, band3: String): String {
        return band1 + when (color) {
            BLACK -> "$band2$band3 "
            BROWN -> ".$band2$band3 k"
            RED -> "${band2}.$band3 k"
            ORANGE -> "$band2$band3 k"
            YELLOW -> ".$band2$band3 M"
            GREEN -> "${band2}.$band3 M"
            BLUE -> "$band2$band3 M"
            VIOLET -> ".$band2$band3 G"
            GRAY -> "${band2}.$band3 G"
            WHITE -> "$band2$band3 G"
            GOLD -> "$band2.$band3 "
            SILVER -> ".$band2$band3 "
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
        if (resistor.getNumberOfBands() != 4 && resistor.sigFigBandOne == BLACK && resistor.sigFigBandTwo != BLACK) {
            multiplier = when (resistor.multiplierBand) {
                BROWN -> "${sigFigTwo}${sigFigThree}0 "
                YELLOW -> "${sigFigTwo}${sigFigThree}0 k"
                VIOLET -> "${sigFigTwo}${sigFigThree}0 M"
                SILVER -> "0.${sigFigTwo}${sigFigThree} "
                else -> formatMultiplier(resistor.multiplierBand, "", sigFigTwo, sigFigThree)
            }
        } else if (resistor.sigFigBandOne == BLACK) {
            multiplier = if (resistor.multiplierBand == SILVER) {
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

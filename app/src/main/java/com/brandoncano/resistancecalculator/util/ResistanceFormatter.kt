package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.constants.*

/**
 * Job: Formats the resistance based on the colors selected for the bands (CtV).
 */
object ResistanceFormatter {

    fun calculate(resistor: Resistor): String {
        if (resistor.isEmpty()) return "Select Colors"

        val sigFigOne = formatSigFig(resistor.sigFigBandOne)
        val sigFigTwo = formatSigFig(resistor.sigFigBandTwo)
        val sigFigThree = formatSigFig(resistor.sigFigBandThree)
        val resistance = formatResistance(resistor, sigFigOne, sigFigTwo, sigFigThree)
        val tolerance = formatTolerance(resistor.toleranceBand)
        val ppm = formatPPM(resistor.ppmBand, resistor.getNumberOfBands())

        return "$resistance $tolerance$ppm"
    }

    private fun formatSigFig(color: String): String {
        val colorToNumber = mapOf(
            BLACK to "0", BROWN to "1", RED to "2", ORANGE to "3", YELLOW to "4",
            GREEN to "5", BLUE to "6", VIOLET to "7", GRAY to "8", WHITE to "9"
        )
        return if (colorToNumber.containsKey(color)) colorToNumber.getValue(color) else "0"
    }

    private fun formatTolerance(color: String): String {
        val colorToTolerance = mapOf(
            BROWN to "1%", RED to "2%", GREEN to "0.5%", BLUE to "0.25%",
            VIOLET to "0.1%", GRAY to "0.05%", GOLD to "5%", SILVER to "10%"
        )
        return PLUS_MINUS + if (colorToTolerance.containsKey(color)) {
            colorToTolerance.getValue(color)
        } else "20%"
    }

    private fun formatPPM(color: String, bands: Int): String {
        val colorToPPM = mapOf(
            BLACK to "250", BROWN to "100", RED to "50", ORANGE to "15",
            YELLOW to "25", GREEN to "20", BLUE to "10", VIOLET to "5", GRAY to "1"
        )
        return if (colorToPPM.containsKey(color) && bands == 6) {
            "\n${colorToPPM.getValue(color)} $PPM_UNIT"
        } else ""
    }

    private fun getMultiplierValue(color: String): Double {
        val colorToMultiplier = mapOf(
            BLACK to 1.0, BROWN to 10.0, RED to 100.0, ORANGE to 1000.0, YELLOW to 10000.0,
            GREEN to 100000.0, BLUE to 1000000.0, VIOLET to 10000000.0, GRAY to 100000000.0,
            WHITE to 1000000000.0, GOLD to 0.1, SILVER to 0.01
        )
        return if (colorToMultiplier.containsKey(color)) {
            colorToMultiplier.getValue(color)
        } else 1.0
    }

    private fun formatResistance(resistor: Resistor, sigFigOne: String, sigFigTwo: String, sigFigThree: String): String {
        if (resistor.allDigitsZero()) return "0 $OHMS" // 0 Ohm resistor

        val hasLeadingZero = sigFigOne == "0"
        val hasTwoLeadingZeros = sigFigOne == "0" && sigFigTwo == "0"
        val value: Int = if (resistor.getNumberOfBands() == 4) {
            (sigFigOne + sigFigTwo).toIntOrNull() ?: return "0 $OHMS"
        } else {
            (sigFigOne + sigFigTwo + sigFigThree).toIntOrNull() ?: return "0 $OHMS"
        }

        val multiplier = getMultiplierValue(resistor.multiplierBand)
        var resistanceAsDecimal = value.times(multiplier)
        val units = unitConversion(resistanceAsDecimal)
        while (resistanceAsDecimal >= 1000) {
            resistanceAsDecimal /= 1000
        }

        val bands = resistor.getNumberOfBands()
        val noDecimal = (bands == 4 && resistanceAsDecimal >= 10) ||
                (bands != 4 && resistanceAsDecimal >= 100) ||
                (bands == 4 && hasLeadingZero && resistanceAsDecimal >= 1) ||
                (bands != 4 && hasLeadingZero && resistanceAsDecimal >= 10) ||
                (bands != 4 && hasTwoLeadingZeros && resistanceAsDecimal >= 1)

        return if (noDecimal) {
            "${String.format("%.0f", resistanceAsDecimal)} $units"
        } else if (resistor.multiplierBand == SILVER) {
            "${String.format("%.2f", resistanceAsDecimal)} $units"
        } else {
            "${String.format("%.1f", resistanceAsDecimal)} $units"
        }
    }

    private fun unitConversion(value: Double): String {
        return when {
            value >= 1000000000 -> "G$OHMS"
            value >= 1000000 -> "M$OHMS"
            value >= 1000 -> "k$OHMS"
            else -> OHMS
        }
    }
}

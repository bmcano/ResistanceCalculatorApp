package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S
import com.brandoncano.resistancecalculator.resistor.Resistor

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
        val colorMap = mapOf(
            C.BLACK to "0", C.BROWN to "1", C.RED    to "2", C.ORANGE to "3", C.YELLOW to "4",
            C.GREEN to "5", C.BLUE  to "6", C.VIOLET to "7", C.GRAY   to "8", C.WHITE  to "9"
        )
        return colorMap[color] ?: "0"
    }

    private fun formatTolerance(color: String): String {
        val toleranceMap = mapOf(
            C.BROWN  to "1%",   C.RED  to "2%",    C.GREEN to "0.5%", C.BLUE to "0.25%",
            C.VIOLET to "0.1%", C.GRAY to "0.05%", C.GOLD  to "5%",   C.SILVER to "10%"
        )
        return S.PM + (toleranceMap[color] ?: "20%")
    }

    private fun formatPPM(color: String, bands: Int): String {
        val colorToPPM = mapOf(
            C.BLACK  to "250", C.BROWN to "100", C.RED  to "50", C.ORANGE to "15",
            C.YELLOW to "25",  C.GREEN to "20",  C.BLUE to "10", C.VIOLET to "5", C.GRAY to "1"
        )
        return if (bands == 6) "\n${colorToPPM[color] ?: return ""} ${S.PPM}" else ""
    }

    private fun getMultiplierValue(color: String): Double {
        val colorToMultiplier = mapOf(
            C.BLACK  to 1.0, C.BROWN to 10.0, C.RED to 100.0, C.ORANGE to 1000.0,
            C.YELLOW to 10000.0, C.GREEN to 100000.0, C.BLUE to 1000000.0, C.VIOLET to 10000000.0,
            C.GRAY   to 100000000.0, C.WHITE to 1000000000.0, C.GOLD to 0.1, C.SILVER to 0.01
        )
        return colorToMultiplier[color] ?: 1.0
    }

    private fun formatResistance(resistor: Resistor, sigFigOne: String, sigFigTwo: String, sigFigThree: String): String {
        if (resistor.allDigitsZero()) return "0 ${S.Ohms}" // 0 Ohm resistor

        val hasLeadingZero = sigFigOne == "0"
        val hasTwoLeadingZeros = sigFigOne == "0" && sigFigTwo == "0"
        val value: Int = if (resistor.getNumberOfBands() == 4) {
            (sigFigOne + sigFigTwo).toIntOrNull() ?: return "0 ${S.Ohms}"
        } else {
            (sigFigOne + sigFigTwo + sigFigThree).toIntOrNull() ?: return "0 ${S.Ohms}"
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
            "${"%.0f".format(resistanceAsDecimal)} $units"
        } else if (resistor.multiplierBand == C.SILVER) {
            "${"%.2f".format(resistanceAsDecimal)} $units"
        } else {
            "${"%.1f".format(resistanceAsDecimal)} $units"
        }
    }

    private fun unitConversion(value: Double): String {
        return when {
            value >= 1000000000 -> S.GOhms
            value >= 1000000 -> S.MOhms
            value >= 1000 -> S.kOhms
            else -> S.Ohms
        }
    }
}

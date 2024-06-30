package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.model.ctv.ResistorCtv
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Formats the resistance based on the colors selected for the bands (CtV).
 */
object ResistanceFormatter {

    private const val ZERO_OHMS = "0 ${S.OHMS}"

    // NOTE: this is for the compose iteration of the app code above will eventually be removed
    fun calculate(resistor: ResistorCtv): String {
        if (resistor.isEmpty()) return "Select colors"

        val sigFigOne = ValueFinder.getSigFig(resistor.band1)
        val sigFigTwo = ValueFinder.getSigFig(resistor.band2)
        val sigFigThree = ValueFinder.getSigFig(resistor.band3)
        val resistance = formatResistance(resistor, sigFigOne, sigFigTwo, sigFigThree)
        val tolerance = ValueFinder.getTolerance(resistor.band5, resistor.isThreeBand())
        val ppm = ValueFinder.getPPM(resistor.band6, resistor.isSixBand())

        return "$resistance $tolerance\n$ppm".trimEnd('\n')
    }

    private fun formatResistance(resistor: ResistorCtv, sigFigOne: String, sigFigTwo: String, sigFigThree: String): String {
        val threeFourBands = resistor.isThreeFourBand()
        val value: Int = if (threeFourBands) {
            (sigFigOne + sigFigTwo).toIntOrNull()
        } else {
            (sigFigOne + sigFigTwo + sigFigThree).toIntOrNull()
        } ?: return ZERO_OHMS

        val multiplier = ValueFinder.getMultiplier(resistor.band4)
        var resistanceAsDecimal = value.times(multiplier)
        val units = UnitsFromMultiplier.execute(resistanceAsDecimal)
        while (resistanceAsDecimal >= 1000) {
            resistanceAsDecimal /= 1000
        }

        val noDecimal = (threeFourBands && resistanceAsDecimal >= 10) || (!threeFourBands && resistanceAsDecimal >= 100)
        val decimalPrecision = when {
            noDecimal -> "%.0f"
            resistor.band4 == C.SILVER -> "%.2f"
            threeFourBands || resistanceAsDecimal >= 10.0 -> "%.1f"
            else -> "%.2f"
        }
        return "${decimalPrecision.format(resistanceAsDecimal)} $units"
    }
}

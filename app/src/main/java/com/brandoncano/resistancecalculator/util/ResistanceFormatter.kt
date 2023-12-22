package com.brandoncano.resistancecalculator.util

import android.provider.Settings.Global.getString
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S
import com.brandoncano.resistancecalculator.resistor.Resistor

/**
 * Job: Formats the resistance based on the colors selected for the bands (CtV).
 */
object ResistanceFormatter {

    private const val zeroOhms = "0 ${S.Ohms}"

    fun calculate(resistor: Resistor): String {
        if (resistor.isEmpty()) return "Select colors"

        val sigFigOne = ValueFinder.getSigFig(resistor.sigFigBandOne)
        val sigFigTwo = ValueFinder.getSigFig(resistor.sigFigBandTwo)
        val sigFigThree = ValueFinder.getSigFig(resistor.sigFigBandThree)
        val resistance = formatResistance(resistor, sigFigOne, sigFigTwo, sigFigThree)
        val tolerance = ValueFinder.getTolerance(resistor.toleranceBand)
        val ppm = ValueFinder.getPPM(resistor.ppmBand, resistor.getNumberOfBands())

        return "$resistance $tolerance$ppm"
    }

    private fun formatResistance(resistor: Resistor, sigFigOne: String, sigFigTwo: String, sigFigThree: String): String {
        if (resistor.isFirstDigitZero()) return zeroOhms // we no longer have the option for the first digit to be zero

        val bands = resistor.getNumberOfBands()
        val value: Int = if (bands == 4) {
            (sigFigOne + sigFigTwo).toIntOrNull()
        } else {
            (sigFigOne + sigFigTwo + sigFigThree).toIntOrNull()
        } ?: return zeroOhms

        val multiplier = ValueFinder.getMultiplier(resistor.multiplierBand)
        var resistanceAsDecimal = value.times(multiplier)
        val units = UnitsFromMultiplier.execute(resistanceAsDecimal)
        while (resistanceAsDecimal >= 1000) {
            resistanceAsDecimal /= 1000
        }

        val noDecimal = (bands == 4 && resistanceAsDecimal >= 10) || (bands != 4 && resistanceAsDecimal >= 100)
        val decimalPrecision = when {
            noDecimal -> "%.0f"
            resistor.multiplierBand == C.SILVER -> "%.2f"
            bands == 4 || resistanceAsDecimal >= 10.0 -> "%.1f"
            else -> "%.2f"
        }
        return "${decimalPrecision.format(resistanceAsDecimal)} $units"
    }
}

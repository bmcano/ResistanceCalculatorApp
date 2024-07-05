package com.brandoncano.resistancecalculator.model.vtc

import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.util.ColorFinder

data class ResistorVtc(
    var resistance: String = "",
    var units: String = "",
    var band5: String = "",
    var band6: String = "",
    var navBarSelection: Int = 1,
) {
    // needed for the resistor image
    var band1 = ""
    var band2 = ""
    var band3 = ""
    var band4 = ""

    fun isThreeBand() = navBarSelection == 0
    fun isThreeFourBand() = navBarSelection == 0 || navBarSelection == 1
    fun isFiveSixBand() = navBarSelection == 2 || navBarSelection == 3
    fun isSixBand() = navBarSelection == 3

    fun getResistorValue(): String {
        var text = "$resistance $units "
        text += if (isThreeBand()) "${Symbols.PM}20%" else band5
        if (isSixBand()) text += ", $band6"
        return text
    }

    fun isEmpty(): Boolean {
        return resistance.isEmpty() || units.isEmpty()
    }

    override fun toString(): String {
        val tolerance = ColorFinder.colorToText(ColorFinder.textToColor(band5))
        val ppm = ColorFinder.colorToText(ColorFinder.textToColor(band6))
        return when (navBarSelection) {
            4 -> "[ $band1, $band2, $band4, $band5 ]"
            5 -> "[ $band1, $band2, $band3, $band4, $tolerance ]"
            6 -> "[ $band1, $band2, $band3, $band4, $tolerance, $ppm ]"
            else -> "[ $band1, $band2, $band4 ]"
        }
    }
}
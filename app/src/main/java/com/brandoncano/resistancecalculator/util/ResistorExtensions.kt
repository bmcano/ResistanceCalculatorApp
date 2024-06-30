package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.model.ctv.Resistor as ResistorCtV

// color to value
fun ResistorCtV.bandThreeForDisplay(): String {
    return if (this.isFiveSixBand()) this.band3 else ""
}

fun ResistorCtV.bandFiveForDisplay(): String {
    return if (!this.isThreeBand()) this.band5 else ""
}

fun ResistorCtV.bandSixForDisplay(): String {
    return if (this.isSixBand()) this.band6 else ""
}

fun ResistorCtV.formatResistance(): String {
    return ResistanceFormatter.calculate(this)
}

// value to color
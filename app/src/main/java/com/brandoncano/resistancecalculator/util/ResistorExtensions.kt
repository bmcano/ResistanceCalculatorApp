package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.model.ctv.ResistorCtv

// color to value
fun ResistorCtv.bandThreeForDisplay(): String {
    return if (this.isFiveSixBand()) this.band3 else ""
}

fun ResistorCtv.bandFiveForDisplay(): String {
    return if (!this.isThreeBand()) this.band5 else ""
}

fun ResistorCtv.bandSixForDisplay(): String {
    return if (this.isSixBand()) this.band6 else ""
}

fun ResistorCtv.formatResistance(): String {
    return ResistanceFormatter.calculate(this)
}

// value to color
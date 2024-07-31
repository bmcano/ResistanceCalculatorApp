package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.model.ctv.ResistorCtv
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc

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
fun ResistorVtc.bandThreeForDisplay(): String {
    return if (this.isFiveSixBand()) this.band3 else ""
}

fun ResistorVtc.bandFiveForDisplay(): String {
    return if (!this.isThreeBand()) this.band5 else ""
}

fun ResistorVtc.bandSixForDisplay(): String {
    return if (this.isSixBand()) this.band6 else ""
}

fun ResistorVtc.formatResistor() {
    ResistorFormatter.generateResistor(this)
}

fun ResistorVtc.isInputInvalid(): Boolean {
    return !IsValidResistance.execute(this, this.resistance)
}

fun String.adjustValueForSharing(): String {
    val color = ColorFinder.textToColor(this)
    return ColorFinder.colorToColorText(color)
}

// smd
fun SmdResistor.isSmdInputInvalid(): Boolean {
    return !IsValidSmdCode.execute(this.code, this.getSmdMode())
}

fun SmdResistor.formatResistance(): String {
    return ResistanceSmdFormatter.execute(this)
}
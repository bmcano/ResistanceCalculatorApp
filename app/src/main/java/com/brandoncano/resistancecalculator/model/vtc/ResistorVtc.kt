package com.brandoncano.resistancecalculator.model.vtc

data class ResistorVtc(
    var resistance: String = "",
    var units: String = "",
    var band5: String = "",
    var band6: String = "",
    var numberOfBands: Int = 4,
) {
    // needed for the resistor image
    var band1 = ""
    var band2 = ""
    var band3 = ""
    var band4 = ""

    fun isThreeBand() = numberOfBands == 3
    fun isThreeFourBand() = numberOfBands == 3 || numberOfBands == 4
    fun isFiveSixBand() = numberOfBands == 5 || numberOfBands == 6
    fun isSixBand() = numberOfBands == 6

    fun isEmpty(): Boolean {
        return resistance.isEmpty() || units.isEmpty()
    }
}
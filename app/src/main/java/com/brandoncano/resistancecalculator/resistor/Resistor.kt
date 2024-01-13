package com.brandoncano.resistancecalculator.resistor

/**
 * Job: Interface for the resistor.
 */
abstract class Resistor {
    // these are the common attributes used in both
    var numberOfBands = 4
    var sigFigBandOne = ""
    var sigFigBandTwo = ""
    var sigFigBandThree = ""
    var multiplierBand = ""
    var resistance = ""

    abstract fun loadData()
    abstract fun loadNumberOfBands(): String
    abstract fun clear()
    abstract fun updateResistance(resistance: String)
    abstract fun updateNumberOfBands(number: Int)

    fun isThreeBand() = numberOfBands == 3
    fun isThreeFourBand() = numberOfBands == 3 || numberOfBands == 4
    fun isSixBand() = numberOfBands == 6
}

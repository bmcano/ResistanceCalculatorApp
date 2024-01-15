package com.brandoncano.resistancecalculator.resistor

/**
 * Job: Interface for the resistor.
 */
abstract class Resistor {
    // these are the common attributes used in both
    var numberOfBands = 4
        protected set
    var sigFigBandOne = ""
    var sigFigBandTwo = ""
    var sigFigBandThree = ""
    var multiplierBand = ""
    var toleranceBand = ""
    var ppmBand = ""
    var resistance = ""

    abstract fun loadData()
    abstract fun loadNumberOfBands(): String
    abstract fun clear()
    abstract fun isEmpty(): Boolean
    abstract fun saveResistance(resistance: String)
    abstract fun saveNumberOfBands(number: Int)
    abstract fun saveDropdownSelections()

    fun isThreeBand() = numberOfBands == 3
    fun isThreeFourBand() = numberOfBands == 3 || numberOfBands == 4
    fun isSixBand() = numberOfBands == 6
}

package com.brandoncano.resistancecalculator.model.ctv

/**
 * Job: Model class for the color to value resistor calculator
 */
data class ResistorCtv(
    var band1: String = "",
    var band2: String = "",
    var band3: String = "",
    var band4: String = "",
    var band5: String = "",
    var band6: String = "",
    var navBarSelection: Int = 1,
) {
    fun isThreeBand() = navBarSelection == 0
    fun isThreeFourBand() = navBarSelection == 0 || navBarSelection == 1
    fun isFiveSixBand() = navBarSelection == 2 || navBarSelection == 3
    fun isSixBand() = navBarSelection == 3

    fun isEmpty(): Boolean {
        val isMissingBands = band1.isEmpty() || band2.isEmpty() || band4.isEmpty()
        val isMissingBandsFiveSix = isMissingBands || band3.isEmpty()
        return (isThreeFourBand() && isMissingBands) || (!isThreeFourBand() && isMissingBandsFiveSix)
    }

    override fun toString(): String {
        return when (navBarSelection) {
            4 -> "[ $band1, $band2, $band4, $band5 ]"
            5 -> "[ $band1, $band2, $band3, $band4, $band5 ]"
            6 -> "[ $band1, $band2, $band3, $band4, $band5, $band6 ]"
            else -> "[ $band1, $band2, $band4 ]"
        }
    }
}

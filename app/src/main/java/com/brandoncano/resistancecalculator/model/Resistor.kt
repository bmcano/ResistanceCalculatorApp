package com.brandoncano.resistancecalculator.model

/**
 * Job: Model class for the color to value resistor calculator
 */
data class Resistor(
    var band1: String = "",
    var band2: String = "",
    var band3: String = "",
    var band4: String = "",
    var band5: String = "",
    var band6: String = "",
    var numberOfBands: Int = 4,
) {
    fun clear() {
        band1 = ""
        band2 = ""
        band3 = ""
        band4 = ""
        band5 = ""
        band6 = ""
    }

    fun isThreeBand() = numberOfBands == 3
    fun isThreeFourBand() = numberOfBands == 3 || numberOfBands == 4
    fun isFiveSixBand() = numberOfBands == 5 || numberOfBands == 6
    fun isSixBand() = numberOfBands == 6

    fun isEmpty(): Boolean {
        val isMissingBands = band1.isEmpty() || band2.isEmpty() || band4.isEmpty()
        val isMissingBandsFiveSix = isMissingBands || band3.isEmpty()
        return (isThreeFourBand() && isMissingBands) || (!isThreeFourBand() && isMissingBandsFiveSix)
    }

    override fun toString(): String {
        return when (numberOfBands) {
            4 -> "[ $band1, $band2, $band4, $band5 ]"
            5 -> "[ $band1, $band2, $band3, $band4, $band5 ]"
            6 -> "[ $band1, $band2, $band3, $band4, $band5, $band6 ]"
            else -> "[ $band1, $band2, $band4 ]"
        }
    }
}

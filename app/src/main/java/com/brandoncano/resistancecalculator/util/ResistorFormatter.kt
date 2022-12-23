package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor

/**
 * @author: Brandon
 *
 * Job: formats the resistor based resistance that has been entered
 */
object ResistorFormatter {

    private const val OMEGA: String = "Ω"
    private const val EMPTY_STRING = ""

    private val colorsArray = arrayOf(
        "Silver", "Gold", "Black", "Brown", "Red", "Orange", "Yellow",
        "Green", "Blue", "Violet", "Gray", "White", "Blank"
    )

    fun generateResistor(resistor: Resistor) {
        val resistance = resistor.resistance
        if (resistance == "NotValid" || resistance.isEmpty()) {
            return
        }

        // find color for the sig fig bands
        var numberBand1 = 0
        var numberBand2 = 0
        var numberBand3 = 0
        val formattedResistance = resistance.replace(".", EMPTY_STRING).toInt().toString()
        formattedResistance.forEachIndexed { index, digit ->
            if (index == 0) { numberBand1 = digit.digitToInt() }
            if (index == 1) { numberBand2 = digit.digitToInt() }
            if (index == 2) { numberBand3 = digit.digitToInt() }
        }

        resistor.sigFigBandOne = ColorFinder.numberToText(numberBand1)
        resistor.sigFigBandTwo = ColorFinder.numberToText(numberBand2)

        resistor.sigFigBandThree = if (resistor.getNumberOfBands() != 4) {
            ColorFinder.numberToText(numberBand3)
        } else {
            EMPTY_STRING
        }

        resistor.multiplierBand = if ('.' in resistance) {
            decimalInput(resistor.getNumberOfBands(), resistance, resistor.units)
        } else {
            numericalInput(resistor.getNumberOfBands(), resistance, resistor.units)
        }
    }

    // find the correct multiplier for an input with a decimal
    private fun decimalInput(numBands: Int, resistance: String, units: String): String {
        var before = 0
        var after = 0
        var change = false
        for (digit in resistance) {
            if (digit == '.') {
                change = true; continue
            }
            if (!change) before++
            else after++
        }

        val first = resistance[0]
        return if (numBands == 4) {
            when {
                units == OMEGA && (first == '0' || first == '.') -> "Silver"
                units == OMEGA && before == 1 -> "Gold"
                units == OMEGA && before == 2 && after == 0 -> "Black"

                units == "k$OMEGA" && (first == '0' || first == '.') -> "Brown"
                units == "k$OMEGA" && before == 1 -> "Red"
                units == "k$OMEGA" && before == 2 && after == 0 -> "Orange"

                units == "M$OMEGA" && (first == '0' || first == '.') -> "Yellow"
                units == "M$OMEGA" && before == 1 -> "Green"
                units == "M$OMEGA" && before == 2 && after == 0 -> "Blue"

                units == "G$OMEGA" && (first == '0' || first == '.') -> "Violet"
                units == "G$OMEGA" && before == 1 -> "Gray"
                units == "G$OMEGA" && before == 2 && after == 0 -> "White"

                else -> "Blank"
            }
        } else {
            when {
                units == OMEGA && before == 1 -> "Silver"
                units == OMEGA && before == 2 -> "Gold"
                units == OMEGA && before == 3 && after == 0 -> "Black"

                units == "k$OMEGA" && (first == '0' || first == '.') -> "Black"
                units == "k$OMEGA" && before == 1 -> "Brown"
                units == "k$OMEGA" && before == 2 -> "Red"
                units == "k$OMEGA" && before == 3 && after == 0 -> "Orange"

                units == "M$OMEGA" && (first == '0' || first == '.') -> "Orange"
                units == "M$OMEGA" && before == 1 -> "Yellow"
                units == "M$OMEGA" && before == 2 -> "Green"
                units == "M$OMEGA" && before == 3 && after == 0 -> "Blue"

                units == "G$OMEGA" && (first == '0' || first == '.') -> "Blue"
                units == "G$OMEGA" && before == 1 -> "Violet"
                units == "G$OMEGA" && before == 2 -> "Gray"
                units == "G$OMEGA" && before == 3 && after == 0 -> "White"

                else -> "Blank"
            }
        }
    }

    // find the value of the multiplier for any whole numbers
    private fun numericalInput(numberOfBands: Int, resistance: String, units: String): String {
        val length: Int = resistance.length

        var shifts = 0
        var remainder = length.mod(3)
        if (length > 3) {
            remainder = (length - 3).mod(3)
            shifts++
        }

        shifts = when (units) {
            OMEGA -> shifts
            "k$OMEGA" -> shifts + 1
            "M$OMEGA" -> shifts + 2
            "G$OMEGA" -> shifts + 3
            else -> 0
        }

        var index = when {
            shifts == 0 && remainder == 1 -> 0
            shifts == 0 && remainder == 2 -> 1
            shifts == 0 && remainder == 0 -> 2
            shifts == 1 && remainder == 1 -> 3
            shifts == 1 && remainder == 2 -> 4
            shifts == 1 && remainder == 0 -> 5
            shifts == 2 && remainder == 1 -> 6
            shifts == 2 && remainder == 2 -> 7
            shifts == 2 && remainder == 0 -> 8
            shifts == 3 && remainder == 1 -> 9
            shifts == 3 && remainder == 2 -> 10
            shifts == 3 && remainder == 0 -> 11
            else -> 12
        }

        if (numberOfBands == 4) {
            index++
        }
        return colorsArray[index]
    }
}

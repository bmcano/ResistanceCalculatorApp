package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.components.OHMS

/**
 * Job: Formats the resistor based resistance that has been entered (VtC).
 */
object ResistorFormatter {

    private val colorsArray = arrayOf(
        "Silver", "Gold", "Black", "Brown", "Red", "Orange", "Yellow",
        "Green", "Blue", "Violet", "Gray", "White", "Blank"
    )

    fun generateResistor(resistor: Resistor) {
        val resistance = resistor.resistance
        if (resistance == "NotValid" || resistance.isEmpty()) return

        // remove decimal and check leading zeros
        val numberBands = arrayOf(0, 0, 0)
        val formattedResistance = checkLeadingZeros(resistor.getNumberOfBands(), resistance)
        formattedResistance.forEachIndexed { index, digit ->
            if (index < 3) numberBands[index] = digit.digitToInt()
        }

        resistor.sigFigBandOne = ColorFinder.numberToText(numberBands[0])
        resistor.sigFigBandTwo = ColorFinder.numberToText(numberBands[1])
        resistor.sigFigBandThree = if (resistor.getNumberOfBands() != 4) {
            ColorFinder.numberToText(numberBands[2])
        } else ""

        resistor.multiplierBand = if ('.' in resistance) {
            decimalInput(resistor.getNumberOfBands(), resistance, resistor.units)
        } else {
            numericalInput(resistor.getNumberOfBands(), resistance, resistor.units)
        }
    }

    // check leading zero inputs
    private fun checkLeadingZeros(bands: Int, value: String): String {
        val values = value.replace(".", "")
        val numbers = values.toCharArray()
        val validSize = numbers.size == 2 || numbers.size == 3 || numbers.size == 4
        if (bands == 4 && validSize && numbers[0] == '0') {
            return values.substring(1, values.length)
        }
        return values
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
            if (!change) before++ else after++
        }

        val first = resistance[0]
        return if (numBands == 4) {
            when {
                units == OHMS && (first == '0' || first == '.') -> "Silver"
                units == OHMS && before == 1 -> "Gold"
                units == OHMS && before == 2 -> "Black"

                units == "k$OHMS" && (first == '0' || first == '.') -> "Brown"
                units == "k$OHMS" && before == 1 -> "Red"
                units == "k$OHMS" && before == 2 -> "Orange"

                units == "M$OHMS" && (first == '0' || first == '.') -> "Yellow"
                units == "M$OHMS" && before == 1 -> "Green"
                units == "M$OHMS" && before == 2 -> "Blue"

                units == "G$OHMS" && (first == '0' || first == '.') -> "Violet"
                units == "G$OHMS" && before == 1 -> "Gray"
                units == "G$OHMS" && before == 2 -> "White"

                else -> "Blank"
            }
        } else {
            when {
                units == OHMS && before == 1 -> "Silver"
                units == OHMS && before == 2 -> "Gold"
                units == OHMS && before == 3 && after == 0 -> "Black"

                units == "k$OHMS" && (first == '0' || first == '.') -> "Black"
                units == "k$OHMS" && before == 1 -> "Brown"
                units == "k$OHMS" && before == 2 -> "Red"
                units == "k$OHMS" && before == 3 && after == 0 -> "Orange"

                units == "M$OHMS" && (first == '0' || first == '.') -> "Orange"
                units == "M$OHMS" && before == 1 -> "Yellow"
                units == "M$OHMS" && before == 2 -> "Green"
                units == "M$OHMS" && before == 3 && after == 0 -> "Blue"

                units == "G$OHMS" && (first == '0' || first == '.') -> "Blue"
                units == "G$OHMS" && before == 1 -> "Violet"
                units == "G$OHMS" && before == 2 -> "Gray"
                units == "G$OHMS" && before == 3 && after == 0 -> "White"

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
            OHMS -> shifts
            "k$OHMS" -> shifts + 1
            "M$OHMS" -> shifts + 2
            "G$OHMS" -> shifts + 3
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

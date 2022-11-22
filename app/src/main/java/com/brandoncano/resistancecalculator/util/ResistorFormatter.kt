package com.brandoncano.resistancecalculator.util

import com.Ostermiller.util.SignificantFigures
import com.brandoncano.resistancecalculator.R

/**
 * @author: Brandon
 *
 * Job: formats the resistor based resistance that has been entered
 */
object ResistorFormatter {
    private const val OMEGA: String = "Ω"
    private const val EMPTY_STRING = ""

    // EditText already limits this to decimal and whole numbers and 5 characters
    // Invalid Inputs: 0.0... , 00... , 0.xyz , 0x , .0x , too many sig figs, etc.
    fun isValidInput(numBands: Int, input: String, units: String): Boolean {
        try {
            input.toDouble()
        } catch (e: NumberFormatException) {
            return false
        }
        val sigFigs = SignificantFigures(input)

        return when {
            (numBands == 4 && sigFigs.numberSignificantFigures > 2) ||
                    ((numBands == 5 || numBands == 6) && sigFigs.numberSignificantFigures > 3) ||
                    ((numBands == 5 || numBands == 6) && (input[0] == '0' || input[0] == '.') &&
                            (units == OMEGA || units == EMPTY_STRING)) ||
                    ((numBands == 5 || numBands == 6) && units == "G$OMEGA" && input.length > 3) ||
                    ((numBands == 4) && units == "G$OMEGA" && input.length > 2) ||
                    (input.length > 1 && input[0] == '0' && input[1] == '0') ||
                    (input.length > 2 && input[0] == '0' && input[1] == '.' && input[2] == '0') ||
                    (input.length > 1 && input[0] == '0' && input[1] != '.') ||
                    (input.length > 1 && input[0] == '.' && input[1] == '0') -> return false
            else -> true
        }
    }

    // returns an array of the 3 or 4 colors to be returned
    fun generateResistor(numBands: Int, resistance: String, units: String): Array<Int> {
        // this will prevent the program from crashing
        if (resistance == "NotValid" || resistance == EMPTY_STRING) {
            return arrayOf()
        }

        // find color for the sig fig bands
        var numberBand1 = 0
        var numberBand2 = 0
        var numberBand3 = 0
        val formattedResistance = resistance.replace(".", EMPTY_STRING).toInt().toString()
        var i = 0
        for (digit in formattedResistance) {
            if (i == 0) numberBand1 = digit.digitToInt()
            if (i == 1) numberBand2 = digit.digitToInt()
            if (i == 2) numberBand3 = digit.digitToInt()
            i += 1
        }

        // find color for multiplier band
        val decimalPresent = '.' in resistance
        val multiplierBand: String = if (decimalPresent) {
            decimalInput(numBands, resistance, units)
        } else {
            numericalInput(numBands, resistance, units)
        }

        // return correct values
        return if (numBands == 4) {
            arrayOf(
                ColorFinder.numberColor(numberBand1),
                ColorFinder.numberColor(numberBand2),
                R.color.resistor_blank,
                ColorFinder.textToColor(multiplierBand)
            )
        } else {
            arrayOf(
                ColorFinder.numberColor(numberBand1),
                ColorFinder.numberColor(numberBand2),
                ColorFinder.numberColor(numberBand3),
                ColorFinder.textToColor(multiplierBand)
            )
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
    private fun numericalInput(numBands: Int, resistance: String, units: String): String {
        var length: Int = resistance.length

        var shifts = 0
        var remainder = length.mod(3)
        while (length > 3) {
            length -= 3
            remainder = length.mod(3)
            shifts++
        }

        shifts = when (units) {
            OMEGA -> shifts + 0
            "k$OMEGA" -> shifts + 1
            "M$OMEGA" -> shifts + 2
            "G$OMEGA" -> shifts + 3
            else -> 0
        }

        return if (numBands == 4) {
            when {
                shifts == 0 && remainder == 1 -> "Gold"
                shifts == 0 && remainder == 2 -> "Black"
                shifts == 0 && remainder == 0 -> "Brown"

                shifts == 1 && remainder == 1 -> "Red"
                shifts == 1 && remainder == 2 -> "Orange"
                shifts == 1 && remainder == 0 -> "Yellow"

                shifts == 2 && remainder == 1 -> "Green"
                shifts == 2 && remainder == 2 -> "Blue"
                shifts == 2 && remainder == 0 -> "Violet"

                shifts == 3 && remainder == 1 -> "Gray"
                shifts == 3 && remainder == 2 -> "White"

                else -> "Blank"
            }
        } else {
            when {
                shifts == 0 && remainder == 1 -> "Silver"
                shifts == 0 && remainder == 2 -> "Gold"
                shifts == 0 && remainder == 0 -> "Black"

                shifts == 1 && remainder == 1 -> "Brown"
                shifts == 1 && remainder == 2 -> "Red"
                shifts == 1 && remainder == 0 -> "Orange"

                shifts == 2 && remainder == 1 -> "Yellow"
                shifts == 2 && remainder == 2 -> "Green"
                shifts == 2 && remainder == 0 -> "Blue"

                shifts == 3 && remainder == 1 -> "Violet"
                shifts == 3 && remainder == 2 -> "Gray"
                shifts == 3 && remainder == 0 -> "White"

                else -> "Blank"
            }
        }
    }
}

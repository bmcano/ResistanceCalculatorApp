package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.constants.BLANK
import com.brandoncano.resistancecalculator.constants.OHMS

/**
 * Job: Formats the resistor based resistance that has been entered (VtC).
 */
object ResistorFormatter {

    private val colorsMap = mapOf(
        0 to "Silver", 1 to "Gold", 2 to "Black", 3 to "Brown", 4 to "Red", 5 to "Orange",
        6 to "Yellow", 7 to "Green", 8 to "Blue", 9 to "Violet", 10 to "Gray", 11 to "White",
    )

    fun generateResistor(resistor: Resistor) {
        val resistance = resistor.resistance
        if (resistance == "NotValid" || resistance.isEmpty()) return

        val multiplier = unitsMultiplier(resistor.units)
        val resLong: Long? = resistance.toLongOrNull()?.times(multiplier)
        val resDouble: Double = resistance.toDoubleOrNull()?.times(multiplier) ?: return
        if (resLong == null) {
            resistor.multiplierBand = decimalInput(resistor, resDouble)
        } else {
            resistor.multiplierBand = numericalInput(resistor, resLong)
        }

        // remove decimal and check leading zeros
        val numberBands = arrayOf(0, 0, 0)
        val numberOfBands = resistor.getNumberOfBands()
        val formattedResistance = checkLeadingZeros(numberOfBands, String.format("%.2f", resDouble))
        formattedResistance.forEachIndexed { index, digit ->
            if (index < 3) numberBands[index] = digit.digitToInt()
        }

        resistor.sigFigBandOne = ColorFinder.numberToText(numberBands[0])
        resistor.sigFigBandTwo = ColorFinder.numberToText(numberBands[1])
        resistor.sigFigBandThree = if (numberOfBands != 4) {
            ColorFinder.numberToText(numberBands[2])
        } else ""
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
    private fun decimalInput(resistor: Resistor, resistance: Double): String {
        val res = String.format("%.2f", resistance)
        var index = res.indexOf(".")
        if (index == -1) index = res.length
        if (res.startsWith("0")) {
            index = 0
        }
        if (resistor.getNumberOfBands() != 4 && !res.startsWith("0.")) index--
        if (colorsMap.containsKey(index)) {
            return colorsMap.getValue(index)
        }
        return BLANK
    }

    // find the value of the multiplier for any whole numbers
    private fun numericalInput(resistor: Resistor, resistance: Long): String {
        var length = resistance.toString().length
        if (resistor.getNumberOfBands() != 4) length--
        if (colorsMap.containsKey(length)) {
            return colorsMap.getValue(length)
        }
        return BLANK
    }

    // multiplier to convert to ohms
    private fun unitsMultiplier(units: String): Int {
        return when (units) {
            "k$OHMS" -> 1000
            "M$OHMS" -> 1000000
            "G$OHMS" -> 1000000000
            else -> 1 // Ohms
        }
    }
}

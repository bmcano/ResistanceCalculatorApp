package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.constants.Colors as C

/**
 * Job: Formats the resistor based resistance that has been entered (VtC).
 */
object ResistorFormatter {

    private val colorsMap = mapOf(
        0 to C.SILVER, 1 to C.GOLD, 2 to C.BLACK, 3 to C.BROWN, 4 to C.RED, 5 to C.ORANGE,
        6 to C.YELLOW, 7 to C.GREEN, 8 to C.BLUE, 9 to C.VIOLET, 10 to C.GRAY, 11 to C.WHITE,
    )

    fun generateResistor(resistor: Resistor) {
        val resistance = resistor.resistance
        if (resistance == "NotValid" || resistance.isEmpty()) return

        val multiplier = MultiplierFromUnits.execute(resistor.units)
        val resLong: Long? = resistance.toLongOrNull()?.times(multiplier)
        val resDouble: Double = resistance.toDoubleOrNull()?.times(multiplier) ?: return
        if (resLong == null) {
            resistor.multiplierBand = decimalInputMultiplier(resistor, resDouble)
        } else {
            resistor.multiplierBand = numericalInputMultiplier(resistor, resLong)
        }

        // remove decimal and check leading zeros
        val numberBands = arrayOf(0, 0, 0)
        val numberOfBands = resistor.getNumberOfBands()
        val formattedResistance = checkLeadingZeros(numberOfBands, String.format("%.2f", resDouble))
        formattedResistance.forEachIndexed { index, digit ->
            // if a invalid character makes its way through, set to -1 for a blank band
            if (index < 3) numberBands[index] = digit.digitToIntOrNull() ?: -1
        }

        resistor.sigFigBandOne = ColorFinder.numberToText(numberBands[0])
        resistor.sigFigBandTwo = ColorFinder.numberToText(numberBands[1])
        resistor.sigFigBandThree = if (numberOfBands != 4) {
            ColorFinder.numberToText(numberBands[2])
        } else ""
    }

    private fun checkLeadingZeros(bands: Int, value: String): String {
        val values = value.replace(".", "")
        val numbers = values.toCharArray()
        val validSize = numbers.size == 2 || numbers.size == 3 || numbers.size == 4
        if (bands == 4 && validSize && numbers[0] == '0') {
            return values.substring(1, values.length)
        }
        return values
    }

    private fun decimalInputMultiplier(resistor: Resistor, resistance: Double): String {
        val res = String.format("%.2f", resistance)
        var index = res.indexOf(".")
        if (index == -1) index = res.length
        if (res.startsWith("0")) {
            index = 0
        }
        if (resistor.getNumberOfBands() != 4 && !res.startsWith("0.")) index--
        return colorsMap[index] ?: C.BLANK
    }

    private fun numericalInputMultiplier(resistor: Resistor, resistance: Long): String {
        var length = resistance.toString().length
        if (resistor.getNumberOfBands() != 4) length--
        return colorsMap[length] ?: C.BLANK
    }
}

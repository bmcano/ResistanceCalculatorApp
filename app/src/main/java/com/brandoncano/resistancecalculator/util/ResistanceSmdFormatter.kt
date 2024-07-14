package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.SmdMode
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.model.smd.SmdResistor

/**
 * Job: Format the resistance text depending on the mode and code
 */
object ResistanceSmdFormatter {

    fun execute(resistor: SmdResistor): String {
        if (resistor.isEmpty()) return "Enter code"
        val smdMode = resistor.getSmdMode()
        val code = resistor.code
        val resistance = when(smdMode) {
            SmdMode.ThreeDigit -> threeDigit(code)
            SmdMode.FourDigit -> fourDigit(code)
            SmdMode.EIA96 -> eia96(code)
        }
        val units = resistor.units.ifEmpty { Symbols.OHMS }
        val unitsConversion = MultiplierFromUnits.execute(units)
        if (resistance.isNaN()) {
            return "$resistance"
        }
        val convertedResistance = resistance / unitsConversion
        // TODO - check for weird floating point repeating patterns
        return "$convertedResistance $units"
    }

    private fun threeDigit(code: String): Double {
        val first = code[0]
        val second = code[1]
        return if (second == 'R') {
            val third = code[2]
            "$first.$third".toDoubleOrNull() ?: Double.NaN
        } else {
            val multiplier = MultiplierFromDigit.execute(code[2])
            return ("$first$second".toDoubleOrNull() ?: Double.NaN) * multiplier
        }
    }

    private fun fourDigit(code: String): Double {
        val first = code[0]
        val second = code[1]
        val third = code[2]
        return if (second == 'R') {
            val fourth = code[3]
            "$first.$third$fourth".toDoubleOrNull() ?: Double.NaN
        } else if (third == 'R') {
            val fourth = code[3]
            "$first$second.$fourth".toDoubleOrNull() ?: Double.NaN
        } else {
            val multiplier = MultiplierFromDigit.execute(code[3])
            return ("$first$second$third".toDoubleOrNull() ?: Double.NaN) * multiplier
        }
    }

    private fun eia96(code: String): Double {
        val lookupValue = code.substring(0, 2)
        val baseValue = FindEIA96Value.execute(lookupValue)
        val multiplier = MultiplierFromDigit.execute(code[2])
        return baseValue * multiplier
    }
}

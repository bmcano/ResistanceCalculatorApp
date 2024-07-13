package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.SmdMode
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.model.smd.SmdResistor

object ResistanceSmdFormatter {

    fun execute(resistor: SmdResistor): String {
        val smdMode = resistor.getSmdMode()
        val length = resistor.code.length
        if (length < 3 || (smdMode is SmdMode.FourDigit && length < 4)) {
            return "Enter code"
        }
        val code = resistor.code
        val resistance = when(smdMode) {
            SmdMode.ThreeDigit -> threeDigit(code)
            SmdMode.FourDigit -> fourDigit(code)
            SmdMode.EIA96 -> eia96(code)
        }
        val units = resistor.units.ifEmpty { Symbols.OHMS }
        val unitsConversion = MultiplierFromUnits.execute(units)
        // TODO - check for weird floating point repeating patterns
        val convertedResistance = resistance / unitsConversion
        return "$convertedResistance $units"
    }

    private fun threeDigit(code: String): Double {
        val first = code[0]
        val second = code[1]
        return if (second == 'R') {
            val third = code[2]
            "$first.$third".toDouble()
        } else {
            val multiplier = MultiplierFromDigit.execute(code[2])
            return "$first$second".toInt() * multiplier
        }
    }

    private fun fourDigit(code: String): Double {
        val first = code[0]
        val second = code[1]
        val third = code[2]
        return if (second == 'R') {
            val fourth = code[3]
            "$first.$third$fourth".toDouble()
        } else if (third == 'R') {
            val fourth = code[3]
            "$first$second.$fourth".toDouble()
        } else {
            val multiplier = MultiplierFromDigit.execute(code[2])
            return "$first$second$third".toInt() * multiplier
        }
    }

    private fun eia96(code: String): Double {
        // TODO - understand what each value means
        return 0.0
    }
}

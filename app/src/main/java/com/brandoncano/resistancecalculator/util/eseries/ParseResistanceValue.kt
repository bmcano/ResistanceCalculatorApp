package com.brandoncano.resistancecalculator.util.eseries

import com.brandoncano.resistancecalculator.util.MultiplierFromUnits

/**
 * Gives the resistance value based on the entered value and units.
 */
object ParseResistanceValue {

    fun execute(valueStr: String, units: String): Double? {
        val value = valueStr.toDoubleOrNull() ?: return null
        val multiplier = MultiplierFromUnits.execute(units)
        return value * multiplier
    }
}

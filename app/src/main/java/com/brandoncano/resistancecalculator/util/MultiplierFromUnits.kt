package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Gives back the multiplier from the given unit.
 */
object MultiplierFromUnits {

    fun execute(units: String): Int {
        return when (units) {
            S.KOHMS -> 1000
            S.MOHMS -> 1000000
            S.GOHMS -> 1000000000
            else -> 1 // Ohms
        }
    }
}

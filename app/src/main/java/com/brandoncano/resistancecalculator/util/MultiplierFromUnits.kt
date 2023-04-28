package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Gives back the multiplier from the given unit.
 */
object MultiplierFromUnits {

    fun execute(units: String): Int {
        return when (units) {
            S.kOhms -> 1000
            S.MOhms -> 1000000
            S.GOhms -> 1000000000
            else -> 1 // Ohms
        }
    }
}

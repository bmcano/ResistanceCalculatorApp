package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.OHMS

/**
 * Job: will get the proper value in relation to the units
 */
object MultiplierFromUnits {

    fun execute(units: String): Int {
        return when (units) {
            "k$OHMS" -> 1000
            "M$OHMS" -> 1000000
            "G$OHMS" -> 1000000000
            else -> 1 // Ohms
        }
    }
}

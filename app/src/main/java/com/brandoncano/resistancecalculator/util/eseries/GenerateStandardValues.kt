package com.brandoncano.resistancecalculator.util.eseries

import kotlin.math.pow

/**
 * Job: For each decade, compute the list of possible values in the E-series.
 */
object GenerateStandardValues {

    fun execute(eSeriesList: List<Int>): List<Double> {
        val standardValues = mutableListOf<Double>()
        val exponents = -2..9 // from 0.01Ω to 1GΩ
        for (exp in exponents) {
            val multiplier = 10.0.pow(exp)
            for (eValue in eSeriesList) {
                // Note: rounded of floating point errors is handled in the RoundStandardValue function
                standardValues.add(eValue * multiplier)
            }
        }
        return standardValues
    }
}

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
                standardValues.add(eValue * multiplier) // TODO - round values to prevent long decimals
            }
        }
        return standardValues
    }
}

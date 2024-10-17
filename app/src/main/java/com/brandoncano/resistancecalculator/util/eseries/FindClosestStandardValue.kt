package com.brandoncano.resistancecalculator.util.eseries

import kotlin.math.abs

/**
 * Job: Find the next closest value in the E-series, or the exact value if it exists.
 */
object FindClosestStandardValue {

    // TODO
    //  - (?) try to find the closest value greater than the resistanceValue
    //  - potential chance for a crash if an empty list enters
    fun execute(resistanceValue: Double, standardValues: List<Double>): Double {
        var closestValue = standardValues[0]
        var minDiff = abs(resistanceValue - closestValue)
        for (value in standardValues) {
            val diff = abs(resistanceValue - value)
            if (diff < minDiff) {
                minDiff = diff
                closestValue = value
            }
        }
        return closestValue
    }
}

package com.brandoncano.resistancecalculator.util.eseries

import java.util.Locale

/**
 * Job: Take in the closest standard value and then round it properly for the text field
 */
object RoundStandardValue {

    fun execute(resistance: Double): String {
        var roundedValue = String.format(Locale.US, "%.2f", resistance)
        roundedValue = roundedValue.removeSuffix(".00")
        roundedValue = roundedValue.removeSuffix(".0")
        if (roundedValue.contains(".")) {
            roundedValue = roundedValue.removeSuffix("0")
        }
        return roundedValue
    }
}

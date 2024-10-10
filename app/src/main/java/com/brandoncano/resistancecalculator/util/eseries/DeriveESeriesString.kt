package com.brandoncano.resistancecalculator.util.eseries

import com.brandoncano.resistancecalculator.data.ESeries.E12
import com.brandoncano.resistancecalculator.data.ESeries.E192
import com.brandoncano.resistancecalculator.data.ESeries.E24
import com.brandoncano.resistancecalculator.data.ESeries.E48
import com.brandoncano.resistancecalculator.data.ESeries.E96

/**
 * Job: Get string representation for the E-Series list
 */
object DeriveESeriesString {

    fun execute(eSeries: List<Int>): String {
        return when (eSeries) {
            E12 -> "E12"
            E24 -> "E24"
            E48 -> "E48"
            E96 -> "E96"
            E192 -> "E192"
            else -> "E6"
        }
    }
}

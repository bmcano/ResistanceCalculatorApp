package com.brandoncano.resistancecalculator.spinner

/**
 * @author Brandon
 *
 * Job: holds the data for each spinner option
 */
data class SpinnerItem(val name: String, val logo: Int) {

    override fun toString(): String {
        return name
    }
}

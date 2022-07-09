package com.brandoncano.resistancecalculator.spinner

/**
 * Job: holds the information for each item in the dropdown
 *
 * @author Brandon
 */

data class SpinnerItem (
    val name: String,
    val logo: Int
) {
    override fun toString() : String {
        return name
    }
}

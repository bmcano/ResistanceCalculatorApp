package com.brandoncano.resistancecalculator.components

/**
 * Job: Holds the drawable and the name of the color.
 */
data class SpinnerItem(val name: String, val logo: Int) {

    override fun toString(): String {
        return name
    }
}

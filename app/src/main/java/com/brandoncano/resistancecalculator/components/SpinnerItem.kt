package com.brandoncano.resistancecalculator.components

/**
 * Job: Holds the name and drawable resource for a color.
 */
data class SpinnerItem(val name: String, val logo: Int) {

    override fun toString(): String {
        return name
    }
}

package com.brandoncano.resistancecalculator.components

/**
 * Job: Holds the name and drawable resource for a color.
 */
data class SpinnerItem(val name: String, val logo: Int, val value: String, val isVtC: Boolean = false) {

    override fun toString(): String {
        return if (isVtC) value else name
    }
}

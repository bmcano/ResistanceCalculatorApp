package com.brandoncano.resistancecalculator.spinner

/**
 * @author Brandon
 */
data class SpinnerItem(val name: String, val logo: Int) {

    override fun toString(): String {
        return name
    }
}

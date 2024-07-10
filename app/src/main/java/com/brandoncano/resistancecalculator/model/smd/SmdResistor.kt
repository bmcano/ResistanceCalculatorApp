package com.brandoncano.resistancecalculator.model.smd

data class SmdResistor(
    var code: String = "",
    var resistance: String = "",
    var units: String = "",
    var navBarSelection: Int = 0,
) {


    override fun toString(): String {
        return "$code\n$resistance $units"
    }
}
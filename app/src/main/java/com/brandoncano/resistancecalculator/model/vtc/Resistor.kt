package com.brandoncano.resistancecalculator.model.vtc

data class Resistor(
    var resistance: String = "",
    var units: String = "",
    var band5: String = "",
    var band6: String = "",
    var numberOfBands: Int = 4,
) {

}
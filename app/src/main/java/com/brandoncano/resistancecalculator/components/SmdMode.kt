package com.brandoncano.resistancecalculator.components

sealed class SmdMode(val number: Int) {
    data object ThreeDigit : SmdMode(0)
    data object FourDigit : SmdMode(1)
    data object EIA96 : SmdMode(2)
}
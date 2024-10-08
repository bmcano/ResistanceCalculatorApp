package com.brandoncano.resistancecalculator.data

sealed class SmdMode {
    data object ThreeDigit : SmdMode()
    data object FourDigit : SmdMode()
    data object EIA96 : SmdMode()
}

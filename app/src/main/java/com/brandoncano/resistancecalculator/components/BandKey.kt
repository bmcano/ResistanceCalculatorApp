package com.brandoncano.resistancecalculator.components

sealed class BandKey {
    data object Band1 : BandKey()
    data object Band2 : BandKey()
    data object Band3 : BandKey()
    data object Band4 : BandKey()
    data object Band5 : BandKey()
    data object Band6 : BandKey()
}
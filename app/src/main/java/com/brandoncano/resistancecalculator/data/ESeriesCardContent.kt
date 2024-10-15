package com.brandoncano.resistancecalculator.data

sealed class ESeriesCardContent {
    data class ValidResistance(val value: String) : ESeriesCardContent()
    data class InvalidTolerance(val value: String) : ESeriesCardContent()
    data class InvalidResistance(val value: String) : ESeriesCardContent()
    data object NoContent: ESeriesCardContent()
}

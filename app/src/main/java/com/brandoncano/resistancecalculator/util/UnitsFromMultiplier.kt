package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S

object UnitsFromMultiplier {

    fun execute(value: Double): String {
        return when {
            value >= 1000000000 -> S.GOhms
            value >= 1000000 -> S.MOhms
            value >= 1000 -> S.kOhms
            else -> S.Ohms
        }
    }
}
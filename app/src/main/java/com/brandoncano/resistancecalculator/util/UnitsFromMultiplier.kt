package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S

object UnitsFromMultiplier {

    fun execute(value: Double): String {
        return when {
            value >= 1000000000 -> S.GOHMS
            value >= 1000000 -> S.MOHMS
            value >= 1000 -> S.KOHMS
            else -> S.OHMS
        }
    }
}
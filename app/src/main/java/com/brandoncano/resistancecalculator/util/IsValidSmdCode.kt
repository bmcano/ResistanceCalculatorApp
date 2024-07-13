package com.brandoncano.resistancecalculator.util

object IsValidSmdCode {

    fun execute(code: String, mode: Int): Boolean {
        // 3, 4, 96
        val regex3 = Regex("^[0-9][0-9R][0-9]$")
        val regex4 = Regex("^[0-9][0-9R][0-9R][0-9]$")
        val regex96 = Regex("^[0-9][0-9][A-HRSXYZ]$")
        val isValidRCount = code.count { it == 'R'} < 2
        return when (mode) {
            3 -> regex3.matches(code)
            4 -> regex4.matches(code) && isValidRCount
            96 -> regex96.matches(code)
            else -> false
        }
    }
}

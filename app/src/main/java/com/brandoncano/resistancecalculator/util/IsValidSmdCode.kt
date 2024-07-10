package com.brandoncano.resistancecalculator.util

object IsValidSmdCode {

    fun execute(code: String, mode: Int): Boolean {
        // 3, 4, 96
        val regex3 = Regex("^[0-9][0-9R][0-9]$")
//        val regex4 = Regex("^[0-9](?:(?=[^R]{2}$)|(?=R[^R]$)|(?=[^R]R$))[0-9R]{2}[0-9]$")
        val regex4p1 = Regex("^[0-9][0-9R][0-9][0-9]$")
        val regex4p2 = Regex("^[0-9][0-9][0-9R][0-9]$")
        val regex96 = Regex("^[0-9][0-9][A-HRSXYZ]$")

        return when (mode) {
            3 -> regex3.matches(code)
            4 -> regex4p1.matches(code).xor(regex4p2.matches(code))
            96 -> regex96.matches(code)
            else -> false
        }
    }
}
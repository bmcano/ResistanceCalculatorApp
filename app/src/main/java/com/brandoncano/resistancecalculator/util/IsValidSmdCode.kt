package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.SmdMode

/**
 * Job: Checks for a valid SMD resistor code depending on the mode.
 * Notes:
 *  0 -> 3-digit EIA
 *  1 -> 4-digit EIA
 *  2 -> EIA-96
 */
object IsValidSmdCode {

    fun execute(code: String, mode: SmdMode): Boolean {
        // we return true here since the calculation for resistance won't unless proper length
        if (code.length < 4) return true
        val regex3 = Regex("^[0-9][0-9R][0-9]$")
        val regex4 = Regex("^[0-9][0-9R][0-9R][0-9]$")
        val regex96 = Regex("^[0-9][0-9][A-FHRSXYZ]$")
        val isValidRCount = code.count { it == 'R'} < 2
        return when (mode) {
            SmdMode.ThreeDigit -> regex3.matches(code)
            SmdMode.FourDigit -> regex4.matches(code) && isValidRCount
            SmdMode.EIA96 -> regex96.matches(code)
        }
    }
}

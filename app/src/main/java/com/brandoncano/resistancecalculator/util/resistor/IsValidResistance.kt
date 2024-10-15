package com.brandoncano.resistancecalculator.util.resistor

import com.Ostermiller.util.SignificantFigures
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.util.MultiplierFromUnits

/**
 * Job: Checks if a user inputted resistance is valid.
 *
 * Note: Valid inputs are written next to each Regex
 */
object IsValidResistance {

    private val validThreeFourBandPatterns = listOf(
        Regex("""^\d\.?$"""),       // x0 or x.
        Regex("""^\d\.\d$"""),      // x.y
        Regex("""^\d\d$"""),        // xy or xy.0
        Regex("""^0\.\d\d?$""")     // 0.xy or 0.x0
    )

    private val validFiveSixBandPatterns = listOf(
        Regex("""^\d\.?$"""),       // x00 or x.
        Regex("""^\d\d\.?$"""),     // xy0 or xy.
        Regex("""^\d\.\d\d?$"""),   // x.yz or x.y0
        Regex("""^\d\d\.\d$"""),    // xy.z
        Regex("""^\d\d\d$"""),      // xyz
        Regex("""^0\.\d\d\d?$""")   // 0.xyz or 0.xy0
    )

    fun execute(resistor: ResistorVtc, input: String): Boolean {
        input.ifEmpty { return true }
        if (input.contains(" ")) return false
        if (input.startsWith(".")) return false
        if (input.length == 1 && input.startsWith("0")) return true
        if (input.length == 2 && input.startsWith("0.")) return true
        if (input.length == 2 && input.startsWith('0') && input[1] != '.') return false

        val isThreeFourBand = resistor.isThreeFourBand()
        val patterns = if (isThreeFourBand) validThreeFourBandPatterns else validFiveSixBandPatterns
        val isValidFormat = patterns.any { it.matches(input) }
        if (isValidFormat) return true

        val sigFigs: Int
        try {
            sigFigs = SignificantFigures(input).numberSignificantFigures
        } catch (e: NumberFormatException) {
            return false
        }

        val maxSigFigs = if (isThreeFourBand) 2 else 3
        if (sigFigs > maxSigFigs) return false
        val maxValue = if (isThreeFourBand) 99_000_000_000 else 999_000_000_000
        val resistance = input.toDoubleOrNull() ?: return false
        val multiplier = MultiplierFromUnits.execute(resistor.units)
        return ((resistance * multiplier) <= maxValue)
    }
}

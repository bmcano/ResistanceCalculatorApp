package com.brandoncano.resistancecalculator.util

import com.Ostermiller.util.SignificantFigures
import com.brandoncano.resistancecalculator.Resistor

/**
 * Notes:
 *  - EditText already limits this to decimal and whole numbers and a max of 5 characters
 *  - Invalid Inputs: 0.0... , 00... , 0.xyz , 0x , .0x , too many sig figs, etc.
 */
object ResistanceInput {

    private const val OMEGA: String = "Ω"

    fun isValid(resistor: Resistor, input: String): Boolean {
        input.toDoubleOrNull() ?: return false
        val sigFigs = SignificantFigures(input)
        val numberOfBands = resistor.getNumberOfBands()
        val units = resistor.units

        // check for invalid conditions
        return when {
            numberOfBands == 4 && sigFigs.numberSignificantFigures > 2 -> false

            resistor.isNotFourBandResistor() && (sigFigs.numberSignificantFigures > 3
                    || ((input[0] == '0' || input[0] == '.') && (units == OMEGA || units.isEmpty()))
                    || (units == "G$OMEGA" && input.length > 3)) -> false

            ((numberOfBands == 4) && units == "G$OMEGA" && input.length > 2) -> false

            (input.length > 1 && input[0] == '0' && input[1] == '0') ||
            (input.length > 2 && input[0] == '0' && input[1] == '.' && input[2] == '0') ||
            (input.length > 1 && input[0] == '0' && input[1] != '.') ||
            (input.length > 1 && input[0] == '.' && input[1] == '0') -> false

            else -> true
        }
    }
}
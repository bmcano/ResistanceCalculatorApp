package com.brandoncano.resistancecalculator.util

import com.Ostermiller.util.SignificantFigures
import com.brandoncano.resistancecalculator.constants.Symbols.GOhms
import com.brandoncano.resistancecalculator.resistor.ResistorVtC

/**
 * Job: Checks if a user inputted resistance is valid.
 *
 * Notes:
 *   EditText already limits this to decimal and whole numbers and a max of 5 characters.
 *   Invalid inputs (where [x,y,z,w] are some numeric digit):
 *    - 4 Bands: xyz, 0xy, 00x; .x, .xy, .xyz; 0.0, 0.00x, 0.0xy, x.0y, x.0yz; xy0 for GΩ
 *    - 5/6 Bands: xyzw, 0xy(z), 00x(y); .xy, .0x; 0.xyz, 0.0xyz, x.0yz, x.y0z, 0.00x; 1230 for GΩ
 *   Valid inputs (special cases):
 *    - 4 Bands: xy.0, 0.xy, x.y
 *    - 5/6 Bands: x.yz, xy.z, xyz.0
 */
object IsValidResistance {

    fun execute(resistor: ResistorVtC, input: String): Boolean {
        input.toDoubleOrNull() ?: return false
        val sigFigs = SignificantFigures(input).numberSignificantFigures
        val isThreeFourBand = resistor.isThreeFourBand()
        val units = resistor.units

        // check for specific conditions, priority matters
        return when {
            input.length > 1 && input[0] == '0' && input[1] != '.' ||
            input.isNotEmpty() && input[0] == '.' -> false

            isThreeFourBand -> when { /* FOUR BAND */
                input.startsWith("0.00") ||
                sigFigs >= 2 && input.startsWith("0.0") -> false
                sigFigs == 3 && input.endsWith(".0") ||
                sigFigs == 2 && input.startsWith("0.") ||
                sigFigs <= 2 && '.' in input -> true
                sigFigs > 2 || units == GOhms && input.length > 2 -> false
                else -> true
            }
            else -> when { /* FIVE/SIX BAND */
                input.startsWith("0.00") -> false
                sigFigs == 4 && input.endsWith(".0") -> true
                sigFigs == 3 && input.startsWith("0") ||
                sigFigs == 2 && input.startsWith("0.0") -> false
                sigFigs <= 3 && '.' in input -> true
                sigFigs > 3 || units == GOhms && input.length > 3 -> false
                else -> true
            }
        }
    }
}

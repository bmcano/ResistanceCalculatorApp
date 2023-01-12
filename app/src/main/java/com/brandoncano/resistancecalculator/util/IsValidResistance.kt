package com.brandoncano.resistancecalculator.util

import com.Ostermiller.util.SignificantFigures
import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.components.OHMS

/**
 * Notes:
 *   EditText already limits this to decimal and whole numbers and a max of 5 characters.
 *   Invalid inputs: where (x,y,z,w) are some number.
 *    - 4 Bands: xyz, 0xy, 00x; .x, .xy, .xyz; 0.0, 0.00x, 0.0xy, x.0y, x.0yz; xy0 for GΩ
 *    - 5/6 Bands: xyzw, 0xy(z), 00x(y); .xy, .0x; 0.xyz, 0.0xyz, x.0yz, x.y0z, 0.00x; 1230 for GΩ
 *   Valid inputs (special cases):
 *    - 4 Bands: xy.0, 0.xy, x.y
 *    - 5/6 Bands: x.yz, xy.z, xyz.0
 */
object IsValidResistance {

    fun execute(resistor: Resistor, input: String): Boolean {
        input.toDoubleOrNull() ?: return false
        val sigFigs = SignificantFigures(input).numberSignificantFigures
        val numberOfBands = resistor.getNumberOfBands()
        val units = resistor.units

        // check for specific conditions, priority matters
        return when {
            // general invalid inputs
            input.length > 1 && input[0] == '0' && input[1] != '.' -> false
            input.isNotEmpty() && input[0] == '.' -> false

            // FOUR BAND
            numberOfBands == 4 && input.startsWith("0.00") -> false
            numberOfBands == 4 && sigFigs >= 2 && input.startsWith("0.0") -> false
            numberOfBands == 4 && sigFigs == 3 && input.endsWith(".0") -> true
            numberOfBands == 4 && sigFigs == 2 && input.startsWith("0.") -> true
            numberOfBands == 4 && sigFigs <= 2 && '.' in input -> true
            numberOfBands == 4 && sigFigs > 2 -> false
            numberOfBands == 4 && units == "G$OHMS" && input.length > 2 -> false

            // FIVE/SIX BAND
            resistor.getNumberOfBands() != 4 && input.startsWith("0.00") -> false
            resistor.getNumberOfBands() != 4 && sigFigs == 4 && input.endsWith(".0") -> true
            resistor.getNumberOfBands() != 4 && sigFigs == 3 && input.startsWith("0") -> false
            resistor.getNumberOfBands() != 4 && sigFigs == 2 && input.startsWith("0.0") -> false
            resistor.getNumberOfBands() != 4 && sigFigs <= 3 && '.' in input -> true
            resistor.getNumberOfBands() != 4 && sigFigs > 3 -> false
            resistor.getNumberOfBands() != 4 && units == "G$OHMS" && input.length > 3 -> false

            else -> true
        }
    }
}
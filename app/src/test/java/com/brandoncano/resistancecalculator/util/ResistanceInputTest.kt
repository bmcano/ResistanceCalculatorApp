package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Notes:
 *  - EditText already limits this to decimal and whole numbers and a max of 5 characters
 *  - Invalid Inputs: 0.0... , 00... , 0.xyz , 0x , .0x , too many sig figs, etc.
 */
class ResistanceInputTest {

    private companion object {
        private const val OMEGA: String = "Ω"
    }

    @Test
    fun invalidInputsNew() {
        val resistor = Resistor()
        val numBands: Array<Int> = arrayOf(
            0, 4, 5, 6, 6, 4, 4, 5, 4, 6
        )
        val inputs: Array<String> = arrayOf(
            "InValid", "123", "1234", "0.1", "1000", "100", "00", "0.0", "012", ".0"
        )
        val units: Array<String> = arrayOf(
            "", OMEGA, OMEGA, OMEGA, "G$OMEGA", "G$OMEGA", "", OMEGA, OMEGA, OMEGA
        )

        for (i in 0..9) {
            resistor.setNumberOfBands(numBands[i])
            resistor.units = units[i]

            val actualResult = ResistanceInput.isValid(resistor, inputs[i])
            assertFalse(actualResult)
        }
    }

    @Test
    fun validInput() {
        val resistor = Resistor()
        resistor.setNumberOfBands(5)
        resistor.units = OMEGA

        val actualResult = ResistanceInput.isValid(resistor, "120")
        assertTrue(actualResult)
    }
}
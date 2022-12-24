package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Notes:
 *   EditText already limits this to decimal and whole numbers and a max of 5 characters
 */
class IsValidResistanceTest {

    private companion object {
        private const val OMEGA: String = "Ω"
    }

    @Test
    fun invalidInputs() {
        val resistor = Resistor()
        // four band
        resistor.units = OMEGA
        assertFalse(IsValidResistance.execute(resistor, "InValid"))

        assertFalse(IsValidResistance.execute(resistor, "133"))
        assertFalse(IsValidResistance.execute(resistor, "011"))
        assertFalse(IsValidResistance.execute(resistor, "001"))

        assertFalse(IsValidResistance.execute(resistor, ".1"))
        assertFalse(IsValidResistance.execute(resistor, ".12"))
        assertFalse(IsValidResistance.execute(resistor, ".123"))

        assertFalse(IsValidResistance.execute(resistor, "0.0"))
        assertFalse(IsValidResistance.execute(resistor, "0.006"))
        assertFalse(IsValidResistance.execute(resistor, "0.056"))
        assertFalse(IsValidResistance.execute(resistor, "1.02"))
        assertFalse(IsValidResistance.execute(resistor, "1.023"))

        resistor.units = "G$OMEGA"
        assertFalse(IsValidResistance.execute(resistor, "130"))

        // five/six band
        resistor.units = OMEGA
        resistor.setNumberOfBands(5)
        assertFalse(IsValidResistance.execute(resistor, "1234"))
        assertFalse(IsValidResistance.execute(resistor, "0123"))
        assertFalse(IsValidResistance.execute(resistor, "0012"))
        assertFalse(IsValidResistance.execute(resistor, "012"))
        assertFalse(IsValidResistance.execute(resistor, "001"))

        assertFalse(IsValidResistance.execute(resistor, ".12"))
        assertFalse(IsValidResistance.execute(resistor, ".01"))

        assertFalse(IsValidResistance.execute(resistor, "0.123"))
        assertFalse(IsValidResistance.execute(resistor, "0.0123"))
        assertFalse(IsValidResistance.execute(resistor, "1.023"))
        assertFalse(IsValidResistance.execute(resistor, "1.203"))
        assertFalse(IsValidResistance.execute(resistor, "0.001"))
        resistor.units = "G$OMEGA"
        assertFalse(IsValidResistance.execute(resistor, "1230"))
        assertFalse(IsValidResistance.execute(resistor, "0.001"))
    }

    @Test
    fun validInputs() {
        val resistor = Resistor()
        // four band
        resistor.units = OMEGA
        assertTrue(IsValidResistance.execute(resistor, "12"))
        assertTrue(IsValidResistance.execute(resistor, "10"))
        assertTrue(IsValidResistance.execute(resistor, "5"))
        assertTrue(IsValidResistance.execute(resistor, "12.0"))
        assertTrue(IsValidResistance.execute(resistor, "0.67"))
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "0.05"))

        resistor.units = "G$OMEGA"
        assertTrue(IsValidResistance.execute(resistor, "13.0"))
        assertTrue(IsValidResistance.execute(resistor, "0.67"))
        assertTrue(IsValidResistance.execute(resistor, "0.6"))
        assertTrue(IsValidResistance.execute(resistor, "6.7"))

        // five band
        resistor.units = OMEGA
        resistor.setNumberOfBands(5)
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "6.23"))
        assertTrue(IsValidResistance.execute(resistor, "63.2"))
        assertTrue(IsValidResistance.execute(resistor, "663.0"))
        assertTrue(IsValidResistance.execute(resistor, "0.01"))

        resistor.units = "G$OMEGA"
        resistor.setNumberOfBands(5)
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "6.23"))
        assertTrue(IsValidResistance.execute(resistor, "63.2"))
        assertTrue(IsValidResistance.execute(resistor, "663.0"))
        assertTrue(IsValidResistance.execute(resistor, "0.123"))
    }
}
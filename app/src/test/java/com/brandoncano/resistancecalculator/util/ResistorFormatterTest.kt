package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor
import org.junit.Assert.*
import org.junit.Test

class ResistorFormatterTest {
    companion object {
        private const val OMEGA: String = "Ω"
        private const val PLUS_MINUS: String = "±"
        private const val DEGREE: String = "°"
    }

    @Test
    fun invalidInputs() {
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
            val number = numBands[i]
            val input = inputs[i]
            val unit = units[i]

            val actualResult = ResistorFormatter.isValidInput(number, input, unit)
            assertFalse(actualResult)
        }
    }

    @Test
    fun validInput() {
        val actualResult = ResistorFormatter.isValidInput(5, "120", OMEGA)
        assertTrue(actualResult)
    }

    @Test
    fun inValidGeneration() {
        val expectedResult:Array<Int> = arrayOf()
        val actualResult = ResistorFormatter.generateResistor(4, "NotValid", OMEGA)

        assertArrayEquals(expectedResult, actualResult)
    }

    @Test
    fun emptyStringGeneration() {
        val expectedResult:Array<Int> = arrayOf()
        val actualResult = ResistorFormatter.generateResistor(4, "", OMEGA)

        assertArrayEquals(expectedResult, actualResult)
    }

    @Test
    fun numericalInputs() {
        // 5 band
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(5)

        resistor.resistance = "100"
        resistor.units = OMEGA
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)
    }
}

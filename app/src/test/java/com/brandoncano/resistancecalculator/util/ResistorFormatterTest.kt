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

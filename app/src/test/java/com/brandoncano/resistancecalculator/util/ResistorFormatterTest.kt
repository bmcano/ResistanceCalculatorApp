package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistorFormatterTest {

    companion object {
        private const val OMEGA: String = "Ω"
        private const val PLUS_MINUS: String = "±"
        private const val DEGREE: String = "°"
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

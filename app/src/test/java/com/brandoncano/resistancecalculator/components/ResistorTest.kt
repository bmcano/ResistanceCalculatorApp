package com.brandoncano.resistancecalculator.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ResistorTest {

    @Test
    fun `empty resistors as a string`() {
        val resistor = Resistor()
        assertEquals("[ , , ,  ]", resistor.toColorBandString())
        resistor.setNumberOfBands(5)
        assertEquals("[ , , , ,  ]", resistor.toColorBandString())
        resistor.setNumberOfBands(6)
        assertEquals("[ , , , , ,  ]", resistor.toColorBandString())
    }

    @Test
    fun `is the resistor empty`() {
        var resistor = Resistor()
        assertTrue(resistor.isEmpty())
        resistor.setNumberOfBands(5)
        assertTrue(resistor.isEmpty())

        resistor.sigFigBandTwo = "Black"
        assertTrue(resistor.isEmpty())

        resistor = Resistor("Black", "Brown", "", "Red", "Gold")
        assertFalse(resistor.isEmpty())

        resistor.setNumberOfBands(5)
        assertTrue(resistor.isEmpty())
        resistor.sigFigBandThree = "Orange"
        assertFalse(resistor.isEmpty())
    }
}
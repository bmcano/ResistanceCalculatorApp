package com.brandoncano.resistancecalculator.components

import com.brandoncano.resistancecalculator.constants.*
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
    fun `resistors as a string`() {
        val resistor = Resistor(BLACK, BROWN, RED, ORANGE, GOLD, SILVER)
        assertEquals("[ $BLACK, $BROWN, $ORANGE, $GOLD ]", resistor.toColorBandString())
        resistor.setNumberOfBands(5)
        assertEquals("[ $BLACK, $BROWN, $RED, $ORANGE, $GOLD ]", resistor.toColorBandString())
        resistor.setNumberOfBands(6)
        assertEquals("[ $BLACK, $BROWN, $RED, $ORANGE, $GOLD, $SILVER ]", resistor.toColorBandString())

        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.ppmValue = "250 $PPM_UNIT"
        assertEquals("[ $BLACK, $BROWN, $RED, $ORANGE, $GOLD, $BLACK ]", resistor.toColorBandString(true))
    }

    @Test
    fun `make displayable text`() {
        val resistor = Resistor()
        resistor.resistance = "12"
        resistor.units = OHMS
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.ppmValue = "250 $PPM_UNIT"
        assertEquals("12 $OHMS ${PLUS_MINUS}5%", resistor.getResistanceText())
        resistor.setNumberOfBands(5)
        assertEquals("12 $OHMS ${PLUS_MINUS}5%", resistor.getResistanceText())
        resistor.setNumberOfBands(6)
        assertEquals("12 $OHMS ${PLUS_MINUS}5%\n250 $PPM_UNIT", resistor.getResistanceText())
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

    @Test
    fun `all sig figs are zero`() {
        val resistor = Resistor(BLACK, BLACK, BLACK)
        assertTrue(resistor.allDigitsZero())
        resistor.setNumberOfBands(5)
        assertTrue(resistor.allDigitsZero())

        resistor.sigFigBandTwo = BROWN
        assertFalse(resistor.allDigitsZero())
        resistor.setNumberOfBands(4)
        assertFalse(resistor.allDigitsZero())
    }
}
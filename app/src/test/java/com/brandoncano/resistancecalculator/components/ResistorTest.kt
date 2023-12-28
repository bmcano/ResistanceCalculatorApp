package com.brandoncano.resistancecalculator.components

import com.brandoncano.resistancecalculator.resistor.Resistor
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S
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
        val resistor = Resistor(C.BLACK, C.BROWN, C.RED, C.ORANGE, C.GOLD, C.SILVER)
        assertEquals("[ ${C.BLACK}, ${C.BROWN}, ${C.ORANGE}, ${C.GOLD} ]", resistor.toColorBandString())
        resistor.setNumberOfBands(5)
        assertEquals("[ ${C.BLACK}, ${C.BROWN}, ${C.RED}, ${C.ORANGE}, ${C.GOLD} ]", resistor.toColorBandString())
        resistor.setNumberOfBands(6)
        assertEquals("[ ${C.BLACK}, ${C.BROWN}, ${C.RED}, ${C.ORANGE}, ${C.GOLD}, ${C.SILVER} ]", resistor.toColorBandString())

        resistor.toleranceValue = "${S.PM}5%"
        resistor.ppmValue = "250 ${S.PPM}"
        assertEquals("[ ${C.BLACK}, ${C.BROWN}, ${C.RED}, ${C.ORANGE}, ${C.GOLD}, ${C.BLACK} ]", resistor.toColorBandString(true))
    }

    @Test
    fun `make displayable text`() {
        val resistor = Resistor()
        resistor.resistance = "12"
        resistor.units = S.Ohms
        resistor.toleranceValue = "${S.PM}5%"
        resistor.ppmValue = "250 ${S.PPM}"
        assertEquals("12 ${S.Ohms} ${S.PM}5%", resistor.getResistanceText())
        resistor.setNumberOfBands(5)
        assertEquals("12 ${S.Ohms} ${S.PM}5%", resistor.getResistanceText())
        resistor.setNumberOfBands(6)
        assertEquals("12 ${S.Ohms} ${S.PM}5%\n250 ${S.PPM}", resistor.getResistanceText())
        resistor.ppmValue = ""
        assertEquals("12 ${S.Ohms} ${S.PM}5%", resistor.getResistanceText())
    }

    @Test
    fun `is the resistor empty`() {
        var resistor = Resistor()
        assertTrue(resistor.isEmpty())
        resistor.setNumberOfBands(5)
        assertTrue(resistor.isEmpty())

        resistor.sigFigBandTwo = C.BLACK
        assertTrue(resistor.isEmpty())

        resistor = Resistor(C.BLACK, C.BROWN, "", C.RED, C.GOLD)
        assertFalse(resistor.isEmpty())

        resistor.setNumberOfBands(5)
        assertTrue(resistor.isEmpty())
        resistor.sigFigBandThree = C.ORANGE
        assertFalse(resistor.isEmpty())
    }

//    @Test
//    fun `all sig figs are zero`() {
//        val resistor = Resistor(C.BLACK, C.BLACK, C.BLACK)
//        assertTrue(resistor.isFirstDigitZero())
//        resistor.setNumberOfBands(5)
//        assertTrue(resistor.isFirstDigitZero())
//
//        resistor.sigFigBandTwo = C.BROWN
//        assertFalse(resistor.isFirstDigitZero())
//        resistor.setNumberOfBands(4)
//        assertFalse(resistor.isFirstDigitZero())
//    }

    @Test
    fun `only valid number of bands as an input`() {
        val resistor = Resistor()
        resistor.setNumberOfBands(4)
        assertEquals(4, resistor.getNumberOfBands())

        resistor.setNumberOfBands(5)
        assertEquals(5, resistor.getNumberOfBands())

        resistor.setNumberOfBands(6)
        assertEquals(6, resistor.getNumberOfBands())

        resistor.setNumberOfBands(3)
        assertEquals(3, resistor.getNumberOfBands())

        resistor.setNumberOfBands(7)
        assertEquals(6, resistor.getNumberOfBands())
    }
}
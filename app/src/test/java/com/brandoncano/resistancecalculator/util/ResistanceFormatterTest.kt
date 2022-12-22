package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistanceFormatterTest {

    private val omega: String = "Ω"
    private val plusMinus: String = "±"

    @Test
    fun emptyResistorTest() {
        assertEquals("Select Colors", ResistanceFormatter.calculate(Resistor()))
    }

    @Test
    fun generalFourBandResistorTest() {
        var resistor = Resistor("Blue", "Gray", "","Black", "Gold")
        assertEquals("68 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Yellow", "Violet", "","Brown", "Gold")
        assertEquals("470 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Orange", "Orange", "","Brown", "Gold")
        assertEquals("330 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Black", "","Brown", "Gold")
        assertEquals("100 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Red", "","Red", "Gold")
        assertEquals("1.2 k$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Black", "","Red", "Gold")
        assertEquals("1.0 k$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("White", "Black", "","Red", "Gold")
        assertEquals("9.0 k$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))
    }

    @Test
    fun leadingZerosFourBandResistorTest() {
        var resistor = Resistor("Black", "Black", "","Brown", "Gold")
        assertEquals("0 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Black", "Green", "","Red", "Gold")
        assertEquals("500 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Black", "Green", "","Green", "Gold")
        assertEquals("500 k$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Black", "Green", "","Gray", "Gold")
        assertEquals("500 M$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Black", "Green", "","Silver", "Gold")
        assertEquals("0.05 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))
    }

    @Test
    fun generalFiveBandResistorTest() {
        var resistor = Resistor("Green", "Brown", "Black", "Gold", "Brown")
        resistor.setNumberOfBands(5)
        assertEquals("51.0 $omega ${plusMinus}1%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Green", "Red", "Blue", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("152 M$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Orange", "Black", "Black", "Blue", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("300 M$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Violet", "Gray", "White", "Blue", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("789 M$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor))
    }

    @Test
    fun leadingZerosFiveBandResistorTest() {

    }

    @Test
    fun generalSixBandResistorTest() {

    }
}
package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistanceFormatterTest {

    private val omega: String = "Ω"
    private val plusMinus: String = "±"

    @Test
    fun randomResistorTest() {
        var resistor = Resistor("Blue", "Gray", "","Black", "Gold")
        assertEquals("68 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor, 4))

        resistor = Resistor("Yellow", "Violet", "","Brown", "Gold")
        assertEquals("470 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor, 4))

        resistor = Resistor("Orange", "Orange", "","Brown", "Gold")
        assertEquals("330 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor, 4))

        resistor = Resistor("Brown", "Black", "","Brown", "Gold")
        assertEquals("100 $omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor, 4))

        resistor = Resistor("Brown", "Red", "","Red", "Gold")
        assertEquals("1.2 k$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor, 4))

        resistor = Resistor("Brown", "Black", "","Red", "Gold")
        assertEquals("1.0 k$omega ${plusMinus}5%", ResistanceFormatter.calculate(resistor, 4))

        resistor = Resistor("Green", "Brown", "Black", "Gold", "Brown")
        assertEquals("51.0 $omega ${plusMinus}1%", ResistanceFormatter.calculate(resistor, 5))
    }
}
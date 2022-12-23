package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistanceFormatterTest {

    private companion object {
        private const val OMEGA: String = "Ω"
        private const val PLUS_MINUS: String = "±"
        private const val DEGREE: String = "°"
    }

    @Test
    fun emptyResistorTest() {
        assertEquals("Select Colors", ResistanceFormatter.calculate(Resistor()))
    }

    @Test
    fun generalFourBandResistorTest() {
        var resistor = Resistor("Blue", "Gray", "","Black", "Gold")
        assertEquals("68 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Yellow", "Violet", "","Brown", "Gold")
        assertEquals("470 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Orange", "Orange", "","Brown", "Gold")
        assertEquals("330 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Black", "","Brown", "Gold")
        assertEquals("100 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Red", "","Red", "Gold")
        assertEquals("1.2 k$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Black", "","Red", "Gold")
        assertEquals("1.0 k$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("White", "Black", "","Red", "Gold")
        assertEquals("9.0 k$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
    }

    @Test
    fun leadingZerosFourBandResistorTest() {
        var resistor = Resistor("Black", "Black", "","Brown", "Gold")
        assertEquals("0 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // red
        resistor = Resistor("Black", "Green", "","Red", "Gold")
        assertEquals("500 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // green
        resistor = Resistor("Black", "Green", "","Green", "Gold")
        assertEquals("500 k$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // gray
        resistor = Resistor("Black", "Green", "","Gray", "Gold")
        assertEquals("500 M$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // silver
        resistor = Resistor("Black", "Green", "","Silver", "Gold")
        assertEquals("0.05 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
    }

    @Test
    fun generalFiveBandResistorTest() {
        var resistor = Resistor("Green", "Brown", "Black", "Gold", "Brown")
        resistor.setNumberOfBands(5)
        assertEquals("51.0 $OMEGA ${PLUS_MINUS}1%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Green", "Red", "Blue", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("152 M$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Orange", "Black", "Black", "Blue", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("300 M$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Violet", "Gray", "White", "Blue", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("789 M$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
    }

    @Test
    fun leadingZerosFiveSixBandResistorTest() {
        var resistor = Resistor("Black", "Black", "Black","Brown", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("0 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // brown
        resistor = Resistor("Black", "Green", "Green","Brown", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("550 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
        resistor = Resistor("Black", "Black", "Green","Brown", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("50 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // red
        resistor = Resistor("Black", "Green", "Green","Red", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("5.5 k$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
        resistor = Resistor("Black", "Black", "Green","Red", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("500 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // yellow
        resistor = Resistor("Black", "Green", "Green","Yellow", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("550 k$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
        resistor = Resistor("Black", "Black", "Green","Yellow", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("50 k$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // green
        resistor = Resistor("Black", "Green", "Green","Green", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("5.5 M$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
        resistor = Resistor("Black", "Black", "Green","Green", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("500 k$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // violet
        resistor = Resistor("Black", "Green", "Green","Violet", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("550 M$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
        resistor = Resistor("Black", "Black", "Green","Violet", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("50 M$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // gray
        resistor = Resistor("Black", "Green", "Green","Gray", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("5.5 G$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
        resistor = Resistor("Black", "Black", "Green","Gray", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("500 M$OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // gold
        resistor = Resistor("Black", "Green", "Green","Gold", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("5.5 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
        resistor = Resistor("Black", "Black", "Green","Gold", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("0.5 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))

        // silver
        resistor = Resistor("Black", "Green", "Green","Silver", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("0.55 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
        resistor = Resistor("Black", "Black", "Green","Silver", "Gold")
        resistor.setNumberOfBands(5)
        assertEquals("0.05 $OMEGA ${PLUS_MINUS}5%", ResistanceFormatter.calculate(resistor))
    }

    @Test
    fun generalSixBandResistorTest() {
        var resistor = Resistor("Brown", "Red", "Green","Red", "Gold", "Blue")
        resistor.setNumberOfBands(6)
        assertEquals("12.5 k$OMEGA ${PLUS_MINUS}5%\n10 ppm/${DEGREE}C", ResistanceFormatter.calculate(resistor))

        resistor = Resistor("Brown", "Red", "Green","Red", "Gold", "Red")
        resistor.setNumberOfBands(6)
        assertEquals("12.5 k$OMEGA ${PLUS_MINUS}5%\n50 ppm/${DEGREE}C", ResistanceFormatter.calculate(resistor))
    }
}
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
    fun fourBandMultiplierTest1() {
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        resistor.units = "M$OMEGA"
        resistor.resistance = "1"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Green", resistor.multiplierBand)

        resistor.units = "k$OMEGA"
        resistor.resistance = "820"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "680"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "560"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "470"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "390"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "330"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "27"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.resistance = "22"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.resistance = "18"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.resistance = "15"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.resistance = "12"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)
    }

    @Test
    fun fourBandMultiplierTest2() {
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        resistor.units = "k$OMEGA"
        resistor.resistance = "8.2"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "6.8"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "5.6"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "4.7"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "3.9"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "3.3"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.units = OMEGA
        resistor.resistance = "820"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        resistor.resistance = "680"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        resistor.resistance = "560"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        resistor.resistance = "470"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        resistor.resistance = "27"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.resistance = "22"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.resistance = "18"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.resistance = "15"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.resistance = "12"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)
    }

    @Test
    fun numericalFourBandInputs() {
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        // Ohms
        resistor.units = OMEGA
        resistor.resistance = "1"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gold", resistor.multiplierBand)

        resistor.resistance = "0.10"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Silver", resistor.multiplierBand)

        resistor.resistance = "10"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.resistance = "100"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        // kOhms
        resistor.units = "k$OMEGA"
        resistor.resistance = "1"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "10"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.resistance = "100"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        // MOhms
        resistor.units = "M$OMEGA"
        resistor.resistance = "1"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Green", resistor.multiplierBand)

        resistor.resistance = "10"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blue", resistor.multiplierBand)

        resistor.resistance = "100"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Violet", resistor.multiplierBand)

        // GOhms
        resistor.units = "G$OMEGA"
        resistor.resistance = "1"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gray", resistor.multiplierBand)

        resistor.resistance = "10"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("White", resistor.multiplierBand)
    }

    @Test
    fun numericalFiveBandInputs() {
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(5)

        // Ohms
        resistor.units = OMEGA
        resistor.resistance = "12.3"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gold", resistor.multiplierBand)

        resistor.resistance = "1.23"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Silver", resistor.multiplierBand)

        resistor.resistance = "123"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        // kOhms
        resistor.units = "k$OMEGA"
        resistor.resistance = "1.23"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        resistor.resistance = "12.3"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "123"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        // MOhms
        resistor.units = "M$OMEGA"
        resistor.resistance = "1.23"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "12.3"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Green", resistor.multiplierBand)

        resistor.resistance = "123"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blue", resistor.multiplierBand)

        // GOhms
        resistor.units = "G$OMEGA"
        resistor.resistance = "1.23"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Violet", resistor.multiplierBand)

        resistor.resistance = "12.3"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gray", resistor.multiplierBand)

        resistor.resistance = "123"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("White", resistor.multiplierBand)
    }
}

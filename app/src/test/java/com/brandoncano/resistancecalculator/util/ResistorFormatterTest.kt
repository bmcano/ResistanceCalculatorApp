package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.Resistor
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistorFormatterTest {

    companion object {
        private const val OMEGA: String = "Ω"
        private const val PLUS_MINUS: String = "±"
    }

    @Test
    fun invalidInputs() {
        val resistor = Resistor()
        ResistorFormatter.generateResistor(resistor)
        assertEquals("", resistor.multiplierBand)

        resistor.resistance = "NotValid"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("", resistor.multiplierBand)
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

    @Test
    fun decimalFourBandInputs() {
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        resistor.units = OMEGA
        resistor.resistance = "0.70"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Silver", resistor.multiplierBand)

        resistor.resistance = "1.7"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gold", resistor.multiplierBand)

        resistor.resistance = "17."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.units = "k$OMEGA"
        resistor.resistance = "0.70"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        resistor.resistance = "1.7"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "17."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.units = "M$OMEGA"
        resistor.resistance = "0.70"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "1.7"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Green", resistor.multiplierBand)

        resistor.resistance = "17."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blue", resistor.multiplierBand)

        resistor.units = "G$OMEGA"
        resistor.resistance = "0.70"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Violet", resistor.multiplierBand)

        resistor.resistance = "1.7"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gray", resistor.multiplierBand)

        resistor.resistance = "17."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("White", resistor.multiplierBand)
    }

    @Test
    fun decimalFiveBandInputs() {
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(5)

        resistor.units = OMEGA
        resistor.resistance = "6.89"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Silver", resistor.multiplierBand)

        resistor.resistance = "68.9"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gold", resistor.multiplierBand)

        resistor.resistance = "689."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.units = "k$OMEGA"
        resistor.resistance = "0.689"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.resistance = "6.89"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        resistor.resistance = "68.9"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "689."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.units = "M$OMEGA"
        resistor.resistance = "0.689"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.resistance = "6.89"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "68.9"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Green", resistor.multiplierBand)

        resistor.resistance = "689"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blue", resistor.multiplierBand)

        resistor.units = "G$OMEGA"
        resistor.resistance = "0.689"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blue", resistor.multiplierBand)

        resistor.resistance = "6.89"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Violet", resistor.multiplierBand)

        resistor.resistance = "68.9"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gray", resistor.multiplierBand)

        resistor.resistance = "689."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("White", resistor.multiplierBand)
    }

    @Test
    fun leadingZeroBandsTest() {
        val resistor = Resistor()
        resistor.units = OMEGA

        // 4 bands
        resistor.resistance = "0.12"
        ResistorFormatter.generateResistor(resistor)
        var expectedResult = Resistor("Brown", "Red", "", "Silver")
        expectedResult.units = OMEGA
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.10"
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Black", "", "Silver")
        expectedResult.units = OMEGA
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.01"
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Black", "Brown", "", "Silver")
        expectedResult.units = OMEGA
        assertEquals(expectedResult, resistor)

        // 5 bands
        resistor.setNumberOfBands(5)
        resistor.units = "k$OMEGA"
        resistor.resistance = "0.123"
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Red", "Orange", "Black")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = "k$OMEGA"
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.12"
        resistor.units = OMEGA
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Red", "Black", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OMEGA
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.01"
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Black", "Brown", "Black", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OMEGA
        assertEquals(expectedResult, resistor)
    }

    // Note: we might need to change these to adhere to proper unit convention
    // i.e: 10000 Ω -> 10 kΩ
    @Test
    fun longInputResistanceTest() {
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        resistor.units = OMEGA
        resistor.resistance = "1000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand) // 1000 Ω

        resistor.resistance = "10000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand) // 10000 Ω

        resistor.setNumberOfBands(5)
        resistor.units = OMEGA
        resistor.resistance = "1000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand) // 1000 Ω

        resistor.resistance = "10000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand) // 10000 Ω

        resistor.setNumberOfBands(6)
        resistor.units = OMEGA
        resistor.resistance = "1000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand) // 1000 Ω

        resistor.resistance = "10000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand) // 10000 Ω
    }

    @Test
    fun elseClausesTest() {
        // these are not possible outputs, only for code coverage
        val resistor = Resistor()
        resistor.resistance = "12"
        resistor.units = "notValid"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.resistance = "123.0"
        resistor.units = "G$OMEGA"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blank", resistor.multiplierBand)

        resistor.resistance = "1234.0"
        resistor.setNumberOfBands(5)
        resistor.units = "G$OMEGA"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blank", resistor.multiplierBand)

        resistor.resistance = "1234"
        resistor.setNumberOfBands(5)
        resistor.units = "G$OMEGA"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blank", resistor.multiplierBand)
    }
}

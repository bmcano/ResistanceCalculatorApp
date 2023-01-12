package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.constants.OHMS
import com.brandoncano.resistancecalculator.constants.PLUS_MINUS
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistorFormatterTest {

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

        resistor.units = "M$OHMS"
        resistor.resistance = "1"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Green", resistor.multiplierBand)

        resistor.units = "k$OHMS"
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

        resistor.units = "k$OHMS"
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

        resistor.units = OHMS
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
        resistor.units = OHMS
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
        resistor.units = "k$OHMS"
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
        resistor.units = "M$OHMS"
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
        resistor.units = "G$OHMS"
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
        resistor.units = OHMS
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
        resistor.units = "k$OHMS"
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
        resistor.units = "M$OHMS"
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
        resistor.units = "G$OHMS"
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

        resistor.units = OHMS
        resistor.resistance = "0.70"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Silver", resistor.multiplierBand)

        resistor.resistance = "1.7"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gold", resistor.multiplierBand)

        resistor.resistance = "17."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.units = "k$OHMS"
        resistor.resistance = "0.70"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand)

        resistor.resistance = "1.7"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand)

        resistor.resistance = "17."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand)

        resistor.units = "M$OHMS"
        resistor.resistance = "0.70"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Yellow", resistor.multiplierBand)

        resistor.resistance = "1.7"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Green", resistor.multiplierBand)

        resistor.resistance = "17."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blue", resistor.multiplierBand)

        resistor.units = "G$OHMS"
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

        resistor.units = OHMS
        resistor.resistance = "6.89"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Silver", resistor.multiplierBand)

        resistor.resistance = "68.9"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Gold", resistor.multiplierBand)

        resistor.resistance = "689."
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Black", resistor.multiplierBand)

        resistor.units = "k$OHMS"
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

        resistor.units = "M$OHMS"
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

        resistor.units = "G$OHMS"
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
    fun leadingZeroBandsFourBandTest() {
        // 4 -> 0.0x, 0.x0, 0.xy, x.y
        val resistor = Resistor()
        resistor.units = OHMS

        resistor.resistance = "0.12"
        ResistorFormatter.generateResistor(resistor)
        var expectedResult = Resistor("Brown", "Red", "", "Silver")
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.10"
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Black", "", "Silver")
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.01"
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Black", "Brown", "", "Silver")
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "1.2"
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Red", "", "Gold")
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)
    }

    @Test
    fun leadingZerosFiveBandTest() {
        // 5 -> 0.0x, 0.x0, x.00, 0.xy, x.y0, x.0y, x.yz
        val resistor = Resistor()
        resistor.units = OHMS

        resistor.units = OHMS
        resistor.resistance = "1.23"
        resistor.setNumberOfBands(5)
        ResistorFormatter.generateResistor(resistor)
        var expectedResult = Resistor("Brown", "Red", "Orange", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = "k$OHMS"
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.01"
        resistor.units = OHMS
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Black", "Black", "Brown", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.10"
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Black", "Brown", "Black", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "1.00"
        resistor.units = OHMS
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Black", "Black", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "0.12"
        resistor.units = OHMS
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Black", "Brown", "Red", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "1.20"
        resistor.units = OHMS
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Red", "Black", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "1.02"
        resistor.units = OHMS
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Black", "Red", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)

        resistor.resistance = "1.23"
        resistor.units = OHMS
        ResistorFormatter.generateResistor(resistor)
        expectedResult = Resistor("Brown", "Red", "Orange", "Silver")
        expectedResult.setNumberOfBands(5)
        expectedResult.units = OHMS
        assertEquals(expectedResult, resistor)
    }

    // Note: we might need to change these to adhere to proper unit convention
    // i.e: 10000 Ω -> 10 kΩ
    @Test
    fun longInputResistanceTest() {
        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        resistor.units = OHMS
        resistor.resistance = "1000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand) // 1000 Ω

        resistor.resistance = "10000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Orange", resistor.multiplierBand) // 10000 Ω

        resistor.setNumberOfBands(5)
        resistor.units = OHMS
        resistor.resistance = "1000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Brown", resistor.multiplierBand) // 1000 Ω

        resistor.resistance = "10000"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Red", resistor.multiplierBand) // 10000 Ω

        resistor.setNumberOfBands(6)
        resistor.units = OHMS
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
        resistor.units = "G$OHMS"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blank", resistor.multiplierBand)

        resistor.resistance = "1234.0"
        resistor.setNumberOfBands(5)
        resistor.units = "G$OHMS"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blank", resistor.multiplierBand)

        resistor.resistance = "1234"
        resistor.setNumberOfBands(5)
        resistor.units = "G$OHMS"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("Blank", resistor.multiplierBand)
    }
}

package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.constants.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Note: This will only test valid number inputs, since only valid inputs are allowed through this.
 */
class ResistorFormatterTest {

    @Test
    fun `non number resistance values`() {
        val resistor = Resistor()
        ResistorFormatter.generateResistor(resistor)
        assertEquals("", resistor.multiplierBand)

        resistor.resistance = "NotValid"
        ResistorFormatter.generateResistor(resistor)
        assertEquals("", resistor.multiplierBand)
    }

    @Test
    fun `numerical input for multiplier band of four band resistors`() {
        val resistances = listOf(
            "1", "10", "100", "1000", "10000",
            "1", "10", "100", "1000", "10000",
            "1", "10", "100", "1000", "10000",
            "1", "10",
            "23", "230", "2300", "23000",
            "23", "230", "2300", "23000",
            "23", "230", "2300", "23000",
            "23", "230" // blank, since invalid
        )

        val units = listOf(
            OHMS, OHMS, OHMS, OHMS, OHMS,
            "k$OHMS", "k$OHMS", "k$OHMS", "k$OHMS", "k$OHMS",
            "M$OHMS", "M$OHMS", "M$OHMS", "M$OHMS", "M$OHMS",
            "G$OHMS", "G$OHMS",
            OHMS, OHMS, OHMS, OHMS,
            "k$OHMS", "k$OHMS", "k$OHMS", "k$OHMS",
            "M$OHMS", "M$OHMS", "M$OHMS", "M$OHMS",
            "G$OHMS", "G$OHMS"
        )

        val answers = listOf(
            GOLD, BLACK, BROWN, RED, ORANGE,
            RED, ORANGE, YELLOW, GREEN, BLUE,
            GREEN, BLUE, VIOLET, GRAY, WHITE,
            GRAY, WHITE,
            BLACK, BROWN, RED, ORANGE,
            ORANGE, YELLOW, GREEN, BLUE,
            BLUE, VIOLET, GRAY, WHITE,
            WHITE, BLANK
        )

        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.multiplierBand)
        }
    }

    @Test
    fun `numerical input for multiplier band of five band resistors`() {
        val resistances = listOf(
            "1", "10", "100", "1000", "10000",
            "1", "10", "100", "1000", "10000",
            "1", "10", "100", "1000", "10000",
            "1", "10", "100",
            "23", "230", "2300", "23000",
            "23", "230", "2300", "23000",
            "23", "230", "2300", "23000",
            "23", "230",
            "456", "4560", "45600",
            "456", "4560", "45600",
            "456", "4560", "45600",
            "456", "4560" // blank, since invalid
        )

        val units = listOf(
            OHMS, OHMS, OHMS, OHMS, OHMS,
            "k$OHMS", "k$OHMS", "k$OHMS", "k$OHMS", "k$OHMS",
            "M$OHMS", "M$OHMS", "M$OHMS", "M$OHMS", "M$OHMS",
            "G$OHMS", "G$OHMS", "G$OHMS",
            OHMS, OHMS, OHMS, OHMS,
            "k$OHMS", "k$OHMS", "k$OHMS", "k$OHMS",
            "M$OHMS", "M$OHMS", "M$OHMS", "M$OHMS",
            "G$OHMS", "G$OHMS",
            OHMS, OHMS, OHMS,
            "k$OHMS", "k$OHMS", "k$OHMS",
            "M$OHMS", "M$OHMS", "M$OHMS",
            "G$OHMS", "G$OHMS"
        )

        val answers = listOf(
            SILVER, GOLD, BLACK, BROWN, RED,
            BROWN, RED, ORANGE, YELLOW, GREEN,
            YELLOW, GREEN, BLUE, VIOLET, GRAY,
            VIOLET, GRAY, WHITE,
            GOLD, BLACK, BROWN, RED,
            RED, ORANGE, YELLOW, GREEN,
            GREEN, BLUE, VIOLET, GRAY,
            GRAY, WHITE,
            BLACK, BROWN, RED,
            ORANGE, YELLOW, GREEN,
            BLUE, VIOLET, GRAY,
            WHITE, BLANK
        )

        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(5)

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.multiplierBand)
        }
    }

    @Test
    fun `decimal inputs for multiplier band of four band resistors`() {
        val resistances = listOf(
            "1.0", "10.0", "1.0", "10.0", "1.0", "10.0", "1.0", "10.0",
            "23.0", "23.0", "23.0", "23.0", "23.", "23.", "23.", "23.",
            "4.5", "4.5", "4.5", "4.5",
        )

        val units = listOf(
            OHMS, OHMS, "k$OHMS", "k$OHMS", "M$OHMS", "M$OHMS", "G$OHMS", "G$OHMS",
            OHMS, "k$OHMS", "M$OHMS", "G$OHMS", OHMS, "k$OHMS", "M$OHMS", "G$OHMS",
            OHMS, "k$OHMS", "M$OHMS", "G$OHMS",
        )

        val answers = listOf(
            GOLD, BLACK, RED, ORANGE, GREEN, BLUE, GRAY, WHITE,
            BLACK, ORANGE, BLUE, WHITE, BLACK, ORANGE, BLUE, WHITE,
            GOLD, RED, GREEN, GRAY,
        )

        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.multiplierBand)
        }
    }

    @Test
    fun `decimal inputs for multiplier band of five band resistors`() {
        val resistances = listOf(
            "1.0", "10.0", "100.0", "1.0", "10.0", "100.0", "1.0", "10.0", "100.0", "1.0", "10.0", "100.0",
            "23.0", "230.0", "23.0", "230.0", "23.0", "230.0", "23.0", "230.0",
            "456.0", "456.0", "456.0", "456.0", "456.", "456.", "456.", "456.",
            "4.5", "4.5", "4.50", "4.50", "4.56", "4.56", "4.56", "4.56",
            "78.9", "78.9", "78.9", "78.9", "70.9", "70.9", "70.9", "70.9", "1.00"
        )

        val units = listOf(
            OHMS, OHMS, OHMS, "k$OHMS", "k$OHMS", "k$OHMS", "M$OHMS", "M$OHMS", "M$OHMS", "G$OHMS", "G$OHMS", "G$OHMS",
            OHMS, OHMS, "k$OHMS", "k$OHMS", "M$OHMS", "M$OHMS", "G$OHMS", "G$OHMS",
            OHMS, "k$OHMS", "M$OHMS", "G$OHMS", OHMS, "k$OHMS", "M$OHMS", "G$OHMS",
            OHMS, "k$OHMS", "M$OHMS", "G$OHMS", OHMS, "k$OHMS", "M$OHMS", "G$OHMS",
            OHMS, "k$OHMS", "M$OHMS", "G$OHMS", OHMS, "k$OHMS", "M$OHMS", "G$OHMS", OHMS
        )

        val answers = listOf(
            SILVER, GOLD, BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE,
            GOLD, BLACK, RED, ORANGE, GREEN, BLUE, GRAY, WHITE,
            BLACK, ORANGE, BLUE, WHITE, BLACK, ORANGE, BLUE, WHITE,
            SILVER, BROWN, YELLOW, VIOLET, SILVER, BROWN, YELLOW, VIOLET,
            GOLD, RED, GREEN, GRAY, GOLD, RED, GREEN, GRAY, SILVER
        )

        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(5)

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.multiplierBand)
        }
    }

    @Test
    fun `leading zeros as inputs for four band resistors`() {
        val resistances = listOf(
            "0.1", "0.10", "0.12", "0.1", "0.10", "0.12", "0.1", "0.10", "0.12", "0.1", "0.10", "0.12",
            "0.01", "0.01", "0.01", "0.01"
        )

        var units = listOf(
            OHMS, OHMS, OHMS, "k$OHMS", "k$OHMS", "k$OHMS", "M$OHMS", "M$OHMS", "M$OHMS", "G$OHMS", "G$OHMS", "G$OHMS",
            OHMS, "k$OHMS", "M$OHMS", "G$OHMS"
        )

        val answers = listOf(
            SILVER, SILVER, SILVER, BROWN, BROWN, BROWN, YELLOW, YELLOW, YELLOW, VIOLET, VIOLET, VIOLET,
            SILVER, BLACK, ORANGE, BLUE
        )

        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(4)

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.multiplierBand)
        }

        // test resistor
        val resistors = listOf(
            Resistor(BLACK, BROWN, "", SILVER, GOLD), // 0.01 Ohms
            Resistor(BROWN, BLACK, "", BLACK, GOLD), // 0.01 kOhms -> 10 Ohms
            Resistor(BROWN, BLACK, "", ORANGE, GOLD), // 0.01 MOhms -> 10 kOhms
            Resistor(BROWN, BLACK, "", BLUE, GOLD), // 0.01 GOhms -> 10 MOhms
        )

        resistor.resistance = "0.01"
        resistor.toleranceBand = GOLD
        units = listOf(OHMS, "k$OHMS", "M$OHMS", "G$OHMS")
        for (i in resistors.indices) {
            resistor.units = units[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(resistors[i], resistor)
        }
    }

    @Test
    fun `leading zeros as inputs for five band resistors`() {
        val resistances = listOf(
            "0.10", "0.12", "0.10", "0.12", "0.10", "0.12", "0.10", "0.12",
            "0.01", "0.01", "0.01", "0.01"
        )

        var units = listOf(
            OHMS, OHMS, "k$OHMS", "k$OHMS", "M$OHMS", "M$OHMS", "G$OHMS", "G$OHMS",
            OHMS, "k$OHMS", "M$OHMS", "G$OHMS",
        )

        val answers = listOf(
            SILVER, SILVER, BLACK, BLACK, ORANGE, ORANGE, BLUE, BLUE,
            SILVER, GOLD, RED, GREEN
        )

        val resistor = Resistor()
        resistor.toleranceValue = "${PLUS_MINUS}5%"
        resistor.setNumberOfBands(5)

        // test multiplier
        for (i in answers.indices) {
            resistor.units = units[i]
            resistor.resistance = resistances[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(answers[i], resistor.multiplierBand)
        }

        // test resistor
        val resistors = listOf(
            Resistor(BLACK, BLACK, BROWN, SILVER, GOLD), // 0.01 Ohms
            Resistor(BROWN, BLACK, BLACK, GOLD, GOLD), // 0.01 kOhms -> 10 Ohms
            Resistor(BROWN, BLACK, BLACK, RED, GOLD), // 0.01 MOhms -> 10 kOhms
            Resistor(BROWN, BLACK, BLACK, GREEN, GOLD), // 0.01 GOhms -> 10 MOhms
        )

        resistor.resistance = "0.01"
        resistor.toleranceBand = GOLD
        units = listOf(OHMS, "k$OHMS", "M$OHMS", "G$OHMS")
        for (i in resistors.indices) {
            resistor.units = units[i]
            ResistorFormatter.generateResistor(resistor)
            assertEquals(resistors[i], resistor)
        }
    }

    @Test
    fun `not valid unit string`() {
        val resistor = Resistor()
        resistor.resistance = "12"
        resistor.units = "notValid"
        ResistorFormatter.generateResistor(resistor)
        assertEquals(BLACK, resistor.multiplierBand)
    }
}

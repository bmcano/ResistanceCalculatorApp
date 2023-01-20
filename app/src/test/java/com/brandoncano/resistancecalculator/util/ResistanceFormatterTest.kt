package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.constants.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistanceFormatterTest {

    @Test
    fun `empty resistor`() {
        assertEquals("Select Colors", ResistanceFormatter.calculate(Resistor()))
    }

    @Test
    fun `four band resistors resistance text testing`() {
        val resistors = listOf(
            // general
            Resistor(BLUE, GRAY, "", BLACK, GOLD),
            Resistor(YELLOW, VIOLET, "", BROWN, GOLD),
            Resistor(ORANGE, ORANGE, "", BROWN, GOLD),
            Resistor(BROWN, BLACK, "", BROWN, GOLD),
            Resistor(BROWN, RED, "", RED, GOLD),
            Resistor(BROWN, BLACK, "", RED, GOLD),
            Resistor(WHITE, BLACK, "", RED, GOLD),
            // leading zero
            Resistor(BLACK, BLACK, "", BROWN, GOLD),
            Resistor(BLACK, GREEN, "", RED, GOLD),
            Resistor(BLACK, GREEN, "", GREEN, GOLD),
            Resistor(BLACK, GREEN, "", GRAY, GOLD),
            Resistor(BLACK, GREEN, "", SILVER, GOLD)
        )

        val answers = listOf(
            "68 $OHMS ${PLUS_MINUS}5%", "470 $OHMS ${PLUS_MINUS}5%", "330 $OHMS ${PLUS_MINUS}5%",
            "100 $OHMS ${PLUS_MINUS}5%", "1.2 k$OHMS ${PLUS_MINUS}5%", "1.0 k$OHMS ${PLUS_MINUS}5%",
            "9.0 k$OHMS ${PLUS_MINUS}5%",

            "0 $OHMS ${PLUS_MINUS}5%", "500 $OHMS ${PLUS_MINUS}5%", "500 k$OHMS ${PLUS_MINUS}5%",
            "500 M$OHMS ${PLUS_MINUS}5%", "0.05 $OHMS ${PLUS_MINUS}5%",
        )

        for (i in answers.indices) {
            assertEquals(answers[i], ResistanceFormatter.calculate(resistors[i]))
        }
    }

    @Test
    fun `five band resistors resistance text testing`() {
        val resistors = listOf(
            // general
            Resistor(GREEN, BROWN, BLACK, GOLD, BROWN),
            Resistor(BROWN, GREEN, RED, BLUE, GOLD),
            Resistor(ORANGE, BLACK, BLACK, BLUE, GOLD),
            Resistor(VIOLET, GRAY, WHITE, BLUE, GOLD),
            // leading zero
            Resistor(BLACK, BLACK, BLACK, BROWN, GOLD),
            Resistor(BLACK, GREEN, GREEN, BROWN, GOLD), Resistor(BLACK, BLACK, GREEN, BROWN, GOLD),
            Resistor(BLACK, GREEN, GREEN, RED, GOLD), Resistor(BLACK, BLACK, GREEN, RED, GOLD),
            Resistor(BLACK, GREEN, GREEN, YELLOW, GOLD), Resistor(BLACK, BLACK, GREEN, YELLOW, GOLD),
            Resistor(BLACK, GREEN, GREEN, GREEN, GOLD), Resistor(BLACK, BLACK, GREEN, GREEN, GOLD),
            Resistor(BLACK, GREEN, GREEN, VIOLET, GOLD), Resistor(BLACK, BLACK, GREEN, VIOLET, GOLD),
            Resistor(BLACK, GREEN, GREEN, GRAY, GOLD), Resistor(BLACK, BLACK, GREEN, GRAY, GOLD),
            Resistor(BLACK, GREEN, GREEN, GOLD, GOLD), Resistor(BLACK, BLACK, GREEN, GOLD, GOLD),
            Resistor(BLACK, GREEN, GREEN, SILVER, GOLD), Resistor(BLACK, BLACK, GREEN, SILVER, GOLD),
        )

        val answers = listOf(
            "51.0 $OHMS ${PLUS_MINUS}1%", "152 M$OHMS ${PLUS_MINUS}5%", "300 M$OHMS ${PLUS_MINUS}5%",
            "789 M$OHMS ${PLUS_MINUS}5%",

            "0 $OHMS ${PLUS_MINUS}5%",
            "550 $OHMS ${PLUS_MINUS}5%", "50 $OHMS ${PLUS_MINUS}5%",
            "5.5 k$OHMS ${PLUS_MINUS}5%", "500 $OHMS ${PLUS_MINUS}5%",
            "550 k$OHMS ${PLUS_MINUS}5%", "50 k$OHMS ${PLUS_MINUS}5%",
            "5.5 M$OHMS ${PLUS_MINUS}5%", "500 k$OHMS ${PLUS_MINUS}5%",
            "550 M$OHMS ${PLUS_MINUS}5%", "50 M$OHMS ${PLUS_MINUS}5%",
            "5.5 G$OHMS ${PLUS_MINUS}5%", "500 M$OHMS ${PLUS_MINUS}5%",
            "5.5 $OHMS ${PLUS_MINUS}5%", "0.5 $OHMS ${PLUS_MINUS}5%",
            "0.55 $OHMS ${PLUS_MINUS}5%", "0.05 $OHMS ${PLUS_MINUS}5%"
        )

        for (i in answers.indices) {
            resistors[i].setNumberOfBands(5)
            assertEquals(answers[i], ResistanceFormatter.calculate(resistors[i]))
        }
    }

    @Test
    fun `six band resistors ppm bands`() {
        val ppmBands = listOf(
            BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY
        )

        val answers = listOf(
            "12.5 k$OHMS ${PLUS_MINUS}5%\n250 $PPM_UNIT",
            "12.5 k$OHMS ${PLUS_MINUS}5%\n100 $PPM_UNIT",
            "12.5 k$OHMS ${PLUS_MINUS}5%\n50 $PPM_UNIT",
            "12.5 k$OHMS ${PLUS_MINUS}5%\n15 $PPM_UNIT",
            "12.5 k$OHMS ${PLUS_MINUS}5%\n25 $PPM_UNIT",
            "12.5 k$OHMS ${PLUS_MINUS}5%\n20 $PPM_UNIT",
            "12.5 k$OHMS ${PLUS_MINUS}5%\n10 $PPM_UNIT",
            "12.5 k$OHMS ${PLUS_MINUS}5%\n5 $PPM_UNIT",
            "12.5 k$OHMS ${PLUS_MINUS}5%\n1 $PPM_UNIT",
        )

        val resistor = Resistor(BROWN, RED, GREEN, RED, GOLD, BLACK)
        resistor.setNumberOfBands(6)
        for (i in answers.indices) {
            resistor.ppmBand = ppmBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }
    }

    @Test
    fun `leading zeros for 4 bands`() {
        val multiplierBands = listOf(
            BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE, GOLD, SILVER
        )

        val answers = listOf(
            "1 $OHMS ${PLUS_MINUS}5%", "10 $OHMS ${PLUS_MINUS}5%", "100 $OHMS ${PLUS_MINUS}5%",
            "1 k$OHMS ${PLUS_MINUS}5%", "10 k$OHMS ${PLUS_MINUS}5%", "100 k$OHMS ${PLUS_MINUS}5%",
            "1 M$OHMS ${PLUS_MINUS}5%", "10 M$OHMS ${PLUS_MINUS}5%", "100 M$OHMS ${PLUS_MINUS}5%",
            "1 G$OHMS ${PLUS_MINUS}5%", "0.1 $OHMS ${PLUS_MINUS}5%", "0.01 $OHMS ${PLUS_MINUS}5%"
        )

        val resistor = Resistor(BLACK, BROWN, "", BLACK, GOLD)
        for (i in answers.indices) {
            resistor.multiplierBand = multiplierBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }
    }

    @Test
    fun `leading zeros for 5 bands`() {
        val multiplierBands = listOf(
            BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE, GOLD, SILVER
        )

        var answers = listOf(
            "1 $OHMS ${PLUS_MINUS}5%", "10 $OHMS ${PLUS_MINUS}5%", "100 $OHMS ${PLUS_MINUS}5%",
            "1 k$OHMS ${PLUS_MINUS}5%", "10 k$OHMS ${PLUS_MINUS}5%", "100 k$OHMS ${PLUS_MINUS}5%",
            "1 M$OHMS ${PLUS_MINUS}5%", "10 M$OHMS ${PLUS_MINUS}5%", "100 M$OHMS ${PLUS_MINUS}5%",
            "1 G$OHMS ${PLUS_MINUS}5%", "0.1 $OHMS ${PLUS_MINUS}5%", "0.01 $OHMS ${PLUS_MINUS}5%"
        )

        var resistor = Resistor(BLACK, BLACK, BROWN, BLACK, GOLD)
        resistor.setNumberOfBands(5)
        for (i in answers.indices) {
            resistor.multiplierBand = multiplierBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }

        answers = listOf(
            "11 $OHMS ${PLUS_MINUS}5%", "110 $OHMS ${PLUS_MINUS}5%",
            "1.1 k$OHMS ${PLUS_MINUS}5%", "11 k$OHMS ${PLUS_MINUS}5%", "110 k$OHMS ${PLUS_MINUS}5%",
            "1.1 M$OHMS ${PLUS_MINUS}5%", "11 M$OHMS ${PLUS_MINUS}5%", "110 M$OHMS ${PLUS_MINUS}5%",
            "1.1 G$OHMS ${PLUS_MINUS}5%", "11 G$OHMS ${PLUS_MINUS}5%",
            "1.1 $OHMS ${PLUS_MINUS}5%", "0.11 $OHMS ${PLUS_MINUS}5%"
        )

        resistor = Resistor(BLACK, BROWN, BROWN, BLACK, GOLD)
        resistor.setNumberOfBands(5)
        for (i in answers.indices) {
            resistor.multiplierBand = multiplierBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }
    }
}
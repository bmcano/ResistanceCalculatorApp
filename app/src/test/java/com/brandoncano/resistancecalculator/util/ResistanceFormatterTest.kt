package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S
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
            Resistor(C.BLUE, C.GRAY, "", C.BLACK, C.GOLD),
            Resistor(C.YELLOW, C.VIOLET, "", C.BROWN, C.GOLD),
            Resistor(C.ORANGE, C.ORANGE, "", C.BROWN, C.GOLD),
            Resistor(C.BROWN, C.BLACK, "", C.BROWN, C.GOLD),
            Resistor(C.BROWN, C.RED, "", C.RED, C.GOLD),
            Resistor(C.BROWN, C.BLACK, "", C.RED, C.GOLD),
            Resistor(C.WHITE, C.BLACK, "", C.RED, C.GOLD),
            // leading zero
            Resistor(C.BLACK, C.BLACK, "", C.BROWN, C.GOLD),
            Resistor(C.BLACK, C.GREEN, "", C.RED, C.GOLD),
            Resistor(C.BLACK, C.GREEN, "", C.GREEN, C.GOLD),
            Resistor(C.BLACK, C.GREEN, "", C.GRAY, C.GOLD),
            Resistor(C.BLACK, C.GREEN, "", C.SILVER, C.GOLD)
        )

        val answers = listOf(
            "68 ${S.Ohms} ${S.PM}5%", "470 ${S.Ohms} ${S.PM}5%", "330 ${S.Ohms} ${S.PM}5%",
            "100 ${S.Ohms} ${S.PM}5%", "1.2 ${S.kOhms} ${S.PM}5%", "1.0 ${S.kOhms} ${S.PM}5%",
            "9.0 ${S.kOhms} ${S.PM}5%",

            "0 ${S.Ohms} ${S.PM}5%", "500 ${S.Ohms} ${S.PM}5%", "500 ${S.kOhms} ${S.PM}5%",
            "500 ${S.MOhms} ${S.PM}5%", "0.05 ${S.Ohms} ${S.PM}5%",
        )

        for (i in answers.indices) {
            assertEquals(answers[i], ResistanceFormatter.calculate(resistors[i]))
        }
    }

    @Test
    fun `five band resistors resistance text testing`() {
        val resistors = listOf(
            // general
            Resistor(C.GREEN, C.BROWN, C.BLACK, C.GOLD, C.BROWN),
            Resistor(C.BROWN, C.GREEN, C.RED, C.BLUE, C.GOLD),
            Resistor(C.ORANGE, C.BLACK, C.BLACK, C.BLUE, C.GOLD),
            Resistor(C.VIOLET, C.GRAY, C.WHITE, C.BLUE, C.GOLD),
            // leading zero
            Resistor(C.BLACK, C.BLACK, C.BLACK, C.BROWN, C.GOLD),
            Resistor(C.BLACK, C.GREEN, C.GREEN, C.BROWN, C.GOLD), Resistor(C.BLACK, C.BLACK, C.GREEN, C.BROWN, C.GOLD),
            Resistor(C.BLACK, C.GREEN, C.GREEN, C.RED, C.GOLD), Resistor(C.BLACK, C.BLACK, C.GREEN, C.RED, C.GOLD),
            Resistor(C.BLACK, C.GREEN, C.GREEN, C.YELLOW, C.GOLD), Resistor(C.BLACK, C.BLACK, C.GREEN, C.YELLOW, C.GOLD),
            Resistor(C.BLACK, C.GREEN, C.GREEN, C.GREEN, C.GOLD), Resistor(C.BLACK, C.BLACK, C.GREEN, C.GREEN, C.GOLD),
            Resistor(C.BLACK, C.GREEN, C.GREEN, C.VIOLET, C.GOLD), Resistor(C.BLACK, C.BLACK, C.GREEN, C.VIOLET, C.GOLD),
            Resistor(C.BLACK, C.GREEN, C.GREEN, C.GRAY, C.GOLD), Resistor(C.BLACK, C.BLACK, C.GREEN, C.GRAY, C.GOLD),
            Resistor(C.BLACK, C.GREEN, C.GREEN, C.GOLD, C.GOLD), Resistor(C.BLACK, C.BLACK, C.GREEN, C.GOLD, C.GOLD),
            Resistor(C.BLACK, C.GREEN, C.GREEN, C.SILVER, C.GOLD), Resistor(C.BLACK, C.BLACK, C.GREEN, C.SILVER, C.GOLD),
        )

        val answers = listOf(
            "51.0 ${S.Ohms} ${S.PM}1%", "152 ${S.MOhms} ${S.PM}5%", "300 ${S.MOhms} ${S.PM}5%",
            "789 ${S.MOhms} ${S.PM}5%",

            "0 ${S.Ohms} ${S.PM}5%",
            "550 ${S.Ohms} ${S.PM}5%", "50 ${S.Ohms} ${S.PM}5%",
            "5.5 ${S.kOhms} ${S.PM}5%", "500 ${S.Ohms} ${S.PM}5%",
            "550 ${S.kOhms} ${S.PM}5%", "50 ${S.kOhms} ${S.PM}5%",
            "5.5 ${S.MOhms} ${S.PM}5%", "500 ${S.kOhms} ${S.PM}5%",
            "550 ${S.MOhms} ${S.PM}5%", "50 ${S.MOhms} ${S.PM}5%",
            "5.5 ${S.GOhms} ${S.PM}5%", "500 ${S.MOhms} ${S.PM}5%",
            "5.5 ${S.Ohms} ${S.PM}5%", "0.5 ${S.Ohms} ${S.PM}5%",
            "0.55 ${S.Ohms} ${S.PM}5%", "0.05 ${S.Ohms} ${S.PM}5%"
        )

        for (i in answers.indices) {
            resistors[i].setNumberOfBands(5)
            assertEquals(answers[i], ResistanceFormatter.calculate(resistors[i]))
        }
    }

    @Test
    fun `six band resistors ppm bands`() {
        val ppmBands = listOf(
            C.BLACK, C.BROWN, C.RED, C.ORANGE, C.YELLOW, C.GREEN, C.BLUE, C.VIOLET, C.GRAY
        )

        val answers = listOf(
            "12.5 ${S.kOhms} ${S.PM}5%\n250 ${S.PPM}",
            "12.5 ${S.kOhms} ${S.PM}5%\n100 ${S.PPM}",
            "12.5 ${S.kOhms} ${S.PM}5%\n50 ${S.PPM}",
            "12.5 ${S.kOhms} ${S.PM}5%\n15 ${S.PPM}",
            "12.5 ${S.kOhms} ${S.PM}5%\n25 ${S.PPM}",
            "12.5 ${S.kOhms} ${S.PM}5%\n20 ${S.PPM}",
            "12.5 ${S.kOhms} ${S.PM}5%\n10 ${S.PPM}",
            "12.5 ${S.kOhms} ${S.PM}5%\n5 ${S.PPM}",
            "12.5 ${S.kOhms} ${S.PM}5%\n1 ${S.PPM}",
        )

        val resistor = Resistor(C.BROWN, C.RED, C.GREEN, C.RED, C.GOLD, C.BLACK)
        resistor.setNumberOfBands(6)
        for (i in answers.indices) {
            resistor.ppmBand = ppmBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }
    }

    @Test
    fun `leading zeros for 4 bands`() {
        val multiplierBands = listOf(
            C.BLACK, C.BROWN, C.RED, C.ORANGE, C.YELLOW, C.GREEN, C.BLUE, C.VIOLET, C.GRAY, C.WHITE, C.GOLD, C.SILVER
        )

        val answers = listOf(
            "1 ${S.Ohms} ${S.PM}5%", "10 ${S.Ohms} ${S.PM}5%", "100 ${S.Ohms} ${S.PM}5%",
            "1 ${S.kOhms} ${S.PM}5%", "10 ${S.kOhms} ${S.PM}5%", "100 ${S.kOhms} ${S.PM}5%",
            "1 ${S.MOhms} ${S.PM}5%", "10 ${S.MOhms} ${S.PM}5%", "100 ${S.MOhms} ${S.PM}5%",
            "1 ${S.GOhms} ${S.PM}5%", "0.1 ${S.Ohms} ${S.PM}5%", "0.01 ${S.Ohms} ${S.PM}5%"
        )

        val resistor = Resistor(C.BLACK, C.BROWN, "", C.BLACK, C.GOLD)
        for (i in answers.indices) {
            resistor.multiplierBand = multiplierBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }
    }

    @Test
    fun `leading zeros for 5 bands`() {
        val multiplierBands = listOf(
            C.BLACK, C.BROWN, C.RED, C.ORANGE, C.YELLOW, C.GREEN, C.BLUE, C.VIOLET, C.GRAY, C.WHITE, C.GOLD, C.SILVER
        )

        var answers = listOf(
            "1 ${S.Ohms} ${S.PM}5%", "10 ${S.Ohms} ${S.PM}5%", "100 ${S.Ohms} ${S.PM}5%",
            "1 ${S.kOhms} ${S.PM}5%", "10 ${S.kOhms} ${S.PM}5%", "100 ${S.kOhms} ${S.PM}5%",
            "1 ${S.MOhms} ${S.PM}5%", "10 ${S.MOhms} ${S.PM}5%", "100 ${S.MOhms} ${S.PM}5%",
            "1 ${S.GOhms} ${S.PM}5%", "0.1 ${S.Ohms} ${S.PM}5%", "0.01 ${S.Ohms} ${S.PM}5%"
        )

        var resistor = Resistor(C.BLACK, C.BLACK, C.BROWN, C.BLACK, C.GOLD)
        resistor.setNumberOfBands(5)
        for (i in answers.indices) {
            resistor.multiplierBand = multiplierBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }

        answers = listOf(
            "11 ${S.Ohms} ${S.PM}5%", "110 ${S.Ohms} ${S.PM}5%",
            "1.1 ${S.kOhms} ${S.PM}5%", "11 ${S.kOhms} ${S.PM}5%", "110 ${S.kOhms} ${S.PM}5%",
            "1.1 ${S.MOhms} ${S.PM}5%", "11 ${S.MOhms} ${S.PM}5%", "110 ${S.MOhms} ${S.PM}5%",
            "1.1 ${S.GOhms} ${S.PM}5%", "11 ${S.GOhms} ${S.PM}5%",
            "1.1 ${S.Ohms} ${S.PM}5%", "0.11 ${S.Ohms} ${S.PM}5%"
        )

        resistor = Resistor(C.BLACK, C.BROWN, C.BROWN, C.BLACK, C.GOLD)
        resistor.setNumberOfBands(5)
        for (i in answers.indices) {
            resistor.multiplierBand = multiplierBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }
    }
}
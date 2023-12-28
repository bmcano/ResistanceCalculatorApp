package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.resistor.Resistor
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S
import org.junit.Assert.assertEquals
import org.junit.Test

class ResistanceFormatterTest {

    @Test
    fun `empty resistor`() {
        assertEquals("Select colors", ResistanceFormatter.calculate(Resistor()))
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
        )

        val answers = listOf(
            "68 ${S.Ohms} ${S.PM}5%", "470 ${S.Ohms} ${S.PM}5%", "330 ${S.Ohms} ${S.PM}5%",
            "100 ${S.Ohms} ${S.PM}5%", "1.2 ${S.kOhms} ${S.PM}5%", "1.0 ${S.kOhms} ${S.PM}5%",
            "9.0 ${S.kOhms} ${S.PM}5%",
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

        )

        val answers = listOf(
            "51.0 ${S.Ohms} ${S.PM}1%", "152 ${S.MOhms} ${S.PM}5%", "300 ${S.MOhms} ${S.PM}5%",
            "789 ${S.MOhms} ${S.PM}5%",
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
    fun `code coverage`() {
        val resistor = Resistor(C.GOLD, C.GOLD, "", C.RED, C.GOLD)
        val answer = "0 ${S.Ohms} ${S.PM}5%"
        assertEquals(answer, ResistanceFormatter.calculate(resistor))
    }
}
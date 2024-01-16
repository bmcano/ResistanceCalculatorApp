package com.brandoncano.resistancecalculator.util

import android.content.Context
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.resistor.ResistorCtV
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

class ResistanceFormatterTest {

    @Test
    fun `empty resistor`() {
        val context: Context = mockk<Context>()
        assertEquals("Select colors", ResistanceFormatter.calculate(ResistorCtV(context)))
    }

    @Test
    fun `four band resistors resistance text testing`() {
        val context: Context = mockk<Context>()
        val resistors = listOf(
            ResistorCtV(context),
            ResistorCtV(context),
            ResistorCtV(context),
            ResistorCtV(context),
            ResistorCtV(context),
            ResistorCtV(context),
            ResistorCtV(context),
        )

        val bands = listOf(
            listOf(C.BLUE, C.GRAY, C.BLACK, C.GOLD),
            listOf(C.YELLOW, C.VIOLET, C.BROWN, C.GOLD),
            listOf(C.ORANGE, C.ORANGE, C.BROWN, C.GOLD),
            listOf(C.BROWN, C.BLACK, C.BROWN, C.GOLD),
            listOf(C.BROWN, C.RED, C.RED, C.GOLD),
            listOf(C.BROWN, C.BLACK, C.RED, C.GOLD),
            listOf(C.WHITE, C.BLACK, C.RED, C.GOLD),
        )

        resistors.forEachIndexed { index, resistorCtV ->
            resistorCtV.sigFigBandOne = bands[index][0]
            resistorCtV.sigFigBandTwo = bands[index][1]
            resistorCtV.multiplierBand = bands[index][2]
            resistorCtV.toleranceBand = bands[index][3]
        }

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
        val context: Context = mockk<Context>()
        val resistors = listOf(
            // general
            ResistorCtV(context),
            ResistorCtV(context),
            ResistorCtV(context),
            ResistorCtV(context),
        )

        val bands = listOf(
            listOf(C.GREEN, C.BROWN, C.BLACK, C.GOLD, C.BROWN),
            listOf(C.BROWN, C.GREEN, C.RED, C.BLUE, C.GOLD),
            listOf(C.ORANGE, C.BLACK, C.BLACK, C.BLUE, C.GOLD),
            listOf(C.VIOLET, C.GRAY, C.WHITE, C.BLUE, C.GOLD),
        )

        resistors.forEachIndexed { index, resistorCtV ->
            resistorCtV.sigFigBandOne = bands[index][0]
            resistorCtV.sigFigBandTwo = bands[index][1]
            resistorCtV.sigFigBandThree = bands[index][2]
            resistorCtV.multiplierBand = bands[index][3]
            resistorCtV.toleranceBand = bands[index][4]
        }

        val answers = listOf(
            "51.0 ${S.Ohms} ${S.PM}1%", "152 ${S.MOhms} ${S.PM}5%", "300 ${S.MOhms} ${S.PM}5%",
            "789 ${S.MOhms} ${S.PM}5%",
        )

        every { StateData.BUTTON_SELECTION_CTV.saveData(context, "5") } answers { }

        for (i in answers.indices) {
            resistors[i].saveNumberOfBands(5)
            assertEquals(answers[i], ResistanceFormatter.calculate(resistors[i]))
        }
    }

    @Test
    fun `six band resistors ppm bands`() {
        val context: Context = mockk<Context>()
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

        every { StateData.BUTTON_SELECTION_CTV.saveData(context, "6") } answers { }

        val resistor = ResistorCtV(context)
        resistor.sigFigBandOne = C.BROWN
        resistor.sigFigBandTwo = C.RED
        resistor.sigFigBandThree = C.GREEN
        resistor.multiplierBand = C.RED
        resistor.toleranceBand = C.GOLD
        resistor.ppmBand = C.BLACK

        resistor.saveNumberOfBands(6)
        for (i in answers.indices) {
            resistor.ppmBand = ppmBands[i]
            assertEquals(answers[i], ResistanceFormatter.calculate(resistor))
        }
    }

    @Test
    fun `code coverage`() {
        val context: Context = mockk<Context>()
        val resistor = ResistorCtV(context)
        resistor.sigFigBandOne = C.GOLD
        resistor.sigFigBandTwo = C.GOLD
        resistor.multiplierBand = C.RED
        resistor.toleranceBand = C.GOLD
        val answer = "0 ${S.Ohms} ${S.PM}5%"
        assertEquals(answer, ResistanceFormatter.calculate(resistor))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
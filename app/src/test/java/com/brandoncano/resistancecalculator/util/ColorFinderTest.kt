package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.ui.theme.black
import com.brandoncano.resistancecalculator.ui.theme.blue
import com.brandoncano.resistancecalculator.ui.theme.brown
import com.brandoncano.resistancecalculator.ui.theme.gold
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.resistancecalculator.ui.theme.green
import com.brandoncano.resistancecalculator.ui.theme.orange
import com.brandoncano.resistancecalculator.ui.theme.red
import com.brandoncano.resistancecalculator.ui.theme.resistor_blank
import com.brandoncano.resistancecalculator.ui.theme.silver
import com.brandoncano.resistancecalculator.ui.theme.violet
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.ui.theme.yellow
import org.junit.Assert.assertEquals
import org.junit.Test
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

class ColorFinderTest {

    @Test
    fun textToColorTest() {
        assertEquals(black, ColorFinder.textToColor(C.BLACK))
        assertEquals(black, ColorFinder.textToColor("250 ${S.PPM}"))

        assertEquals(brown, ColorFinder.textToColor(C.BROWN))
        assertEquals(brown, ColorFinder.textToColor("${S.PM}1%"))
        assertEquals(brown, ColorFinder.textToColor("100 ${S.PPM}"))

        assertEquals(red, ColorFinder.textToColor(C.RED))
        assertEquals(red, ColorFinder.textToColor("${S.PM}2%"))
        assertEquals(red, ColorFinder.textToColor("50 ${S.PPM}"))

        assertEquals(orange, ColorFinder.textToColor(C.ORANGE))
        assertEquals(orange, ColorFinder.textToColor("15 ${S.PPM}"))

        assertEquals(yellow, ColorFinder.textToColor(C.YELLOW))
        assertEquals(yellow, ColorFinder.textToColor("25 ${S.PPM}"))

        assertEquals(green, ColorFinder.textToColor(C.GREEN))
        assertEquals(green, ColorFinder.textToColor("${S.PM}0.5%"))
        assertEquals(green, ColorFinder.textToColor("20 ${S.PPM}"))

        assertEquals(blue, ColorFinder.textToColor(C.BLUE))
        assertEquals(blue, ColorFinder.textToColor("${S.PM}0.25%"))
        assertEquals(blue, ColorFinder.textToColor("10 ${S.PPM}"))

        assertEquals(violet, ColorFinder.textToColor(C.VIOLET))
        assertEquals(violet, ColorFinder.textToColor("${S.PM}0.1%"))
        assertEquals(violet, ColorFinder.textToColor("5 ${S.PPM}"))

        assertEquals(gray, ColorFinder.textToColor(C.GRAY))
        assertEquals(gray, ColorFinder.textToColor("${S.PM}0.05%"))
        assertEquals(gray, ColorFinder.textToColor("1 ${S.PPM}"))

        assertEquals(white, ColorFinder.textToColor(C.WHITE))

        assertEquals(gold, ColorFinder.textToColor(C.GOLD))
        assertEquals(gold, ColorFinder.textToColor("${S.PM}5%"))

        assertEquals(silver, ColorFinder.textToColor(C.SILVER))
        assertEquals(silver, ColorFinder.textToColor("${S.PM}10%"))

        assertEquals(resistor_blank, ColorFinder.textToColor("Some String"))
        assertEquals(resistor_blank, ColorFinder.textToColor("Not a color Red"))
    }

    @Test
    fun numberToColorTest() {
        assertEquals(C.BLACK, ColorFinder.numberToText(0))
        assertEquals(C.BROWN, ColorFinder.numberToText(1))
        assertEquals(C.RED, ColorFinder.numberToText(2))
        assertEquals(C.ORANGE, ColorFinder.numberToText(3))
        assertEquals(C.YELLOW, ColorFinder.numberToText(4))
        assertEquals(C.GREEN, ColorFinder.numberToText(5))
        assertEquals(C.BLUE, ColorFinder.numberToText(6))
        assertEquals(C.VIOLET, ColorFinder.numberToText(7))
        assertEquals(C.GRAY, ColorFinder.numberToText(8))
        assertEquals(C.WHITE, ColorFinder.numberToText(9))

        assertEquals(C.BLANK, ColorFinder.numberToText(-1))
        assertEquals(C.BLANK, ColorFinder.numberToText())
    }
}

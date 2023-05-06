package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ColorFinderTest {

    @Test
    fun textToColorDrawableTest() {
        assertEquals(R.drawable.square_black, ColorFinder.textToColoredDrawable(C.BLACK))
        assertEquals(R.drawable.square_black, ColorFinder.textToColoredDrawable("250 ${S.PPM}"))

        assertEquals(R.drawable.square_brown, ColorFinder.textToColoredDrawable(C.BROWN))
        assertEquals(R.drawable.square_brown, ColorFinder.textToColoredDrawable("${S.PM}1%"))
        assertEquals(R.drawable.square_brown, ColorFinder.textToColoredDrawable("100 ${S.PPM}"))

        assertEquals(R.drawable.square_red, ColorFinder.textToColoredDrawable(C.RED))
        assertEquals(R.drawable.square_red, ColorFinder.textToColoredDrawable("${S.PM}2%"))
        assertEquals(R.drawable.square_red, ColorFinder.textToColoredDrawable("50 ${S.PPM}"))

        assertEquals(R.drawable.square_orange, ColorFinder.textToColoredDrawable(C.ORANGE))
        assertEquals(R.drawable.square_orange, ColorFinder.textToColoredDrawable("15 ${S.PPM}"))

        assertEquals(R.drawable.square_yellow, ColorFinder.textToColoredDrawable(C.YELLOW))
        assertEquals(R.drawable.square_yellow, ColorFinder.textToColoredDrawable("25 ${S.PPM}"))

        assertEquals(R.drawable.square_green, ColorFinder.textToColoredDrawable(C.GREEN))
        assertEquals(R.drawable.square_green, ColorFinder.textToColoredDrawable("${S.PM}0.5%"))
        assertEquals(R.drawable.square_green, ColorFinder.textToColoredDrawable("20 ${S.PPM}"))

        assertEquals(R.drawable.square_blue, ColorFinder.textToColoredDrawable(C.BLUE))
        assertEquals(R.drawable.square_blue, ColorFinder.textToColoredDrawable("${S.PM}0.25%"))
        assertEquals(R.drawable.square_blue, ColorFinder.textToColoredDrawable("10 ${S.PPM}"))

        assertEquals(R.drawable.square_violet, ColorFinder.textToColoredDrawable(C.VIOLET))
        assertEquals(R.drawable.square_violet, ColorFinder.textToColoredDrawable("${S.PM}0.1%"))
        assertEquals(R.drawable.square_violet, ColorFinder.textToColoredDrawable("5 ${S.PPM}"))

        assertEquals(R.drawable.square_gray, ColorFinder.textToColoredDrawable(C.GRAY))
        assertEquals(R.drawable.square_gray, ColorFinder.textToColoredDrawable("${S.PM}0.05%"))
        assertEquals(R.drawable.square_gray, ColorFinder.textToColoredDrawable("1 ${S.PPM}"))

        assertEquals(R.drawable.square_white, ColorFinder.textToColoredDrawable(C.WHITE))

        assertEquals(R.drawable.square_gold, ColorFinder.textToColoredDrawable(C.GOLD))
        assertEquals(R.drawable.square_gold, ColorFinder.textToColoredDrawable("${S.PM}5%"))

        assertEquals(R.drawable.square_silver, ColorFinder.textToColoredDrawable(C.SILVER))
        assertEquals(R.drawable.square_silver, ColorFinder.textToColoredDrawable("${S.PM}10%"))

        assertEquals(R.drawable.square_blank, ColorFinder.textToColoredDrawable())
        assertEquals(R.drawable.square_blank, ColorFinder.textToColoredDrawable("Some String"))
        assertEquals(R.drawable.square_blank, ColorFinder.textToColoredDrawable("Not a color Red"))
    }

    @Test
    fun textToColorTest() {
        assertEquals(R.color.black, ColorFinder.textToColor(C.BLACK))
        assertEquals(R.color.black, ColorFinder.textToColor("250 ${S.PPM}"))

        assertEquals(R.color.brown, ColorFinder.textToColor(C.BROWN))
        assertEquals(R.color.brown, ColorFinder.textToColor("${S.PM}1%"))
        assertEquals(R.color.brown, ColorFinder.textToColor("100 ${S.PPM}"))

        assertEquals(R.color.red, ColorFinder.textToColor(C.RED))
        assertEquals(R.color.red, ColorFinder.textToColor("${S.PM}2%"))
        assertEquals(R.color.red, ColorFinder.textToColor("50 ${S.PPM}"))

        assertEquals(R.color.orange, ColorFinder.textToColor(C.ORANGE))
        assertEquals(R.color.orange, ColorFinder.textToColor("15 ${S.PPM}"))

        assertEquals(R.color.yellow, ColorFinder.textToColor(C.YELLOW))
        assertEquals(R.color.yellow, ColorFinder.textToColor("25 ${S.PPM}"))

        assertEquals(R.color.green, ColorFinder.textToColor(C.GREEN))
        assertEquals(R.color.green, ColorFinder.textToColor("${S.PM}0.5%"))
        assertEquals(R.color.green, ColorFinder.textToColor("20 ${S.PPM}"))

        assertEquals(R.color.blue, ColorFinder.textToColor(C.BLUE))
        assertEquals(R.color.blue, ColorFinder.textToColor("${S.PM}0.25%"))
        assertEquals(R.color.blue, ColorFinder.textToColor("10 ${S.PPM}"))

        assertEquals(R.color.violet, ColorFinder.textToColor(C.VIOLET))
        assertEquals(R.color.violet, ColorFinder.textToColor("${S.PM}0.1%"))
        assertEquals(R.color.violet, ColorFinder.textToColor("5 ${S.PPM}"))

        assertEquals(R.color.gray, ColorFinder.textToColor(C.GRAY))
        assertEquals(R.color.gray, ColorFinder.textToColor("${S.PM}0.05%"))
        assertEquals(R.color.gray, ColorFinder.textToColor("1 ${S.PPM}"))

        assertEquals(R.color.white, ColorFinder.textToColor(C.WHITE))

        assertEquals(R.color.gold, ColorFinder.textToColor(C.GOLD))
        assertEquals(R.color.gold, ColorFinder.textToColor("${S.PM}5%"))

        assertEquals(R.color.silver, ColorFinder.textToColor(C.SILVER))
        assertEquals(R.color.silver, ColorFinder.textToColor("${S.PM}10%"))

        assertEquals(R.color.resistor_blank, ColorFinder.textToColor())
        assertEquals(R.color.resistor_blank, ColorFinder.textToColor("Some String"))
        assertEquals(R.color.resistor_blank, ColorFinder.textToColor("Not a color Red"))
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

    @Test
    fun idToColorTextTest() {
        assertEquals(C.BLACK, ColorFinder.idToColorText(R.color.black))
        assertEquals(C.BLACK, ColorFinder.idToColorText(R.drawable.square_black))

        assertEquals(C.BROWN, ColorFinder.idToColorText(R.color.brown))
        assertEquals(C.BROWN, ColorFinder.idToColorText(R.drawable.square_brown))

        assertEquals(C.RED, ColorFinder.idToColorText(R.color.red))
        assertEquals(C.RED, ColorFinder.idToColorText(R.drawable.square_red))

        assertEquals(C.ORANGE, ColorFinder.idToColorText(R.color.orange))
        assertEquals(C.ORANGE, ColorFinder.idToColorText(R.drawable.square_orange))

        assertEquals(C.YELLOW, ColorFinder.idToColorText(R.color.yellow))
        assertEquals(C.YELLOW, ColorFinder.idToColorText(R.drawable.square_yellow))

        assertEquals(C.GREEN, ColorFinder.idToColorText(R.color.green))
        assertEquals(C.GREEN, ColorFinder.idToColorText(R.drawable.square_green))

        assertEquals(C.BLUE, ColorFinder.idToColorText(R.color.blue))
        assertEquals(C.BLUE, ColorFinder.idToColorText(R.drawable.square_blue))

        assertEquals(C.VIOLET, ColorFinder.idToColorText(R.color.violet))
        assertEquals(C.VIOLET, ColorFinder.idToColorText(R.drawable.square_violet))

        assertEquals(C.GRAY, ColorFinder.idToColorText(R.color.gray))
        assertEquals(C.GRAY, ColorFinder.idToColorText(R.drawable.square_gray))

        assertEquals(C.WHITE, ColorFinder.idToColorText(R.color.white))
        assertEquals(C.WHITE, ColorFinder.idToColorText(R.drawable.square_white))

        assertEquals(C.SILVER, ColorFinder.idToColorText(R.color.silver))
        assertEquals(C.SILVER, ColorFinder.idToColorText(R.drawable.square_silver))

        assertEquals(C.GOLD, ColorFinder.idToColorText(R.color.gold))
        assertEquals(C.GOLD, ColorFinder.idToColorText(R.drawable.square_gold))

        assertEquals("", ColorFinder.idToColorText(R.color.resistor_blank))
        assertEquals("", ColorFinder.idToColorText(R.drawable.square_blank))
    }

    @Test
    fun randomColorTest() {
        val colors = arrayOf(
            R.color.red, R.color.orange, R.color.yellow, R.color.gold, R.color.green,
            R.color.blue, R.color.violet, R.color.white, R.color.silver, R.color.gray,
            R.color.black, R.color.brown, R.color.resistor_blank
        )
        assertTrue(ColorFinder.randomColor() in colors)
    }
}

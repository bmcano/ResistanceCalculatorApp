package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ColorFinderTest {

    @Test
    fun textToColorDrawableTest() {
        assertEquals(R.drawable.black_square, ColorFinder.textToColoredDrawable(BLACK))
        assertEquals(R.drawable.black_square, ColorFinder.textToColoredDrawable("250 $PPM_UNIT"))

        assertEquals(R.drawable.brown_square, ColorFinder.textToColoredDrawable(BROWN))
        assertEquals(R.drawable.brown_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}1%"))
        assertEquals(R.drawable.brown_square, ColorFinder.textToColoredDrawable("100 $PPM_UNIT"))

        assertEquals(R.drawable.red_square, ColorFinder.textToColoredDrawable(RED))
        assertEquals(R.drawable.red_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}2%"))
        assertEquals(R.drawable.red_square, ColorFinder.textToColoredDrawable("50 $PPM_UNIT"))

        assertEquals(R.drawable.orange_square, ColorFinder.textToColoredDrawable(ORANGE))
        assertEquals(R.drawable.orange_square, ColorFinder.textToColoredDrawable("15 $PPM_UNIT"))

        assertEquals(R.drawable.yellow_square, ColorFinder.textToColoredDrawable(YELLOW))
        assertEquals(R.drawable.yellow_square, ColorFinder.textToColoredDrawable("25 $PPM_UNIT"))

        assertEquals(R.drawable.green_square, ColorFinder.textToColoredDrawable(GREEN))
        assertEquals(R.drawable.green_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}0.5%"))
        assertEquals(R.drawable.green_square, ColorFinder.textToColoredDrawable("20 $PPM_UNIT"))

        assertEquals(R.drawable.blue_square, ColorFinder.textToColoredDrawable(BLUE))
        assertEquals(R.drawable.blue_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}0.25%"))
        assertEquals(R.drawable.blue_square, ColorFinder.textToColoredDrawable("10 $PPM_UNIT"))

        assertEquals(R.drawable.violet_square, ColorFinder.textToColoredDrawable(VIOLET))
        assertEquals(R.drawable.violet_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}0.1%"))
        assertEquals(R.drawable.violet_square, ColorFinder.textToColoredDrawable("5 $PPM_UNIT"))

        assertEquals(R.drawable.gray_square, ColorFinder.textToColoredDrawable(GRAY))
        assertEquals(R.drawable.gray_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}0.05%"))
        assertEquals(R.drawable.gray_square, ColorFinder.textToColoredDrawable("1 $PPM_UNIT"))

        assertEquals(R.drawable.white_square, ColorFinder.textToColoredDrawable(WHITE))

        assertEquals(R.drawable.gold_square, ColorFinder.textToColoredDrawable(GOLD))
        assertEquals(R.drawable.gold_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}5%"))

        assertEquals(R.drawable.silver_square, ColorFinder.textToColoredDrawable(SILVER))
        assertEquals(R.drawable.silver_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}10%"))

        assertEquals(R.drawable.blank32, ColorFinder.textToColoredDrawable())
        assertEquals(R.drawable.blank32, ColorFinder.textToColoredDrawable("Some String"))
        assertEquals(R.drawable.blank32, ColorFinder.textToColoredDrawable("Not a color Red"))
    }

    @Test
    fun textToColorTest() {
        assertEquals(R.color.black32, ColorFinder.textToColor(BLACK))
        assertEquals(R.color.black32, ColorFinder.textToColor("250 $PPM_UNIT"))

        assertEquals(R.color.brown32, ColorFinder.textToColor(BROWN))
        assertEquals(R.color.brown32, ColorFinder.textToColor("${PLUS_MINUS}1%"))
        assertEquals(R.color.brown32, ColorFinder.textToColor("100 $PPM_UNIT"))

        assertEquals(R.color.red32, ColorFinder.textToColor(RED))
        assertEquals(R.color.red32, ColorFinder.textToColor("${PLUS_MINUS}2%"))
        assertEquals(R.color.red32, ColorFinder.textToColor("50 $PPM_UNIT"))

        assertEquals(R.color.orange32, ColorFinder.textToColor(ORANGE))
        assertEquals(R.color.orange32, ColorFinder.textToColor("15 $PPM_UNIT"))

        assertEquals(R.color.yellow32, ColorFinder.textToColor(YELLOW))
        assertEquals(R.color.yellow32, ColorFinder.textToColor("25 $PPM_UNIT"))

        assertEquals(R.color.green32, ColorFinder.textToColor(GREEN))
        assertEquals(R.color.green32, ColorFinder.textToColor("${PLUS_MINUS}0.5%"))
        assertEquals(R.color.green32, ColorFinder.textToColor("20 $PPM_UNIT"))

        assertEquals(R.color.blue32, ColorFinder.textToColor(BLUE))
        assertEquals(R.color.blue32, ColorFinder.textToColor("${PLUS_MINUS}0.25%"))
        assertEquals(R.color.blue32, ColorFinder.textToColor("10 $PPM_UNIT"))

        assertEquals(R.color.violet32, ColorFinder.textToColor(VIOLET))
        assertEquals(R.color.violet32, ColorFinder.textToColor("${PLUS_MINUS}0.1%"))
        assertEquals(R.color.violet32, ColorFinder.textToColor("5 $PPM_UNIT"))

        assertEquals(R.color.gray32, ColorFinder.textToColor(GRAY))
        assertEquals(R.color.gray32, ColorFinder.textToColor("${PLUS_MINUS}0.05%"))
        assertEquals(R.color.gray32, ColorFinder.textToColor("1 $PPM_UNIT"))

        assertEquals(R.color.white32, ColorFinder.textToColor(WHITE))

        assertEquals(R.color.gold32, ColorFinder.textToColor(GOLD))
        assertEquals(R.color.gold32, ColorFinder.textToColor("${PLUS_MINUS}5%"))

        assertEquals(R.color.silver32, ColorFinder.textToColor(SILVER))
        assertEquals(R.color.silver32, ColorFinder.textToColor("${PLUS_MINUS}10%"))

        assertEquals(R.color.resistor_blank, ColorFinder.textToColor())
        assertEquals(R.color.resistor_blank, ColorFinder.textToColor("Some String"))
        assertEquals(R.color.resistor_blank, ColorFinder.textToColor("Not a color Red"))
    }

    @Test
    fun numberToColorTest() {
        assertEquals(BLACK, ColorFinder.numberToText(0))
        assertEquals(BROWN, ColorFinder.numberToText(1))
        assertEquals(RED, ColorFinder.numberToText(2))
        assertEquals(ORANGE, ColorFinder.numberToText(3))
        assertEquals(YELLOW, ColorFinder.numberToText(4))
        assertEquals(GREEN, ColorFinder.numberToText(5))
        assertEquals(BLUE, ColorFinder.numberToText(6))
        assertEquals(VIOLET, ColorFinder.numberToText(7))
        assertEquals(GRAY, ColorFinder.numberToText(8))
        assertEquals(WHITE, ColorFinder.numberToText(9))

        assertEquals(BLANK, ColorFinder.numberToText(-1))
        assertEquals(BLANK, ColorFinder.numberToText())
    }

    @Test
    fun idToColorTextTest() {
        assertEquals(BLACK, ColorFinder.idToColorText(R.color.black32))
        assertEquals(BLACK, ColorFinder.idToColorText(R.drawable.black_square))

        assertEquals(BROWN, ColorFinder.idToColorText(R.color.brown32))
        assertEquals(BROWN, ColorFinder.idToColorText(R.drawable.brown_square))

        assertEquals(RED, ColorFinder.idToColorText(R.color.red32))
        assertEquals(RED, ColorFinder.idToColorText(R.drawable.red_square))

        assertEquals(ORANGE, ColorFinder.idToColorText(R.color.orange32))
        assertEquals(ORANGE, ColorFinder.idToColorText(R.drawable.orange_square))

        assertEquals(YELLOW, ColorFinder.idToColorText(R.color.yellow32))
        assertEquals(YELLOW, ColorFinder.idToColorText(R.drawable.yellow_square))

        assertEquals(GREEN, ColorFinder.idToColorText(R.color.green32))
        assertEquals(GREEN, ColorFinder.idToColorText(R.drawable.green_square))

        assertEquals(BLUE, ColorFinder.idToColorText(R.color.blue32))
        assertEquals(BLUE, ColorFinder.idToColorText(R.drawable.blue_square))

        assertEquals(VIOLET, ColorFinder.idToColorText(R.color.violet32))
        assertEquals(VIOLET, ColorFinder.idToColorText(R.drawable.violet_square))

        assertEquals(GRAY, ColorFinder.idToColorText(R.color.gray32))
        assertEquals(GRAY, ColorFinder.idToColorText(R.drawable.gray_square))

        assertEquals(WHITE, ColorFinder.idToColorText(R.color.white32))
        assertEquals(WHITE, ColorFinder.idToColorText(R.drawable.white_square))

        assertEquals(SILVER, ColorFinder.idToColorText(R.color.silver32))
        assertEquals(SILVER, ColorFinder.idToColorText(R.drawable.silver_square))

        assertEquals(GOLD, ColorFinder.idToColorText(R.color.gold32))
        assertEquals(GOLD, ColorFinder.idToColorText(R.drawable.gold_square))

        assertEquals("", ColorFinder.idToColorText(R.color.resistor_blank))
        assertEquals("", ColorFinder.idToColorText(R.drawable.blank32))
    }

    @Test
    fun randomColorTest() {
        val colors = arrayOf(
            R.color.red32, R.color.orange32, R.color.yellow32, R.color.gold32, R.color.green32,
            R.color.blue32, R.color.violet32, R.color.white32, R.color.silver32, R.color.gray32,
            R.color.black32, R.color.brown32, R.color.resistor_blank
        )
        assertTrue(ColorFinder.randomColor() in colors)
    }
}

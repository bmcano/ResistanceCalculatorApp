package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.R
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Brandon
 */
class ColorFinderTest {

    private companion object {
        const val PLUS_MINUS: String = "±"
        const val DEGREE: String = "°"
    }

    @Test
    fun textToColorDrawableTest() {
        assertEquals(R.drawable.black_square, ColorFinder.textToColoredDrawable("Black"))
        assertEquals(R.drawable.black_square, ColorFinder.textToColoredDrawable("250 ppm/${DEGREE}C"))

        assertEquals(R.drawable.brown_square, ColorFinder.textToColoredDrawable("Brown"))
        assertEquals(R.drawable.brown_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}1%"))
        assertEquals(R.drawable.brown_square, ColorFinder.textToColoredDrawable("100 ppm/${DEGREE}C"))

        assertEquals(R.drawable.red_square, ColorFinder.textToColoredDrawable("Red"))
        assertEquals(R.drawable.red_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}2%"))
        assertEquals(R.drawable.red_square, ColorFinder.textToColoredDrawable("50 ppm/${DEGREE}C"))

        assertEquals(R.drawable.orange_square, ColorFinder.textToColoredDrawable("Orange"))
        assertEquals(R.drawable.orange_square, ColorFinder.textToColoredDrawable("15 ppm/${DEGREE}C"))

        assertEquals(R.drawable.yellow_square, ColorFinder.textToColoredDrawable("Yellow"))
        assertEquals(R.drawable.yellow_square, ColorFinder.textToColoredDrawable("25 ppm/${DEGREE}C"))

        assertEquals(R.drawable.green_square, ColorFinder.textToColoredDrawable("Green"))
        assertEquals(R.drawable.green_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}0.5%"))
        assertEquals(R.drawable.green_square, ColorFinder.textToColoredDrawable("20 ppm/${DEGREE}C"))

        assertEquals(R.drawable.blue_square, ColorFinder.textToColoredDrawable("Blue"))
        assertEquals(R.drawable.blue_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}0.25%"))
        assertEquals(R.drawable.blue_square, ColorFinder.textToColoredDrawable("10 ppm/${DEGREE}C"))

        assertEquals(R.drawable.violet_square, ColorFinder.textToColoredDrawable("Violet"))
        assertEquals(R.drawable.violet_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}0.1%"))
        assertEquals(R.drawable.violet_square, ColorFinder.textToColoredDrawable("5 ppm/${DEGREE}C"))

        assertEquals(R.drawable.gray_square, ColorFinder.textToColoredDrawable("Gray"))
        assertEquals(R.drawable.gray_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}0.05%"))
        assertEquals(R.drawable.gray_square, ColorFinder.textToColoredDrawable("1 ppm/${DEGREE}C"))

        assertEquals(R.drawable.white_square, ColorFinder.textToColoredDrawable("White"))

        assertEquals(R.drawable.gold_square, ColorFinder.textToColoredDrawable("Gold"))
        assertEquals(R.drawable.gold_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}5%"))

        assertEquals(R.drawable.silver_square, ColorFinder.textToColoredDrawable("Silver"))
        assertEquals(R.drawable.silver_square, ColorFinder.textToColoredDrawable("${PLUS_MINUS}10%"))

        assertEquals(R.drawable.blank32, ColorFinder.textToColoredDrawable())
        assertEquals(R.drawable.blank32, ColorFinder.textToColoredDrawable("Some String"))
        assertEquals(R.drawable.blank32, ColorFinder.textToColoredDrawable("Not a color Red"))
    }

    @Test
    fun textToColorTest() {
        assertEquals(R.color.black32, ColorFinder.textToColor("Black"))
        assertEquals(R.color.black32, ColorFinder.textToColor("250 ppm/${DEGREE}C"))

        assertEquals(R.color.brown32, ColorFinder.textToColor("Brown"))
        assertEquals(R.color.brown32, ColorFinder.textToColor("${PLUS_MINUS}1%"))
        assertEquals(R.color.brown32, ColorFinder.textToColor("100 ppm/${DEGREE}C"))

        assertEquals(R.color.red32, ColorFinder.textToColor("Red"))
        assertEquals(R.color.red32, ColorFinder.textToColor("${PLUS_MINUS}2%"))
        assertEquals(R.color.red32, ColorFinder.textToColor("50 ppm/${DEGREE}C"))

        assertEquals(R.color.orange32, ColorFinder.textToColor("Orange"))
        assertEquals(R.color.orange32, ColorFinder.textToColor("15 ppm/${DEGREE}C"))

        assertEquals(R.color.yellow32, ColorFinder.textToColor("Yellow"))
        assertEquals(R.color.yellow32, ColorFinder.textToColor("25 ppm/${DEGREE}C"))

        assertEquals(R.color.green32, ColorFinder.textToColor("Green"))
        assertEquals(R.color.green32, ColorFinder.textToColor("${PLUS_MINUS}0.5%"))
        assertEquals(R.color.green32, ColorFinder.textToColor("20 ppm/${DEGREE}C"))

        assertEquals(R.color.blue32, ColorFinder.textToColor("Blue"))
        assertEquals(R.color.blue32, ColorFinder.textToColor("${PLUS_MINUS}0.25%"))
        assertEquals(R.color.blue32, ColorFinder.textToColor("10 ppm/${DEGREE}C"))

        assertEquals(R.color.violet32, ColorFinder.textToColor("Violet"))
        assertEquals(R.color.violet32, ColorFinder.textToColor("${PLUS_MINUS}0.1%"))
        assertEquals(R.color.violet32, ColorFinder.textToColor("5 ppm/${DEGREE}C"))

        assertEquals(R.color.gray32, ColorFinder.textToColor("Gray"))
        assertEquals(R.color.gray32, ColorFinder.textToColor("${PLUS_MINUS}0.05%"))
        assertEquals(R.color.gray32, ColorFinder.textToColor("1 ppm/${DEGREE}C"))

        assertEquals(R.color.white32, ColorFinder.textToColor("White"))

        assertEquals(R.color.gold32, ColorFinder.textToColor("Gold"))
        assertEquals(R.color.gold32, ColorFinder.textToColor("${PLUS_MINUS}5%"))

        assertEquals(R.color.silver32, ColorFinder.textToColor("Silver"))
        assertEquals(R.color.silver32, ColorFinder.textToColor("${PLUS_MINUS}10%"))

        assertEquals(R.color.resistor_blank, ColorFinder.textToColor())
        assertEquals(R.color.resistor_blank, ColorFinder.textToColor("Some String"))
        assertEquals(R.color.resistor_blank, ColorFinder.textToColor("Not a color Red"))
    }

    @Test
    fun numberToColorTest() {
        assertEquals("Black", ColorFinder.numberToText(0))
        assertEquals("Brown", ColorFinder.numberToText(1))
        assertEquals("Red", ColorFinder.numberToText(2))
        assertEquals("Orange", ColorFinder.numberToText(3))
        assertEquals("Yellow", ColorFinder.numberToText(4))
        assertEquals("Green", ColorFinder.numberToText(5))
        assertEquals("Blue", ColorFinder.numberToText(6))
        assertEquals("Violet", ColorFinder.numberToText(7))
        assertEquals("Gray", ColorFinder.numberToText(8))
        assertEquals("White", ColorFinder.numberToText(9))

        assertEquals("", ColorFinder.numberToText(-1))
        assertEquals("", ColorFinder.numberToText())
    }

    @Test
    fun idToColorTextTest() {
        assertEquals("Black", ColorFinder.idToColorText(R.color.black32))
        assertEquals("Black", ColorFinder.idToColorText(R.drawable.black_square))

        assertEquals("Brown", ColorFinder.idToColorText(R.color.brown32))
        assertEquals("Brown", ColorFinder.idToColorText(R.drawable.brown_square))

        assertEquals("Red", ColorFinder.idToColorText(R.color.red32))
        assertEquals("Red", ColorFinder.idToColorText(R.drawable.red_square))

        assertEquals("Orange", ColorFinder.idToColorText(R.color.orange32))
        assertEquals("Orange", ColorFinder.idToColorText(R.drawable.orange_square))

        assertEquals("Yellow", ColorFinder.idToColorText(R.color.yellow32))
        assertEquals("Yellow", ColorFinder.idToColorText(R.drawable.yellow_square))

        assertEquals("Green", ColorFinder.idToColorText(R.color.green32))
        assertEquals("Green", ColorFinder.idToColorText(R.drawable.green_square))

        assertEquals("Blue", ColorFinder.idToColorText(R.color.blue32))
        assertEquals("Blue", ColorFinder.idToColorText(R.drawable.blue_square))

        assertEquals("Violet", ColorFinder.idToColorText(R.color.violet32))
        assertEquals("Violet", ColorFinder.idToColorText(R.drawable.violet_square))

        assertEquals("Gray", ColorFinder.idToColorText(R.color.gray32))
        assertEquals("Gray", ColorFinder.idToColorText(R.drawable.gray_square))

        assertEquals("White", ColorFinder.idToColorText(R.color.white32))
        assertEquals("White", ColorFinder.idToColorText(R.drawable.white_square))

        assertEquals("Silver", ColorFinder.idToColorText(R.color.silver32))
        assertEquals("Silver", ColorFinder.idToColorText(R.drawable.silver_square))

        assertEquals("Gold", ColorFinder.idToColorText(R.color.gold32))
        assertEquals("Gold", ColorFinder.idToColorText(R.drawable.gold_square))

        assertEquals("", ColorFinder.idToColorText(R.color.resistor_blank))
        assertEquals("", ColorFinder.idToColorText(R.drawable.blank32))
    }
}

package com.brandoncano.resistancecalculator

import com.brandoncano.resistancecalculator.util.ColorFinder
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class ColorFinderTest {
    companion object {
        private const val PLUS_MINUS: String = "±"
        private const val DEGREE: String = "°"
    }

    @Test
    fun imageColorTest() {
        val colors = arrayOf(
            "Black", "Blue", "Brown", "Gold", "Gray", "Green",
            "Orange", "Red", "Silver", "Violet", "White", "Yellow",
            "Blank"
        )

        val colorsInts = arrayOf(
            R.drawable.black32, R.drawable.blue32, R.drawable.brown32, R.drawable.gold32,
            R.drawable.gray32, R.drawable.green32, R.drawable.orange32, R.drawable.red32,
            R.drawable.silver32, R.drawable.violet32, R.drawable.white32, R.drawable.yellow32,
            R.drawable.blank32
        )

        for (i in 0..12) {
            val expectedResult = colorsInts[i]
            val actualResult = ColorFinder.imageColor(colors[i])
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun numberColorTest() {
        val colors = arrayOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1
        )

        val colorsInts = arrayOf(
            R.color.black32, R.color.brown32, R.color.red32, R.color.orange32,
            R.color.yellow32, R.color.green32, R.color.blue32, R.color.violet32,
            R.color.gray32,  R.color.white32, R.color.black32
        )

        for (i in 0..10) {
            val expectedResult = colorsInts[i]
            val actualResult = ColorFinder.numberColor(colors[i])
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun bandColorTest() {
        val colors = arrayOf(
            "Black", "Blue", "Brown", "Gold", "Gray", "Green",
            "Orange", "Red", "Silver", "Violet", "White", "Yellow",
            "Blank"
        )

        val colorsInts = arrayOf(
            R.color.black32, R.color.blue32, R.color.brown32, R.color.gold32,
            R.color.gray32, R.color.green32, R.color.orange32, R.color.red32,
            R.color.silver32, R.color.violet32, R.color.white32, R.color.yellow32,
            R.color.resistor_blank
        )

        for (i in 0..12) {
            val expectedResult = colorsInts[i]
            val actualResult = ColorFinder.bandColor(colors[i])
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun bandColorBlankTest() {
        val expectedResult = R.color.resistor_blank
        val actualResult = ColorFinder.bandColor()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun toleranceImageTest() {
        val colors = arrayOf(
            "${PLUS_MINUS}1%", "${PLUS_MINUS}2%", "${PLUS_MINUS}0.5%", "${PLUS_MINUS}0.25%",
            "${PLUS_MINUS}0.1%", "${PLUS_MINUS}0.05%", "${PLUS_MINUS}5%", "${PLUS_MINUS}10%",
            "${PLUS_MINUS}20%"
        )

        val colorsInts = arrayOf(
            R.drawable.brown32, R.drawable.red32, R.drawable.green32, R.drawable.blue32,
            R.drawable.violet32, R.drawable.gray32, R.drawable.gold32, R.drawable.silver32,
            R.drawable.blank32
        )

        for (i in 0..8) {
            val expectedResult = colorsInts[i]
            val actualResult = ColorFinder.toleranceImage(colors[i])
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun toleranceImageBlankTest() {
        val expectedResult = R.drawable.blank32
        val actualResult = ColorFinder.toleranceImage()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun toleranceColorTest() {
        val colors = arrayOf(
            "${PLUS_MINUS}1%", "${PLUS_MINUS}2%", "${PLUS_MINUS}0.5%", "${PLUS_MINUS}0.25%",
            "${PLUS_MINUS}0.1%", "${PLUS_MINUS}0.05%", "${PLUS_MINUS}5%", "${PLUS_MINUS}10%",
            "${PLUS_MINUS}20%"
        )

        val colorsInts = arrayOf(
            R.color.brown32, R.color.red32, R.color.green32, R.color.blue32,
            R.color.violet32, R.color.gray32, R.color.gold32, R.color.silver32,
            R.color.resistor_blank
        )

        for (i in 0..8) {
            val expectedResult = colorsInts[i]
            val actualResult = ColorFinder.toleranceColor(colors[i])
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun toleranceColorBlankTest() {
        val expectedResult = R.color.resistor_blank
        val actualResult = ColorFinder.toleranceColor()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun ppmImageTest() {
        val colors = arrayOf(
            "250 ppm/${DEGREE}C", "100 ppm/${DEGREE}C", "50 ppm/${DEGREE}C", "15 ppm/${DEGREE}C",
            "25 ppm/${DEGREE}C", "20 ppm/${DEGREE}C", "10 ppm/${DEGREE}C", "5 ppm/${DEGREE}C",
            "1 ppm/${DEGREE}C", "Blank"
        )

        val colorsInts = arrayOf(
            R.drawable.black32, R.drawable.brown32, R.drawable.red32, R.drawable.orange32,
            R.drawable.yellow32, R.drawable.green32, R.drawable.blue32, R.drawable.violet32,
            R.drawable.gray32, R.drawable.blank32
        )

        for (i in 0..9) {
            val expectedResult = colorsInts[i]
            val actualResult = ColorFinder.ppmImage(colors[i])
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun ppmImageBlankTest() {
        val expectedResult = R.drawable.blank32
        val actualResult = ColorFinder.ppmImage()
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun ppmColorTest() {
        val colors = arrayOf(
            "250 ppm/${DEGREE}C", "100 ppm/${DEGREE}C", "50 ppm/${DEGREE}C", "15 ppm/${DEGREE}C",
            "25 ppm/${DEGREE}C", "20 ppm/${DEGREE}C", "10 ppm/${DEGREE}C", "5 ppm/${DEGREE}C",
            "1 ppm/${DEGREE}C", "Blank"
        )

        val colorsInts = arrayOf(
            R.color.black32, R.color.brown32, R.color.red32, R.color.orange32,
            R.color.yellow32, R.color.green32, R.color.blue32, R.color.violet32,
            R.color.gray32, R.color.resistor_blank
        )

        for (i in 0..9) {
            val expectedResult = colorsInts[i]
            val actualResult = ColorFinder.ppmColor(colors[i])
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun ppmColorBlankTest() {
        val expectedResult = R.color.resistor_blank
        val actualResult = ColorFinder.ppmColor()
        assertEquals(expectedResult, actualResult)
    }

    @Ignore("Not possible to get full coverage on this test since its random.")
    @Test
    fun randomTest() {
        ColorFinder.randomColor()
    }
}
package com.brandoncano.resistancecalculator

import com.brandoncano.resistancecalculator.util.ColorFinder
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

/**
 * @author Brandon
 */
class ColorFinderTest {

    companion object {
        private const val PLUS_MINUS: String = "±"
        private const val DEGREE: String = "°"

        private val COLOR_DRAWABLES = arrayOf(
            R.drawable.black32, R.drawable.blue32, R.drawable.brown32, R.drawable.gold32,
            R.drawable.gray32, R.drawable.green32, R.drawable.orange32, R.drawable.red32,
            R.drawable.silver32, R.drawable.violet32, R.drawable.white32, R.drawable.yellow32,
            R.drawable.blank32
        )

        private val SPINNER_TEXT = arrayOf(
            "Black" ,                       "250 ppm/${DEGREE}C",
            "Brown" , "${PLUS_MINUS}1%"   , "100 ppm/${DEGREE}C",
            "Red"   , "${PLUS_MINUS}2%"   , "50 ppm/${DEGREE}C",
            "Orange",                       "15 ppm/${DEGREE}C",
            "Yellow",                       "25 ppm/${DEGREE}C",
            "Green" , "${PLUS_MINUS}0.5%" , "20 ppm/${DEGREE}C",
            "Blue"  , "${PLUS_MINUS}0.25%", "10 ppm/${DEGREE}C",
            "Violet", "${PLUS_MINUS}0.1%" , "5 ppm/${DEGREE}C",
            "Gray"  , "${PLUS_MINUS}0.05%", "1 ppm/${DEGREE}C",
            "White" ,
            "Gold"  , "${PLUS_MINUS}5%",
            "Silver", "${PLUS_MINUS}10%"
        )
    }
    
    // TODO - will add test later
}
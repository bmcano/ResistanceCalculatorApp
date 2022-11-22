package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.R

/**
 * @author: Brandon
 *
 * Job: find the correct color or drawable based on an int or string input
 */
object ColorFinder {

    private const val PLUS_MINUS: String = "±"
    private const val DEGREE: String = "°"
    private const val EMPTY_STRING = ""

    // note: "${PLUS_MINUS}20%" -> R.drawable.blank32
    fun textToColoredDrawable(color: String = ""): Int {
        return when (color) {
            "Black" ,                       "250 ppm/${DEGREE}C" -> R.drawable.black32
            "Brown" , "${PLUS_MINUS}1%"   , "100 ppm/${DEGREE}C" -> R.drawable.brown32
            "Red"   , "${PLUS_MINUS}2%"   , "50 ppm/${DEGREE}C"  -> R.drawable.red32
            "Orange",                       "15 ppm/${DEGREE}C"  -> R.drawable.orange32
            "Yellow",                       "25 ppm/${DEGREE}C"  -> R.drawable.yellow32
            "Green" , "${PLUS_MINUS}0.5%" , "20 ppm/${DEGREE}C"  -> R.drawable.green32
            "Blue"  , "${PLUS_MINUS}0.25%", "10 ppm/${DEGREE}C"  -> R.drawable.blue32
            "Violet", "${PLUS_MINUS}0.1%" , "5 ppm/${DEGREE}C"   -> R.drawable.violet32
            "Gray"  , "${PLUS_MINUS}0.05%", "1 ppm/${DEGREE}C"   -> R.drawable.gray32
            "White"                                              -> R.drawable.white32
            "Gold"  , "${PLUS_MINUS}5%"                          -> R.drawable.gold32
            "Silver", "${PLUS_MINUS}10%"                         -> R.drawable.silver32
            else                                                 -> R.drawable.blank32
        }
    }

    // note: "${PLUS_MINUS}20%" -> R.color.resistor_blank
    fun textToColor(color: String = ""): Int {
        return when (color) {
            "Red"   , "${PLUS_MINUS}2%"   , "50 ppm/${DEGREE}C"  -> R.color.red32
            "Orange",                       "15 ppm/${DEGREE}C"  -> R.color.orange32
            "Yellow"                                             -> R.color.yellow32
            "Gold"  , "${PLUS_MINUS}5%"   , "25 ppm/${DEGREE}C"  -> R.color.gold32
            "Green" , "${PLUS_MINUS}0.5%" , "20 ppm/${DEGREE}C"  -> R.color.green32
            "Blue"  , "${PLUS_MINUS}0.25%", "10 ppm/${DEGREE}C"  -> R.color.blue32
            "Violet", "${PLUS_MINUS}0.1%" , "5 ppm/${DEGREE}C"   -> R.color.violet32
            "White"                                              -> R.color.white32
            "Silver", "${PLUS_MINUS}10%"                         -> R.color.silver32
            "Gray"  , "${PLUS_MINUS}0.05%", "1 ppm/${DEGREE}C"   -> R.color.gray32
            "Black" ,                       "250 ppm/${DEGREE}C" -> R.color.black32
            "Brown" , "${PLUS_MINUS}1%"   , "100 ppm/${DEGREE}C" -> R.color.brown32
            else                                                 -> R.color.resistor_blank
        }
    }

    // finds the color depending on the position
    fun numberColor(color: Int): Int {
        return when (color) {
            0 -> R.color.black32
            1 -> R.color.brown32
            2 -> R.color.red32
            3 -> R.color.orange32
            4 -> R.color.yellow32
            5 -> R.color.green32
            6 -> R.color.blue32
            7 -> R.color.violet32
            8 -> R.color.gray32
            9 -> R.color.white32
            else -> R.color.black32
        }
    }

    // find text (color) from id
    fun idToColorText(colorId: Int): String {
        return when (colorId) {
            R.color.black32, R.drawable.black32 -> "Black"
            R.color.brown32, R.drawable.brown32 -> "Brown"
            R.color.red32, R.drawable.red32 -> "Red"
            R.color.orange32, R.drawable.orange32 -> "Orange"
            R.color.yellow32, R.drawable.yellow32 -> "Yellow"
            R.color.green32, R.drawable.green32 -> "Green"
            R.color.blue32, R.drawable.blue32 -> "Blue"
            R.color.violet32, R.drawable.violet32 -> "Violet"
            R.color.gray32, R.drawable.gray32 -> "Gray"
            R.color.gold32, R.drawable.gold32 -> "Gold"
            R.color.silver32, R.drawable.silver32 -> "Silver"
            else -> EMPTY_STRING
        }
    }

    // picks a random color
    fun randomColor(): Int {
        return when ((1..12).random()) {
            1 -> R.color.red32
            2 -> R.color.orange32
            3 -> R.color.yellow32
            4 -> R.color.gold32
            5 -> R.color.green32
            6 -> R.color.blue32
            7 -> R.color.violet32
            8 -> R.color.white32
            9 -> R.color.silver32
            10 -> R.color.gray32
            11 -> R.color.black32
            12 -> R.color.brown32
            else -> R.color.resistor_blank
        }
    }
}

package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.*

/**
 * Job: Find the correct color, string, or drawable based on the input.
 */
object ColorFinder {

    // note: "${PLUS_MINUS}20%" -> R.drawable.blank32
    fun textToColoredDrawable(color: String = ""): Int {
        return when (color) {
            BLACK ,                       "250 $PPM_UNIT" -> R.drawable.black_square
            BROWN , "${PLUS_MINUS}1%"   , "100 $PPM_UNIT" -> R.drawable.brown_square
            RED   , "${PLUS_MINUS}2%"   , "50 $PPM_UNIT"  -> R.drawable.red_square
            ORANGE,                       "15 $PPM_UNIT"  -> R.drawable.orange_square
            YELLOW,                       "25 $PPM_UNIT"  -> R.drawable.yellow_square
            GREEN , "${PLUS_MINUS}0.5%" , "20 $PPM_UNIT"  -> R.drawable.green_square
            BLUE  , "${PLUS_MINUS}0.25%", "10 $PPM_UNIT"  -> R.drawable.blue_square
            VIOLET, "${PLUS_MINUS}0.1%" , "5 $PPM_UNIT"   -> R.drawable.violet_square
            GRAY  , "${PLUS_MINUS}0.05%", "1 $PPM_UNIT"   -> R.drawable.gray_square
            WHITE                                         -> R.drawable.white_square
            GOLD  , "${PLUS_MINUS}5%"                     -> R.drawable.gold_square
            SILVER, "${PLUS_MINUS}10%"                    -> R.drawable.silver_square
            else                                          -> R.drawable.blank32
        }
    }

    // note: "${PLUS_MINUS}20%" -> R.color.resistor_blank
    fun textToColor(color: String = ""): Int {
        return when (color) {
            BLACK ,                       "250 $PPM_UNIT" -> R.color.black32
            BROWN , "${PLUS_MINUS}1%"   , "100 $PPM_UNIT" -> R.color.brown32
            RED   , "${PLUS_MINUS}2%"   , "50 $PPM_UNIT"  -> R.color.red32
            ORANGE,                       "15 $PPM_UNIT"  -> R.color.orange32
            YELLOW,                       "25 $PPM_UNIT"  -> R.color.yellow32
            GREEN , "${PLUS_MINUS}0.5%" , "20 $PPM_UNIT"  -> R.color.green32
            BLUE  , "${PLUS_MINUS}0.25%", "10 $PPM_UNIT"  -> R.color.blue32
            VIOLET, "${PLUS_MINUS}0.1%" , "5 $PPM_UNIT"   -> R.color.violet32
            GRAY  , "${PLUS_MINUS}0.05%", "1 $PPM_UNIT"   -> R.color.gray32
            WHITE                                         -> R.color.white32
            GOLD  , "${PLUS_MINUS}5%"                     -> R.color.gold32
            SILVER, "${PLUS_MINUS}10%"                    -> R.color.silver32
            else                                          -> R.color.resistor_blank
        }
    }

    // find text (color) from numeric value
    fun numberToText(color: Int = -1): String {
        return when (color) {
            0 -> BLACK
            1 -> BROWN
            2 -> RED
            3 -> ORANGE
            4 -> YELLOW
            5 -> GREEN
            6 -> BLUE
            7 -> VIOLET
            8 -> GRAY
            9 -> WHITE
            else -> ""
        }
    }

    // find text (color) from id
    fun idToColorText(colorId: Int): String {
        return when (colorId) {
            R.color.black32, R.drawable.black_square -> BLACK
            R.color.brown32, R.drawable.brown_square -> BROWN
            R.color.red32, R.drawable.red_square -> RED
            R.color.orange32, R.drawable.orange_square -> ORANGE
            R.color.yellow32, R.drawable.yellow_square -> YELLOW
            R.color.green32, R.drawable.green_square -> GREEN
            R.color.blue32, R.drawable.blue_square -> BLUE
            R.color.violet32, R.drawable.violet_square -> VIOLET
            R.color.gray32, R.drawable.gray_square -> GRAY
            R.color.white32, R.drawable.white_square -> WHITE
            R.color.gold32, R.drawable.gold_square -> GOLD
            R.color.silver32, R.drawable.silver_square -> SILVER
            else -> ""
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

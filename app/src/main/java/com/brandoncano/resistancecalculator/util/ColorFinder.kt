package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Find the correct color, string, or drawable based on the input.
 * Note: "${PLUS_MINUS}20%" -> blank - hidden in else conditions.
 */
object ColorFinder {

    fun textToColoredDrawable(color: String = ""): Int {
        return when (color) {
            C.BLACK ,                 "250 ${S.PPM_UNIT}" -> R.drawable.black_square
            C.BROWN , "${S.PM}1%"   , "100 ${S.PPM_UNIT}" -> R.drawable.brown_square
            C.RED   , "${S.PM}2%"   , "50 ${S.PPM_UNIT}"  -> R.drawable.red_square
            C.ORANGE,                 "15 ${S.PPM_UNIT}"  -> R.drawable.orange_square
            C.YELLOW,                 "25 ${S.PPM_UNIT}"  -> R.drawable.yellow_square
            C.GREEN , "${S.PM}0.5%" , "20 ${S.PPM_UNIT}"  -> R.drawable.green_square
            C.BLUE  , "${S.PM}0.25%", "10 ${S.PPM_UNIT}"  -> R.drawable.blue_square
            C.VIOLET, "${S.PM}0.1%" , "5 ${S.PPM_UNIT}"   -> R.drawable.violet_square
            C.GRAY  , "${S.PM}0.05%", "1 ${S.PPM_UNIT}"   -> R.drawable.gray_square
            C.WHITE                                       -> R.drawable.white_square
            C.GOLD  , "${S.PM}5%"                         -> R.drawable.gold_square
            C.SILVER, "${S.PM}10%"                        -> R.drawable.silver_square
            else                                          -> R.drawable.blank32
        }
    }

    fun textToColor(color: String = ""): Int {
        return when (color) {
            C.BLACK ,                 "250 ${S.PPM_UNIT}" -> R.color.black32
            C.BROWN , "${S.PM}1%"   , "100 ${S.PPM_UNIT}" -> R.color.brown32
            C.RED   , "${S.PM}2%"   , "50 ${S.PPM_UNIT}"  -> R.color.red32
            C.ORANGE,                 "15 ${S.PPM_UNIT}"  -> R.color.orange32
            C.YELLOW,                 "25 ${S.PPM_UNIT}"  -> R.color.yellow32
            C.GREEN , "${S.PM}0.5%" , "20 ${S.PPM_UNIT}"  -> R.color.green32
            C.BLUE  , "${S.PM}0.25%", "10 ${S.PPM_UNIT}"  -> R.color.blue32
            C.VIOLET, "${S.PM}0.1%" , "5 ${S.PPM_UNIT}"   -> R.color.violet32
            C.GRAY  , "${S.PM}0.05%", "1 ${S.PPM_UNIT}"   -> R.color.gray32
            C.WHITE                                       -> R.color.white32
            C.GOLD  , "${S.PM}5%"                         -> R.color.gold32
            C.SILVER, "${S.PM}10%"                        -> R.color.silver32
            else                                          -> R.color.resistor_blank
        }
    }

    fun numberToText(color: Int = -1): String {
        return when (color) {
            0 -> C.BLACK
            1 -> C.BROWN
            2 -> C.RED
            3 -> C.ORANGE
            4 -> C.YELLOW
            5 -> C.GREEN
            6 -> C.BLUE
            7 -> C.VIOLET
            8 -> C.GRAY
            9 -> C.WHITE
            else -> C.BLANK
        }
    }

    fun idToColorText(colorId: Int): String {
        return when (colorId) {
            R.color.black32,  R.drawable.black_square  -> C.BLACK
            R.color.brown32,  R.drawable.brown_square  -> C.BROWN
            R.color.red32,    R.drawable.red_square    -> C.RED
            R.color.orange32, R.drawable.orange_square -> C.ORANGE
            R.color.yellow32, R.drawable.yellow_square -> C.YELLOW
            R.color.green32,  R.drawable.green_square  -> C.GREEN
            R.color.blue32,   R.drawable.blue_square   -> C.BLUE
            R.color.violet32, R.drawable.violet_square -> C.VIOLET
            R.color.gray32,   R.drawable.gray_square   -> C.GRAY
            R.color.white32,  R.drawable.white_square  -> C.WHITE
            R.color.gold32,   R.drawable.gold_square   -> C.GOLD
            R.color.silver32, R.drawable.silver_square -> C.SILVER
            else -> ""
        }
    }

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

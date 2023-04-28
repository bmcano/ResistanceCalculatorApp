package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Find the correct color, string, or drawable based on the input.
 * Note: "${S.PM}20%" -> blank - hidden in else conditions.
 */
object ColorFinder {

    private fun textToColorPair(color: String = ""): Pair<Int, Int> {
        return when (color) {
            C.BLACK ,                 "250 ${S.PPM}" -> R.drawable.square_black  to R.color.black32
            C.BROWN , "${S.PM}1%"   , "100 ${S.PPM}" -> R.drawable.square_brown  to R.color.brown32
            C.RED   , "${S.PM}2%"   , "50 ${S.PPM}"  -> R.drawable.square_red    to R.color.red32
            C.ORANGE,                 "15 ${S.PPM}"  -> R.drawable.square_orange to R.color.orange32
            C.YELLOW,                 "25 ${S.PPM}"  -> R.drawable.square_yellow to R.color.yellow32
            C.GREEN , "${S.PM}0.5%" , "20 ${S.PPM}"  -> R.drawable.square_green  to R.color.green32
            C.BLUE  , "${S.PM}0.25%", "10 ${S.PPM}"  -> R.drawable.square_blue   to R.color.blue32
            C.VIOLET, "${S.PM}0.1%" , "5 ${S.PPM}"   -> R.drawable.square_violet to R.color.violet32
            C.GRAY  , "${S.PM}0.05%", "1 ${S.PPM}"   -> R.drawable.square_gray   to R.color.gray32
            C.WHITE                                  -> R.drawable.square_white  to R.color.white32
            C.GOLD  , "${S.PM}5%"                    -> R.drawable.square_gold   to R.color.gold32
            C.SILVER, "${S.PM}10%"                   -> R.drawable.square_silver to R.color.silver32
            else                                     -> R.drawable.square_blank  to R.color.resistor_blank
        }
    }

    fun textToColoredDrawable(color: String = ""): Int = textToColorPair(color).first

    fun textToColor(color: String = ""): Int = textToColorPair(color).second

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
            R.color.black32,  R.drawable.square_black  -> C.BLACK
            R.color.brown32,  R.drawable.square_brown  -> C.BROWN
            R.color.red32,    R.drawable.square_red    -> C.RED
            R.color.orange32, R.drawable.square_orange -> C.ORANGE
            R.color.yellow32, R.drawable.square_yellow -> C.YELLOW
            R.color.green32,  R.drawable.square_green  -> C.GREEN
            R.color.blue32,   R.drawable.square_blue   -> C.BLUE
            R.color.violet32, R.drawable.square_violet -> C.VIOLET
            R.color.gray32,   R.drawable.square_gray   -> C.GRAY
            R.color.white32,  R.drawable.square_white  -> C.WHITE
            R.color.gold32,   R.drawable.square_gold   -> C.GOLD
            R.color.silver32, R.drawable.square_silver -> C.SILVER
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

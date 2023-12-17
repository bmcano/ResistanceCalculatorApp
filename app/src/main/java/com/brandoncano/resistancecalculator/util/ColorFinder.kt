package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Find the correct color, either as a string or resource based on the input.
 * Note: "${S.PM}20%" -> resistor_blank -> hidden in else conditions.
 */
object ColorFinder {

    private fun textToColorPair(color: String = ""): Pair<Int, Int> {
        return when (color) {
            C.BLACK ,                 "250 ${S.PPM}" -> R.drawable.square_black  to R.color.black
            C.BROWN , "${S.PM}1%"   , "100 ${S.PPM}" -> R.drawable.square_brown  to R.color.brown
            C.RED   , "${S.PM}2%"   , "50 ${S.PPM}"  -> R.drawable.square_red    to R.color.red
            C.ORANGE,                 "15 ${S.PPM}"  -> R.drawable.square_orange to R.color.orange
            C.YELLOW,                 "25 ${S.PPM}"  -> R.drawable.square_yellow to R.color.yellow
            C.GREEN , "${S.PM}0.5%" , "20 ${S.PPM}"  -> R.drawable.square_green  to R.color.green
            C.BLUE  , "${S.PM}0.25%", "10 ${S.PPM}"  -> R.drawable.square_blue   to R.color.blue
            C.VIOLET, "${S.PM}0.1%" , "5 ${S.PPM}"   -> R.drawable.square_violet to R.color.violet
            C.GRAY  , "${S.PM}0.05%", "1 ${S.PPM}"   -> R.drawable.square_gray   to R.color.gray
            C.WHITE                                  -> R.drawable.square_white  to R.color.white
            C.GOLD  , "${S.PM}5%"                    -> R.drawable.square_gold   to R.color.gold
            C.SILVER, "${S.PM}10%"                   -> R.drawable.square_silver to R.color.silver
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
            R.color.black,  R.drawable.square_black  -> C.BLACK
            R.color.brown,  R.drawable.square_brown  -> C.BROWN
            R.color.red,    R.drawable.square_red    -> C.RED
            R.color.orange, R.drawable.square_orange -> C.ORANGE
            R.color.yellow, R.drawable.square_yellow -> C.YELLOW
            R.color.green,  R.drawable.square_green  -> C.GREEN
            R.color.blue,   R.drawable.square_blue   -> C.BLUE
            R.color.violet, R.drawable.square_violet -> C.VIOLET
            R.color.gray,   R.drawable.square_gray   -> C.GRAY
            R.color.white,  R.drawable.square_white  -> C.WHITE
            R.color.gold,   R.drawable.square_gold   -> C.GOLD
            R.color.silver, R.drawable.square_silver -> C.SILVER
            else -> ""
        }
    }
}

package com.brandoncano.resistancecalculator.util

import androidx.compose.ui.graphics.Color
import com.brandoncano.resistancecalculator.ui.theme.black
import com.brandoncano.resistancecalculator.ui.theme.blue
import com.brandoncano.resistancecalculator.ui.theme.brown
import com.brandoncano.resistancecalculator.ui.theme.gold
import com.brandoncano.resistancecalculator.ui.theme.gray
import com.brandoncano.resistancecalculator.ui.theme.green
import com.brandoncano.resistancecalculator.ui.theme.orange
import com.brandoncano.resistancecalculator.ui.theme.red
import com.brandoncano.resistancecalculator.ui.theme.resistor_blank
import com.brandoncano.resistancecalculator.ui.theme.silver
import com.brandoncano.resistancecalculator.ui.theme.violet
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.ui.theme.yellow
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Find the correct color, either as a string or resource based on the input.
 * Note: "${S.PM}20%" -> resistor_blank -> hidden in else conditions.
 */
object ColorFinder {

    fun textToColor(text: String): Color {
        return when (text) {
            C.BLACK ,                 "250 ${S.PPM}" -> black
            C.BROWN , "${S.PM}1%"   , "100 ${S.PPM}" -> brown
            C.RED   , "${S.PM}2%"   , "50 ${S.PPM}"  -> red
            C.ORANGE,                 "15 ${S.PPM}"  -> orange
            C.YELLOW,                 "25 ${S.PPM}"  -> yellow
            C.GREEN , "${S.PM}0.5%" , "20 ${S.PPM}"  -> green
            C.BLUE  , "${S.PM}0.25%", "10 ${S.PPM}"  -> blue
            C.VIOLET, "${S.PM}0.1%" , "5 ${S.PPM}"   -> violet
            C.GRAY  , "${S.PM}0.05%", "1 ${S.PPM}"   -> gray
            C.WHITE                                  -> white
            C.GOLD  , "${S.PM}5%"                    -> gold
            C.SILVER, "${S.PM}10%"                   -> silver
            else                                     -> resistor_blank
        }
    }

    fun colorToText(color: Color): String {
        return when (color) {
            black -> C.BLACK
            brown -> C.BROWN
            red -> C.RED
            orange -> C.ORANGE
            yellow -> C.YELLOW
            green -> C.GREEN
            blue -> C.BLUE
            violet -> C.VIOLET
            gray -> C.GRAY
            white -> C.WHITE
            gold -> C.GOLD
            silver -> C.SILVER
            else -> C.BLANK
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
}

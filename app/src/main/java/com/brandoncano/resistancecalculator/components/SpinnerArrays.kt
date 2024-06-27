package com.brandoncano.resistancecalculator.components

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Holds array information for the spinners (dropdowns).
 */
object SpinnerArrays {

    val numberArray = arrayOf(
        SpinnerItem(C.BLACK, R.drawable.square_black, "0"),
        SpinnerItem(C.BROWN, R.drawable.square_brown, "1"),
        SpinnerItem(C.RED, R.drawable.square_red, "2"),
        SpinnerItem(C.ORANGE, R.drawable.square_orange, "3"),
        SpinnerItem(C.YELLOW, R.drawable.square_yellow, "4"),
        SpinnerItem(C.GREEN, R.drawable.square_green, "5"),
        SpinnerItem(C.BLUE, R.drawable.square_blue, "6"),
        SpinnerItem(C.VIOLET, R.drawable.square_violet, "7"),
        SpinnerItem(C.GRAY, R.drawable.square_gray, "8"),
        SpinnerItem(C.WHITE, R.drawable.square_white, "9"),
    )

    val multiplierArray = arrayOf(
        SpinnerItem(C.BLACK, R.drawable.square_black, "x 1"),
        SpinnerItem(C.BROWN, R.drawable.square_brown, "x 10"),
        SpinnerItem(C.RED, R.drawable.square_red, "x 100"),
        SpinnerItem(C.ORANGE, R.drawable.square_orange, "x 1k"),
        SpinnerItem(C.YELLOW, R.drawable.square_yellow, "x 10k"),
        SpinnerItem(C.GREEN, R.drawable.square_green, "x 100k"),
        SpinnerItem(C.BLUE, R.drawable.square_blue, "x 1M"),
        SpinnerItem(C.VIOLET, R.drawable.square_violet, "x 10M"),
        SpinnerItem(C.GRAY, R.drawable.square_gray, "x 100M"),
        SpinnerItem(C.WHITE, R.drawable.square_white, "x 1G"),
        SpinnerItem(C.GOLD, R.drawable.square_gold, "x 0.1"),
        SpinnerItem(C.SILVER, R.drawable.square_silver, "x 0.01"),
    )

    fun getToleranceArray(isVtC: Boolean = false): Array<SpinnerItem> {
        return arrayOf(
            SpinnerItem(C.BROWN, R.drawable.square_brown, "${S.PM}1%", isVtC),
            SpinnerItem(C.RED, R.drawable.square_red, "${S.PM}2%", isVtC),
            SpinnerItem(C.GREEN, R.drawable.square_green, "${S.PM}0.5%", isVtC),
            SpinnerItem(C.BLUE, R.drawable.square_blue, "${S.PM}0.25%", isVtC),
            SpinnerItem(C.VIOLET, R.drawable.square_violet, "${S.PM}0.1%", isVtC),
            SpinnerItem(C.GRAY, R.drawable.square_gray, "${S.PM}0.05%", isVtC),
            SpinnerItem(C.GOLD, R.drawable.square_gold, "${S.PM}5%", isVtC),
            SpinnerItem(C.SILVER, R.drawable.square_silver, "${S.PM}10%", isVtC),
        )
    }

    fun getPpmArray(isVtC: Boolean = false): Array<SpinnerItem> {
        return arrayOf(
            SpinnerItem(C.BLACK, R.drawable.square_black, "250 ${S.PPM}", isVtC),
            SpinnerItem(C.BROWN, R.drawable.square_brown, "100 ${S.PPM}", isVtC),
            SpinnerItem(C.RED, R.drawable.square_red, "50 ${S.PPM}", isVtC),
            SpinnerItem(C.ORANGE, R.drawable.square_orange, "15 ${S.PPM}", isVtC),
            SpinnerItem(C.YELLOW, R.drawable.square_yellow, "25 ${S.PPM}", isVtC),
            SpinnerItem(C.GREEN, R.drawable.square_green, "20 ${S.PPM}", isVtC),
            SpinnerItem(C.BLUE, R.drawable.square_blue, "10 ${S.PPM}", isVtC),
            SpinnerItem(C.VIOLET, R.drawable.square_violet, "5 ${S.PPM}", isVtC),
            SpinnerItem(C.GRAY, R.drawable.square_gray, "1 ${S.PPM}", isVtC),
        )
    }

    val unitsArray = arrayOf(S.OHMS, S.KOHMS, S.MOHMS, S.GOHMS)
}

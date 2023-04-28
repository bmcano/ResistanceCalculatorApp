package com.brandoncano.resistancecalculator.components

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Holds array information for the spinners.
 */
object SpinnerArrays {

    val ppmArray = arrayOf(
        SpinnerItem(C.BLACK, R.drawable.square_black),
        SpinnerItem(C.BROWN, R.drawable.square_brown),
        SpinnerItem(C.RED, R.drawable.square_red),
        SpinnerItem(C.ORANGE, R.drawable.square_orange),
        SpinnerItem(C.YELLOW, R.drawable.square_yellow),
        SpinnerItem(C.GREEN, R.drawable.square_green),
        SpinnerItem(C.BLUE, R.drawable.square_blue),
        SpinnerItem(C.VIOLET, R.drawable.square_violet),
        SpinnerItem(C.GRAY, R.drawable.square_gray)
    )

    val numberArray = arrayOf(
        *ppmArray,
        SpinnerItem(C.WHITE, R.drawable.square_white)
    )

    val multiplierArray = arrayOf(
        *numberArray,
        SpinnerItem(C.GOLD, R.drawable.square_gold),
        SpinnerItem(C.SILVER, R.drawable.square_silver)
    )

    val toleranceArray = arrayOf(
        SpinnerItem(C.BROWN, R.drawable.square_brown),
        SpinnerItem(C.RED, R.drawable.square_red),
        SpinnerItem(C.GREEN, R.drawable.square_green),
        SpinnerItem(C.BLUE, R.drawable.square_blue),
        SpinnerItem(C.VIOLET, R.drawable.square_violet),
        SpinnerItem(C.GRAY, R.drawable.square_gray),
        SpinnerItem(C.GOLD, R.drawable.square_gold),
        SpinnerItem(C.SILVER, R.drawable.square_silver)
    )

    val toleranceTextArray = arrayOf(
        SpinnerItem("${S.PM}1%", R.drawable.square_brown),
        SpinnerItem("${S.PM}2%", R.drawable.square_red),
        SpinnerItem("${S.PM}0.5%", R.drawable.square_green),
        SpinnerItem("${S.PM}0.25%", R.drawable.square_blue),
        SpinnerItem("${S.PM}0.1%", R.drawable.square_violet),
        SpinnerItem("${S.PM}0.05%", R.drawable.square_gray),
        SpinnerItem("${S.PM}5%", R.drawable.square_gold),
        SpinnerItem("${S.PM}10%", R.drawable.square_silver),
        SpinnerItem("${S.PM}20%", R.drawable.square_blank),
    )

    val ppmTextArray = arrayOf(
        SpinnerItem("250 ${S.PPM}", R.drawable.square_black),
        SpinnerItem("100 ${S.PPM}", R.drawable.square_brown),
        SpinnerItem("50 ${S.PPM}", R.drawable.square_red),
        SpinnerItem("15 ${S.PPM}", R.drawable.square_orange),
        SpinnerItem("25 ${S.PPM}", R.drawable.square_yellow),
        SpinnerItem("20 ${S.PPM}", R.drawable.square_green),
        SpinnerItem("10 ${S.PPM}", R.drawable.square_blue),
        SpinnerItem("5 ${S.PPM}", R.drawable.square_violet),
        SpinnerItem("1 ${S.PPM}", R.drawable.square_gray)
    )

    val unitsArray = arrayOf(S.Ohms, S.kOhms, S.MOhms, S.GOhms)
}

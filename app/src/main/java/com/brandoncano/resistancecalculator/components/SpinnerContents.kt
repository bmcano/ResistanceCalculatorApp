package com.brandoncano.resistancecalculator.components

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Job: Holds array information for the spinners.
 */
object SpinnerContents {

    private val BLACK = SpinnerItem("Black", R.drawable.square_black)
    private val BROWN = SpinnerItem("Brown", R.drawable.square_brown)
    private val RED = SpinnerItem("Red", R.drawable.square_red)
    private val ORANGE = SpinnerItem("Orange", R.drawable.square_orange)
    private val YELLOW = SpinnerItem("Yellow", R.drawable.square_yellow)
    private val GREEN = SpinnerItem("Green", R.drawable.square_green)
    private val BLUE = SpinnerItem("Blue", R.drawable.square_blue)
    private val VIOLET = SpinnerItem("Violet", R.drawable.square_violet)
    private val GRAY = SpinnerItem("Gray", R.drawable.square_gray)
    private val WHITE = SpinnerItem("White", R.drawable.square_white)
    private val GOLD = SpinnerItem("Gold", R.drawable.square_gold)
    private val SILVER = SpinnerItem("Silver", R.drawable.square_silver)

    val numberArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE)

    val multiplierArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE, GOLD, SILVER)

    val toleranceArray =
        arrayOf(BROWN, RED, GREEN, BLUE, VIOLET, GRAY, GOLD, SILVER)

    val ppmArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY)

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

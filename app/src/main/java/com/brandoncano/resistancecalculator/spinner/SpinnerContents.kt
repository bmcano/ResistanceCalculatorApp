package com.brandoncano.resistancecalculator.spinner

import com.brandoncano.resistancecalculator.R

/**
 * @author Brandon
 *
 * Job: holds array information for the spinners
 */
object SpinnerContents {

    private const val OMEGA: String = "Ω"
    private const val PLUS_MINUS: String = "±"
    private const val DEGREE: String = "°"

    private val BLACK = SpinnerItem("Black", R.drawable.black32)
    private val BROWN = SpinnerItem("Brown", R.drawable.brown32)
    private val RED = SpinnerItem("Red", R.drawable.red32)
    private val ORANGE = SpinnerItem("Orange", R.drawable.orange32)
    private val YELLOW = SpinnerItem("Yellow", R.drawable.yellow32)
    private val GREEN = SpinnerItem("Green", R.drawable.green32)
    private val BLUE = SpinnerItem("Blue", R.drawable.blue32)
    private val VIOLET = SpinnerItem("Violet", R.drawable.violet32)
    private val GRAY = SpinnerItem("Gray", R.drawable.gray32)
    private val WHITE = SpinnerItem("White", R.drawable.white32)
    private val GOLD = SpinnerItem("Gold", R.drawable.gold32)
    private val SILVER = SpinnerItem("Silver", R.drawable.silver32)

    val numberArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE)

    val multiplierArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE, GOLD, SILVER)

    val toleranceArray =
        arrayOf(BROWN, RED, GREEN, BLUE, VIOLET, GRAY, GOLD, SILVER)

    val ppmArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY)

    val toleranceTextArray = arrayOf(
        SpinnerItem("${PLUS_MINUS}1%", R.drawable.brown32),
        SpinnerItem("${PLUS_MINUS}2%", R.drawable.red32),
        SpinnerItem("${PLUS_MINUS}0.5%", R.drawable.green32),
        SpinnerItem("${PLUS_MINUS}0.25%", R.drawable.blue32),
        SpinnerItem("${PLUS_MINUS}0.1%", R.drawable.violet32),
        SpinnerItem("${PLUS_MINUS}0.05%", R.drawable.gray32),
        SpinnerItem("${PLUS_MINUS}5%", R.drawable.gold32),
        SpinnerItem("${PLUS_MINUS}10%", R.drawable.silver32),
        SpinnerItem("${PLUS_MINUS}20%", R.drawable.blank32),
    )

    val ppmTextArray = arrayOf(
        SpinnerItem("250 ppm/${DEGREE}C", R.drawable.black32),
        SpinnerItem("100 ppm/${DEGREE}C", R.drawable.brown32),
        SpinnerItem("50 ppm/${DEGREE}C", R.drawable.red32),
        SpinnerItem("15 ppm/${DEGREE}C", R.drawable.orange32),
        SpinnerItem("25 ppm/${DEGREE}C", R.drawable.yellow32),
        SpinnerItem("20 ppm/${DEGREE}C", R.drawable.green32),
        SpinnerItem("10 ppm/${DEGREE}C", R.drawable.blue32),
        SpinnerItem("5 ppm/${DEGREE}C", R.drawable.violet32),
        SpinnerItem("1 ppm/${DEGREE}C", R.drawable.gray32)
    )

    val unitsArray =
        arrayOf(OMEGA, "k${OMEGA}", "M${OMEGA}", "G${OMEGA}")
}

package com.brandoncano.resistancecalculator.spinner

import com.brandoncano.resistancecalculator.R

/**
 * Job: Holds array information for the spinners.
 */
object SpinnerContents {

    private const val OMEGA: String = "Ω"
    private const val PLUS_MINUS: String = "±"
    private const val DEGREE: String = "°"

    private val BLACK = SpinnerItem("Black", R.drawable.black_square)
    private val BROWN = SpinnerItem("Brown", R.drawable.brown_square)
    private val RED = SpinnerItem("Red", R.drawable.red_square)
    private val ORANGE = SpinnerItem("Orange", R.drawable.orange_square)
    private val YELLOW = SpinnerItem("Yellow", R.drawable.yellow_square)
    private val GREEN = SpinnerItem("Green", R.drawable.green_square)
    private val BLUE = SpinnerItem("Blue", R.drawable.blue_square)
    private val VIOLET = SpinnerItem("Violet", R.drawable.violet_square)
    private val GRAY = SpinnerItem("Gray", R.drawable.gray_square)
    private val WHITE = SpinnerItem("White", R.drawable.white_square)
    private val GOLD = SpinnerItem("Gold", R.drawable.gold_square)
    private val SILVER = SpinnerItem("Silver", R.drawable.silver_square)

    val numberArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE)

    val multiplierArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY, WHITE, GOLD, SILVER)

    val toleranceArray =
        arrayOf(BROWN, RED, GREEN, BLUE, VIOLET, GRAY, GOLD, SILVER)

    val ppmArray =
        arrayOf(BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GRAY)

    val toleranceTextArray = arrayOf(
        SpinnerItem("${PLUS_MINUS}1%", R.drawable.brown_square),
        SpinnerItem("${PLUS_MINUS}2%", R.drawable.red_square),
        SpinnerItem("${PLUS_MINUS}0.5%", R.drawable.green_square),
        SpinnerItem("${PLUS_MINUS}0.25%", R.drawable.blue_square),
        SpinnerItem("${PLUS_MINUS}0.1%", R.drawable.violet_square),
        SpinnerItem("${PLUS_MINUS}0.05%", R.drawable.gray_square),
        SpinnerItem("${PLUS_MINUS}5%", R.drawable.gold_square),
        SpinnerItem("${PLUS_MINUS}10%", R.drawable.silver_square),
        SpinnerItem("${PLUS_MINUS}20%", R.drawable.blank32),
    )

    val ppmTextArray = arrayOf(
        SpinnerItem("250 ppm/${DEGREE}C", R.drawable.black_square),
        SpinnerItem("100 ppm/${DEGREE}C", R.drawable.brown_square),
        SpinnerItem("50 ppm/${DEGREE}C", R.drawable.red_square),
        SpinnerItem("15 ppm/${DEGREE}C", R.drawable.orange_square),
        SpinnerItem("25 ppm/${DEGREE}C", R.drawable.yellow_square),
        SpinnerItem("20 ppm/${DEGREE}C", R.drawable.green_square),
        SpinnerItem("10 ppm/${DEGREE}C", R.drawable.blue_square),
        SpinnerItem("5 ppm/${DEGREE}C", R.drawable.violet_square),
        SpinnerItem("1 ppm/${DEGREE}C", R.drawable.gray_square)
    )

    val unitsArray = arrayOf(OMEGA, "k$OMEGA", "M$OMEGA", "G$OMEGA")
}

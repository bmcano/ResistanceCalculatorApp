package com.brandoncano.resistancecalculator.spinner

import com.brandoncano.resistancecalculator.R

/**
 * @author: Brandon
 *
 * Job: contains the arrays for each of the drop downs that exist in the app
 */
enum class SpinnerContents(val array: Array<SpinnerItem>) {

    NUMBER(
        arrayOf(
            S.BLACK, S.BROWN, S.RED, S.ORANGE, S.YELLOW,
            S.GREEN, S.BLUE, S.VIOLET, S.GRAY, S.WHITE
        )
    ),

    MULTIPLIER(
        arrayOf(
            S.BLACK, S.BROWN, S.RED, S.ORANGE, S.YELLOW, S.GREEN,
            S.BLUE, S.VIOLET, S.GRAY, S.WHITE, S.GOLD, S.SILVER
        )
    ),

    TOLERANCE(
        arrayOf(
            S.BROWN, S.RED, S.GREEN, S.BLUE,
            S.VIOLET, S.GRAY, S.GOLD, S.SILVER
        )
    ),

    PPM(
        arrayOf(
            S.BLACK, S.BROWN, S.RED, S.ORANGE, S.YELLOW,
            S.GREEN, S.BLUE, S.VIOLET, S.GRAY
        )
    ),

    TOLERANCE_TEXT(
        arrayOf(
            SpinnerItem("${S.PLUS_MINUS}1%", R.drawable.brown32),
            SpinnerItem("${S.PLUS_MINUS}2%", R.drawable.red32),
            SpinnerItem("${S.PLUS_MINUS}0.5%", R.drawable.green32),
            SpinnerItem("${S.PLUS_MINUS}0.25%", R.drawable.blue32),
            SpinnerItem("${S.PLUS_MINUS}0.1%", R.drawable.violet32),
            SpinnerItem("${S.PLUS_MINUS}0.05%", R.drawable.gray32),
            SpinnerItem("${S.PLUS_MINUS}5%", R.drawable.gold32),
            SpinnerItem("${S.PLUS_MINUS}10%", R.drawable.silver32),
            SpinnerItem("${S.PLUS_MINUS}20%", R.drawable.blank32),
        )
    ),

    PPM_TEXT(
        arrayOf(
            SpinnerItem("250 ppm/${S.DEGREE}C", R.drawable.black32),
            SpinnerItem("100 ppm/${S.DEGREE}C", R.drawable.brown32),
            SpinnerItem("50 ppm/${S.DEGREE}C", R.drawable.red32),
            SpinnerItem("15 ppm/${S.DEGREE}C", R.drawable.orange32),
            SpinnerItem("25 ppm/${S.DEGREE}C", R.drawable.yellow32),
            SpinnerItem("20 ppm/${S.DEGREE}C", R.drawable.green32),
            SpinnerItem("10 ppm/${S.DEGREE}C", R.drawable.blue32),
            SpinnerItem("5 ppm/${S.DEGREE}C", R.drawable.violet32),
            SpinnerItem("1 ppm/${S.DEGREE}C", R.drawable.gray32)
        )
    );

    enum class SimpleArray(val array: Array<String>) {
        UNITS(arrayOf(S.OMEGA, "k${S.OMEGA}", "M${S.OMEGA}", "G${S.OMEGA}"))
    }

    private object S {
        val BLACK: SpinnerItem = SpinnerItem("Black", R.drawable.black32)
        val BROWN: SpinnerItem = SpinnerItem("Brown", R.drawable.brown32)
        val RED = SpinnerItem("Red", R.drawable.red32)
        val ORANGE = SpinnerItem("Orange", R.drawable.orange32)
        val YELLOW = SpinnerItem("Yellow", R.drawable.yellow32)
        val GREEN = SpinnerItem("Green", R.drawable.green32)
        val BLUE = SpinnerItem("Blue", R.drawable.blue32)
        val VIOLET = SpinnerItem("Violet", R.drawable.violet32)
        val GRAY = SpinnerItem("Gray", R.drawable.gray32)
        val WHITE = SpinnerItem("White", R.drawable.white32)
        val GOLD = SpinnerItem("Gold", R.drawable.gold32)
        val SILVER = SpinnerItem("Silver", R.drawable.silver32)

        const val OMEGA: String = "Ω"
        const val PLUS_MINUS: String = "±"
        const val DEGREE: String = "°"
    }
}

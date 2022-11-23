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
            Item.BLACK, Item.BROWN, Item.RED, Item.ORANGE, Item.YELLOW,
            Item.GREEN, Item.BLUE, Item.VIOLET, Item.GRAY, Item.WHITE
        )
    ),

    MULTIPLIER(
        arrayOf(
            Item.BLACK, Item.BROWN, Item.RED, Item.ORANGE, Item.YELLOW, Item.GREEN,
            Item.BLUE, Item.VIOLET, Item.GRAY, Item.WHITE, Item.GOLD, Item.SILVER
        )
    ),

    TOLERANCE(
        arrayOf(
            Item.BROWN, Item.RED, Item.GREEN, Item.BLUE,
            Item.VIOLET, Item.GRAY, Item.GOLD, Item.SILVER
        )
    ),

    PPM(
        arrayOf(
            Item.BLACK, Item.BROWN, Item.RED, Item.ORANGE, Item.YELLOW,
            Item.GREEN, Item.BLUE, Item.VIOLET, Item.GRAY
        )
    ),

    TOLERANCE_TEXT(
        arrayOf(
            SpinnerItem("${Item.PLUS_MINUS}1%", R.drawable.brown32),
            SpinnerItem("${Item.PLUS_MINUS}2%", R.drawable.red32),
            SpinnerItem("${Item.PLUS_MINUS}0.5%", R.drawable.green32),
            SpinnerItem("${Item.PLUS_MINUS}0.25%", R.drawable.blue32),
            SpinnerItem("${Item.PLUS_MINUS}0.1%", R.drawable.violet32),
            SpinnerItem("${Item.PLUS_MINUS}0.05%", R.drawable.gray32),
            SpinnerItem("${Item.PLUS_MINUS}5%", R.drawable.gold32),
            SpinnerItem("${Item.PLUS_MINUS}10%", R.drawable.silver32),
            SpinnerItem("${Item.PLUS_MINUS}20%", R.drawable.blank32),
        )
    ),

    PPM_TEXT(
        arrayOf(
            SpinnerItem("250 ppm/${Item.DEGREE}C", R.drawable.black32),
            SpinnerItem("100 ppm/${Item.DEGREE}C", R.drawable.brown32),
            SpinnerItem("50 ppm/${Item.DEGREE}C", R.drawable.red32),
            SpinnerItem("15 ppm/${Item.DEGREE}C", R.drawable.orange32),
            SpinnerItem("25 ppm/${Item.DEGREE}C", R.drawable.yellow32),
            SpinnerItem("20 ppm/${Item.DEGREE}C", R.drawable.green32),
            SpinnerItem("10 ppm/${Item.DEGREE}C", R.drawable.blue32),
            SpinnerItem("5 ppm/${Item.DEGREE}C", R.drawable.violet32),
            SpinnerItem("1 ppm/${Item.DEGREE}C", R.drawable.gray32)
        )
    );

    enum class SimpleArray(val array: Array<String>) {
        UNITS(arrayOf(Item.OMEGA, "k${Item.OMEGA}", "M${Item.OMEGA}", "G${Item.OMEGA}"))
    }

    private object Item {
        val BLACK = SpinnerItem("Black", R.drawable.black32)
        val BROWN = SpinnerItem("Brown", R.drawable.brown32)
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

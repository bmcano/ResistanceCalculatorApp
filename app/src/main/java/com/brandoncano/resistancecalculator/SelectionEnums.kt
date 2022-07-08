package com.brandoncano.resistancecalculator

import com.brandoncano.resistancecalculator.spinner.SpinnerItem

/**
 * Job: contains the arrays for each of the drop downs that exist in the app
 *
 * @author: Brandon
 */

enum class SelectionEnums(val array: Array<String>){
    NUMBER(arrayOf(
        "Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Gray", "White"
    )),

    MULTIPLIER(arrayOf(
        "Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Gray", "White",
        "Gold", "Silver"
    )),

    TOLERANCE(arrayOf(
        "Brown", "Red", "Green", "Blue", "Violet", "Gray", "Gold", "Silver", "None"
    )),

    PPM(arrayOf(
        "Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Gray"
    )),

    UNITS(arrayOf(
        S.OMEGA, "k${S.OMEGA}", "M${S.OMEGA}", "G${S.OMEGA}"
    )),

    TOLERANCE_TEXT(arrayOf(
        "${S.PLUS_MINUS}1%", "${S.PLUS_MINUS}2%", "${S.PLUS_MINUS}0.5%",
        "${S.PLUS_MINUS}0.25%", "${S.PLUS_MINUS}0.1%", "${S.PLUS_MINUS}0.05%",
        "${S.PLUS_MINUS}5%", "${S.PLUS_MINUS}10%", "${S.PLUS_MINUS}20%"
    )),

    PPM_TEXT(arrayOf(
        "250 ppm/${S.DEGREE}C", "100 ppm/${S.DEGREE}C", "50 ppm/${S.DEGREE}C",
        "15 ppm/${S.DEGREE}C", "25 ppm/${S.DEGREE}C", "20 ppm/${S.DEGREE}C",
        "10 ppm/${S.DEGREE}C", "5 ppm/${S.DEGREE}C", "1 ppm/${S.DEGREE}C"
    ))
    ;
    private object S {
        const val OMEGA: String = "Ω"
        const val PLUS_MINUS: String = "±"
        const val DEGREE: String = "°"
    }
}

// This will be progressively written, then will replace the original
//"Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Gray", "White"
enum class SelectionEnums1(val array: Array<SpinnerItem>) {
    NUMBER(arrayOf(
        SpinnerItem("Black", R.drawable.black32),
        SpinnerItem("Brown", R.drawable.brown32),
        SpinnerItem("Red", R.drawable.red32),
        SpinnerItem("Orange", R.drawable.orange32),
        SpinnerItem("Yellow", R.drawable.yellow32),
        SpinnerItem("Green", R.drawable.green32),
        SpinnerItem("Blue", R.drawable.blue32),
        SpinnerItem("Violet", R.drawable.violet32),
        SpinnerItem("Gray", R.drawable.gray32),
        SpinnerItem("White", R.drawable.white32)
    )),

    MULTIPLIER(arrayOf(
        SpinnerItem("Black", R.drawable.black32),
        SpinnerItem("Brown", R.drawable.brown32),
        SpinnerItem("Red", R.drawable.red32),
        SpinnerItem("Orange", R.drawable.orange32),
        SpinnerItem("Yellow", R.drawable.yellow32),
        SpinnerItem("Green", R.drawable.green32),
        SpinnerItem("Blue", R.drawable.blue32),
        SpinnerItem("Violet", R.drawable.violet32),
        SpinnerItem("Gray", R.drawable.gray32),
        SpinnerItem("White", R.drawable.white32),
        SpinnerItem("Gold", R.drawable.gold32),
        SpinnerItem("Silver", R.drawable.silver32)
    )),

    TOLERANCE(arrayOf(
        SpinnerItem("Brown", R.drawable.brown32),
        SpinnerItem("Red", R.drawable.red32),
        SpinnerItem("Green", R.drawable.green32),
        SpinnerItem("Blue", R.drawable.blue32),
        SpinnerItem("Violet", R.drawable.violet32),
        SpinnerItem("Gray", R.drawable.gray32),
        SpinnerItem("Gold", R.drawable.gold32),
        SpinnerItem("Silver", R.drawable.silver32),
        SpinnerItem("None", R.drawable.blank32)
    )),

    PPM(arrayOf(
        SpinnerItem("Black", R.drawable.black32),
        SpinnerItem("Brown", R.drawable.brown32),
        SpinnerItem("Red", R.drawable.red32),
        SpinnerItem("Orange", R.drawable.orange32),
        SpinnerItem("Yellow", R.drawable.yellow32),
        SpinnerItem("Green", R.drawable.green32),
        SpinnerItem("Blue", R.drawable.blue32),
        SpinnerItem("Violet", R.drawable.violet32),
        SpinnerItem("Gray", R.drawable.gray32)
    ))
}

package com.example.resistancecalculator

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
}

private object S {
    const val OMEGA: String = "Ω"
    const val PLUS_MINUS: String = "±"
    const val DEGREE: String = "°"
}

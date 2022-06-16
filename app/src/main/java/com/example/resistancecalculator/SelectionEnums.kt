package com.example.resistancecalculator

enum class SelectionEnums(val array: Array<String>){

    NUMBER(arrayOf(
        "Black", "Brown", "Red", "Orange", "Yellow",
        "Green", "Blue", "Violet", "Gray", "White"
    )),

    MULTIPLIER(arrayOf(
        "Black", "Brown", "Red", "Orange", "Yellow", "Green",
        "Blue", "Violet", "Gray", "White", "Gold", "Silver"
    )),

    TOLERANCE(arrayOf(
        "Brown", "Red", "Green", "Blue", "Violet",
        "Gray", "Gold", "Silver", "None"
    )),

    PPM(arrayOf(
        "Black", "Brown", "Red",
        "Orange", "Yellow", "Green",
        "Blue", "Violet", "Gray"
    )),

    UNITS(arrayOf(
        Symbols.OMEGA, "k${Symbols.OMEGA}", "M${Symbols.OMEGA}", "G${Symbols.OMEGA}"
    )),

    TOLERANCE_TEXT(arrayOf(
        "${Symbols.PLUS_MINUS}1%", "${Symbols.PLUS_MINUS}2%", "${Symbols.PLUS_MINUS}0.5%",
        "${Symbols.PLUS_MINUS}0.25%", "${Symbols.PLUS_MINUS}0.1%", "${Symbols.PLUS_MINUS}0.05%",
        "${Symbols.PLUS_MINUS}5%", "${Symbols.PLUS_MINUS}10%", "${Symbols.PLUS_MINUS}20%"
    )),

    PPM_TEXT(arrayOf(
        "250 ppm/${Symbols.DEGREE}C", "100 ppm/${Symbols.DEGREE}C", "50 ppm/${Symbols.DEGREE}C",
        "15 ppm/${Symbols.DEGREE}C", "25 ppm/${Symbols.DEGREE}C", "20 ppm/${Symbols.DEGREE}C",
        "10 ppm/${Symbols.DEGREE}C", "5 ppm/${Symbols.DEGREE}C", "1 ppm/${Symbols.DEGREE}C"
    ))
}

// private const for special symbols
private object Symbols {
    const val OMEGA: String = "Ω"
    const val PLUS_MINUS: String = "±"
    const val DEGREE: String = "°"
}
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

    TOLERANCE(arrayOf( // orange, yellow, None(?)
    "Gold", "Silver", "Brown", "Red",
    "Green", "Blue", "Violet", "Gray",
    )),

    PPM(arrayOf( // black, green, gray
        "Brown", "Red", "Orange",
        "Yellow", "Blue", "Violet"
    )),

    UNITS(arrayOf(
        "Ω", "kΩ", "MΩ", "GΩ"
    )),

    TOLERANCE_TEXT(arrayOf(
        "±1%", "±2%", "±0.05%", "±0.02%", "±0.5%", "±0.25%",
        "±0.1%", "±0.01%", "±5%", "±10%", "±20%"
    )),

    PPM_TEXT(arrayOf(
        "250 ppm/K", "100 ppm/K", "50 ppm/K",
        "15 ppm/K", "25 ppm/K", "20 ppm/K",
        "10 ppm/K", "5 ppm/K", "1 ppm/K"
    ))
}
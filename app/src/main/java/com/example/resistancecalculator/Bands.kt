package com.example.resistancecalculator

enum class Bands(val array: Array<String>){
    NUMBER(arrayOf(
        "Black", "Brown", "Red", "Orange", "Yellow",
        "Green", "Blue", "Violet", "Gray", "White"
    )),

    MULTIPLIER(arrayOf(
        "Black", "Brown", "Red", "Orange", "Yellow", "Green",
        "Blue", "Violet", "Gray", "White", "Gold", "Silver"
    )),

    TOLERANCE(arrayOf(
    "Gold", "Silver", "Brown", "Red",
    "Green", "Blue", "Violet", "Gray",
    ))
}
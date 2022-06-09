package com.example.resistancecalculator

// TODO: better formatting of the output string


// will take the proper inputs to calculate the resistance of a resistor
fun calcResistance(Band1: String, Band2: String, Multiplier: String, Tolerance: String) : String {
    val band1 = bandHelper(Band1)
    val band2 = bandHelper(Band2)

    val multiplier: String = when (Multiplier) {
        "Black" -> "1"
        "Brown" -> "10"
        "Red" -> "100"
        "Orange" -> "1000"
        "Yellow" -> "10000"
        "Green" -> "100000"
        "Blue" -> "1000000"
        "Violet" -> "10000000"
        "Gray" -> "100000000"
        "White" -> "1000000000"
        "Gold" -> "0.1"
        "Silver" -> "0.01"
        else -> { "1" }
    }

    val tolerance: String = when (Tolerance) {
        "Brown" -> "1"
        "Red" -> "2"
        "Green" -> "0.5"
        "Blue" -> "0.25"
        "Violet" -> "0.1"
        "Gray" -> "0.05"
        "Gold" -> "5"
        "Silver" -> "10"
        else -> { "5" }
    }

    val value: String = ((band1 + band2).toInt() * multiplier.toDouble()).toInt().toString()
    return "$value Ohms $tolerance%"
}


// purely grabs the number from the color (happens twice)
private fun bandHelper(Color: String) : String {
    val color: String = when (Color) {
        "Black" -> "0"
        "Brown" -> "1"
        "Red" -> "2"
        "Orange" -> "3"
        "Yellow" -> "4"
        "Green" -> "5"
        "Blue" -> "6"
        "Violet" -> "7"
        "Gray" -> "8"
        "White" -> "9"
        else -> { "0" }
    }

    return color
}

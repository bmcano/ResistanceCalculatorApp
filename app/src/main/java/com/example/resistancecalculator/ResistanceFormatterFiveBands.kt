package com.example.resistancecalculator

/**
 * Job: will take the string inputs from the ENUM to format the resistance of a resistor
 * with five bands.
 */

fun calcResistance(Band1: String, Band2: String, Band3: String, Multiplier: String, Tolerance: String) : String {
    // will not work properly with any empty strings
    if(Band1 == "" || Band2 == "" || Band3 == "" || Multiplier == "" || Tolerance == "") {
        return "Select Colors"
    }

    val band1 = bandHelper(Band1)
    val band2 = bandHelper(Band2)
    val band3 = bandHelper(Band3)
    val multiplier = multiplierHelper(Multiplier, band1, band2, band3)
    val tolerance = toleranceHelper(Tolerance)

    var resistance = "${multiplier}Ω  $tolerance%"

    // TODO: update this for 3 bands instead of 2
    if(Band1 == "Black" && Band2 == "Black") {
        resistance = "0 Ω  $tolerance%"
    } else if(Band1 == "Black") {
        resistance = when(Multiplier) {
            "Red","Green","Gray","Gold" -> {
                "0${multiplierHelper(Multiplier, "", band2, band3)}Ω  $tolerance%"
            }
            else -> {
                "${multiplierHelper(Multiplier, "", band2, band3)}Ω  $tolerance%"
            }
        }
    }

    return resistance
}

private fun bandHelper(Color: String) : String {
    return when (Color) {
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
        else -> { "" }
    }
}

private fun multiplierHelper(Color: String, band1: String, band2: String, band3: String) : String {
    return when (Color) {
        "Black" -> "$band1$band2$band3 "
        "Brown" -> "${band1}.${band2}${band3} k"
        "Red" -> "${band1}${band2}.${band3} k"
        "Orange" -> "${band1}${band2}${band3} k"
        "Yellow" -> "${band1}.${band2}${band3} M"
        "Green" -> "${band1}${band2}.${band3} M"
        "Blue" -> "${band1}${band2}${band3} M"
        "Violet" -> "${band1}.${band2}${band3} G"
        "Gray" -> "${band1}${band2}.${band3} G"
        "White" -> "${band1}${band2}${band3} G"
        "Gold" -> "${band1}${band2}.${band3} "
        "Silver" -> "${band1}.${band2}${band3} "
        else -> { "$band1$band2$band3 " }
    }
}

private fun toleranceHelper(Color: String) : String {
    return when (Color) {
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
}
package com.example.resistancecalculator

// TODO: better formatting of the output string


// will take the proper inputs to calculate the resistance of a resistor
fun calcResistance(Band1: String, Band2: String, Multiplier: String, Tolerance: String) : String {
    val band1 = bandHelper(Band1)
    val band2 = bandHelper(Band2)
    val multiplier = multiplierHelper(Multiplier, band1, band2)
    val tolerance = toleranceHelper(Tolerance)

    var resistance = "${multiplier}Ω  $tolerance%"

    // fix for when band1 is black
    if(Band1 == "Black" && Band2 == "Black") {
        resistance = "0 Ω  $tolerance%"
    } else if(Band1 == "Black") {
        resistance = when(Multiplier) {
            "Red","Green","Gray","Gold" -> {
                "0${multiplierHelper(Multiplier, "", band2)}Ω  $tolerance%"
            }
            else -> {
                "${multiplierHelper(Multiplier, "", band2)}Ω  $tolerance%"
            }
        }
    }

    return resistance
}


// purely grabs the number from the color (happens twice)
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

private fun multiplierHelper(Color: String, band1: String, band2: String) : String {
    return when (Color) {
        "Black" -> "$band1$band2 "
        "Brown" -> "${band1}${band2}0 "
        "Red" -> "${band1}.${band2} k"
        "Orange" -> "${band1}${band2} k"
        "Yellow" -> "${band1}${band2}0 k"
        "Green" -> "${band1}.${band2} M"
        "Blue" -> "${band1}${band2} M"
        "Violet" -> "${band1}${band2}0 M"
        "Gray" -> "${band1}.${band2} G"
        "White" -> "${band1}${band2} G"
        "Gold" -> "${band1}.${band2} "
        "Silver" -> "0.${band1}${band2} "
        else -> { "$band1$band2 " }
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

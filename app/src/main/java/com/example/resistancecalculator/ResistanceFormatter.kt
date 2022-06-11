package com.example.resistancecalculator

object ResistanceFormatter {

    private const val OMEGA: String = "Ω"
    private const val PLUS_MINUS: String = "±"

    // four band resistors
    fun calcResistance(Band1: String, Band2: String, Multiplier: String, Tolerance: String) : String {
        // will not work properly with any empty strings
        if (Band1 == "" || Band2 == "" || Multiplier == "" || Tolerance == "") {
            return "Select Colors"
        }

        val band1 = numberHelper(Band1)
        val band2 = numberHelper(Band2)
        val multiplier = multiplierHelper(Multiplier, band1, band2)
        val tolerance = toleranceHelper(Tolerance)

        // format the text, with handling the weird conditions
        val resistance: String = if (Band1 == "Black" && Band2 == "Black") {
            "0 $OMEGA $PLUS_MINUS$tolerance%"
        } else if (Band1 == "Black") {
            when(Multiplier) {
                "Red" -> { "${band2}00 $OMEGA $PLUS_MINUS$tolerance%" }
                "Green" -> { "${band2}00 k$OMEGA $PLUS_MINUS$tolerance%" }
                "Gray" -> { "${band2}00 M$OMEGA $PLUS_MINUS$tolerance%" }
                "Silver" -> { "0.0${band2} $OMEGA $PLUS_MINUS$tolerance%" }
                else -> {
                    "${multiplierHelper(Multiplier, "", band2)}$OMEGA $PLUS_MINUS$tolerance%"
                }
            }
        } else {
            "$multiplier$OMEGA $PLUS_MINUS$tolerance%"
        }

        return resistance
    }

    // five or six band resistors
    fun calcResistance(Band1: String, Band2: String, Band3: String, Multiplier: String, Tolerance: String, PPM: String = "") : String {
        // will not work properly with any empty strings
        if (Band1 == "" || Band2 == "" || Band3 == "" || Multiplier == "" || Tolerance == "") {
            return "Select Colors"
        }

        val band1 = numberHelper(Band1)
        val band2 = numberHelper(Band2)
        val band3 = numberHelper(Band3)
        val multiplier = multiplierHelper(Multiplier, band1, band2, band3)
        val tolerance = toleranceHelper(Tolerance)
        val ppm = ppmHelper(PPM)

        // format the text, with handling the weird conditions
        val resistance: String = if (Band1 == "Black" && Band2 == "Black" && Band3 == "Black") {
            "0 $OMEGA $PLUS_MINUS$tolerance%$ppm"
        } else if (Band1 == "Black" && Band2 == "Black") {
            when(Multiplier) {
                "Brown" -> { "${band3}0 $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Red" -> { "${band3}00 $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Yellow" -> { "${band3}0 k$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Green" -> { "${band3}00 k$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Violet" -> { "${band3}0 M$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Gray" -> { "${band3}00 M$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Gold" -> { "0.${band3} $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Silver" -> { "0.0${band3} $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                else -> {
                    "${multiplierHelper(Multiplier, "", "", band3)}$OMEGA $PLUS_MINUS$tolerance%$ppm"
                }
            }
        } else if (Band1 == "Black") {
            when(Multiplier) {
                "Brown" -> { "${band2}${band3}0 $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Yellow" -> { "${band2}${band3}0 k$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Violet" -> { "${band2}${band3}0 M$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Silver" -> { "0.${band2}${band3} $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                else -> {
                    "${multiplierHelper(Multiplier, "", band2, band3)}$OMEGA $PLUS_MINUS$tolerance%$ppm"
                }
            }
        } else {
            "$multiplier$OMEGA $PLUS_MINUS$tolerance%$ppm"
        }

        return resistance
    }

    // start of private helper methods

    // gets the number from its color representation
    private fun numberHelper(Color: String) : String {
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

    // four band resistor
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

    // five or six band resistor
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

    // finds the tolerance
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

    // temperature coefficient - only on six band resistor
    private fun ppmHelper(Color: String) : String {
        return when (Color) {
            "Brown" -> "\n100 ppm"
            "Red" -> "\n50 ppm"
            "Orange" -> "\n15 ppm"
            "Yellow" -> "\n25 ppm"
            "Blue" -> "\n10 ppm"
            "Violet" -> "\n5 ppm"
            else -> { "" }
        }
    }
}
package com.brandoncano.resistancecalculator.util

/**
 * Job: formats the resistance based on the colors selected for the bands
 *
 * @author: Brandon
 */

object ResistanceFormatter {
    private const val OMEGA: String = "Ω"
    private const val PLUS_MINUS: String = "±"
    private const val DEGREE: String = "°"
    private const val EMPTY_STRING = ""

    // four band resistors
    fun calcResistance(NumberBand1: String, NumberBand2: String, Multiplier: String, Tolerance: String) : String {
        // will not work properly with any empty strings
        if (NumberBand1 == EMPTY_STRING || NumberBand2 == EMPTY_STRING || Multiplier == EMPTY_STRING || Tolerance == EMPTY_STRING) {
            return "Select Colors"
        }

        val numberBand1 = numberHelper(NumberBand1)
        val numberBand2 = numberHelper(NumberBand2)
        val multiplier = multiplierHelper(Multiplier, numberBand1, numberBand2)
        val tolerance = toleranceHelper(Tolerance)

        // format the text, with handling the weird conditions
        return if (NumberBand1 == "Black" && NumberBand2 == "Black") {
            "0 $OMEGA $PLUS_MINUS$tolerance%"
        } else if (NumberBand1 == "Black") {
            when(Multiplier) {
                "Red" -> { "${numberBand2}00 $OMEGA $PLUS_MINUS$tolerance%" }
                "Green" -> { "${numberBand2}00 k$OMEGA $PLUS_MINUS$tolerance%" }
                "Gray" -> { "${numberBand2}00 M$OMEGA $PLUS_MINUS$tolerance%" }
                "Silver" -> { "0.0${numberBand2} $OMEGA $PLUS_MINUS$tolerance%" }
                else -> {
                    "${multiplierHelper(Multiplier, EMPTY_STRING, numberBand2)}$OMEGA $PLUS_MINUS$tolerance%"
                }
            }
        } else {
            "$multiplier$OMEGA $PLUS_MINUS$tolerance%"
        }
    }

    // five or six band resistors
    fun calcResistance(NumberBand1: String, NumberBand2: String, NumberBand3: String, Multiplier: String, Tolerance: String, PPM: String = EMPTY_STRING) : String {
        // will not work properly with any empty strings
        if (NumberBand1 == EMPTY_STRING || NumberBand2 == EMPTY_STRING || NumberBand3 == EMPTY_STRING || Multiplier == EMPTY_STRING || Tolerance == EMPTY_STRING) {
            return "Select Colors"
        }

        val numberBand1 = numberHelper(NumberBand1)
        val numberBand2 = numberHelper(NumberBand2)
        val numberBand3 = numberHelper(NumberBand3)
        val multiplier = multiplierHelper(Multiplier, numberBand1, numberBand2, numberBand3)
        val tolerance = toleranceHelper(Tolerance)
        val ppm = ppmHelper(PPM)

        // format the text, with handling the weird conditions
        return if (NumberBand1 == "Black" && NumberBand2 == "Black" && NumberBand3 == "Black") {
            "0 $OMEGA $PLUS_MINUS$tolerance%$ppm"
        } else if (NumberBand1 == "Black" && NumberBand2 == "Black") {
            when(Multiplier) {
                "Brown" -> { "${numberBand3}0 $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Red" -> { "${numberBand3}00 $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Yellow" -> { "${numberBand3}0 k$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Green" -> { "${numberBand3}00 k$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Violet" -> { "${numberBand3}0 M$.OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Gray" -> { "${numberBand3}00 M$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Gold" -> { "0.${numberBand3} $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Silver" -> { "0.0${numberBand3} $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                else -> {
                    "${multiplierHelper(Multiplier, EMPTY_STRING, EMPTY_STRING, numberBand3)}$OMEGA $PLUS_MINUS$tolerance%$ppm"
                }
            }
        } else if (NumberBand1 == "Black") {
            when(Multiplier) {
                "Brown" -> { "${numberBand2}${numberBand3}0 $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Yellow" -> { "${numberBand2}${numberBand3}0 k$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Violet" -> { "${numberBand2}${numberBand3}0 M$OMEGA $PLUS_MINUS$tolerance%$ppm" }
                "Silver" -> { "0.${numberBand2}${numberBand3} $OMEGA $PLUS_MINUS$tolerance%$ppm" }
                else -> {
                    "${multiplierHelper(Multiplier, EMPTY_STRING, numberBand2, numberBand3)}$OMEGA $PLUS_MINUS$tolerance%$ppm"
                }
            }
        } else {
            "$multiplier$OMEGA $PLUS_MINUS$tolerance%$ppm"
        }
    }

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
            else -> { "0" }
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
            "None" -> "20"
            else -> { "20" }
        }
    }

    // temperature coefficient - only on six band resistor
    private fun ppmHelper(Color: String) : String {
        return when (Color) {
            "Black" -> "\n250 ppm/${DEGREE}C"
            "Brown" -> "\n100 ppm/${DEGREE}C"
            "Red" -> "\n50 ppm/${DEGREE}C"
            "Orange" -> "\n15 ppm/${DEGREE}C"
            "Yellow" -> "\n25 ppm/${DEGREE}C"
            "Green" -> "\n20 ppm/${DEGREE}C"
            "Blue" -> "\n10 ppm/${DEGREE}C"
            "Violet" -> "\n5 ppm/${DEGREE}C"
            "Gray" -> "\n1 ppm/${DEGREE}C"
            else -> { EMPTY_STRING }
        }
    }
}

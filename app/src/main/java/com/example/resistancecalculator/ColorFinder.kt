package com.example.resistancecalculator

object ColorFinder {
    private const val OMEGA: String = "Ω"
    private const val PLUS_MINUS: String = "±"
    private const val DEGREE: String = "°"

    // finds the image representation
    fun imageColor(color: String): Int {
        return when(color) {
            "Black" -> R.drawable.black32
            "Blue" -> R.drawable.blue32
            "Brown" -> R.drawable.brown32
            "Gold" -> R.drawable.gold32
            "Gray" -> R.drawable.gray32
            "Green" -> R.drawable.green32
            "Orange" -> R.drawable.orange32
            "Red" -> R.drawable.red32
            "Silver" -> R.drawable.silver32
            "Violet" -> R.drawable.violet32
            "White" -> R.drawable.white32
            "Yellow" -> R.drawable.yellow32
            else -> { R.drawable.blank32 }
        }
    }

    // finds the color based on the selection
    fun bandColor(color: String = "else") : Int {
        return when(color) {
            "Red" -> R.color.red32
            "Orange" -> R.color.orange32
            "Yellow" -> R.color.yellow32
            "Gold" -> R.color.gold32
            "Green" -> R.color.green32
            "Blue" -> R.color.blue32
            "Violet" -> R.color.violet32
            "White" -> R.color.white32
            "Silver" -> R.color.silver32
            "Gray" -> R.color.gray
            "Black" -> R.color.black32
            "Brown" -> R.color.brown32
            else -> { R.color.resistor_blank }
        }
    }

    // find image based on tolerance value
    fun toleranceImage(color: String = "None") : Int {
        return when(color) {
            "${PLUS_MINUS}1%" -> R.drawable.brown32
            "${PLUS_MINUS}2%" -> R.drawable.red32
            "${PLUS_MINUS}0.5%" -> R.drawable.green32
            "${PLUS_MINUS}0.25%" -> R.drawable.blue32
            "${PLUS_MINUS}0.1%" -> R.drawable.violet32
            "${PLUS_MINUS}0.05%" -> R.drawable.gray32
            "${PLUS_MINUS}5%" -> R.drawable.gold32
            "${PLUS_MINUS}10%" -> R.drawable.silver32
            "${PLUS_MINUS}20%" -> R.drawable.blank32
            else ->{ R.drawable.blank32 }
        }
    }

    // find color based on tolerance value
    fun toleranceColor(color: String = "None") : Int {
        return when(color) {
            "${PLUS_MINUS}1%" -> R.color.brown32
            "${PLUS_MINUS}2%" -> R.color.red32
            "${PLUS_MINUS}0.5%" -> R.color.green32
            "${PLUS_MINUS}0.25%" -> R.color.blue32
            "${PLUS_MINUS}0.1%" -> R.color.violet32
            "${PLUS_MINUS}0.05%" -> R.color.gray32
            "${PLUS_MINUS}5%" -> R.color.gold32
            "${PLUS_MINUS}10%" -> R.color.silver32
            "${PLUS_MINUS}20%" -> R.color.resistor_blank
            else ->{ R.color.resistor_blank }
        }
    }

    // find image based on ppm value
    fun ppmImage(color: String = "None") : Int {
        return when(color) {
            "250 ppm/${DEGREE}C" -> R.drawable.black32
            "100 ppm/${DEGREE}C" -> R.drawable.brown32
            "50 ppm/${DEGREE}C" -> R.drawable.red32
            "15 ppm/${DEGREE}C" -> R.drawable.orange32
            "25 ppm/${DEGREE}C" -> R.drawable.yellow32
            "20 ppm/${DEGREE}C" -> R.drawable.green32
            "10 ppm/${DEGREE}C" -> R.drawable.blue32
            "5 ppm/${DEGREE}C" -> R.drawable.violet32
            "1 ppm/${DEGREE}C" -> R.drawable.gray32
            else ->{ R.drawable.blank32 }
        }
    }

    // find color based on ppm value
    fun ppmColor(color: String = "None") : Int {
        return when(color) {
            "250 ppm/${DEGREE}C" -> R.color.black32
            "100 ppm/${DEGREE}C" -> R.color.brown32
            "50 ppm/${DEGREE}C" -> R.color.red32
            "15 ppm/${DEGREE}C" -> R.color.orange32
            "25 ppm/${DEGREE}C" -> R.color.yellow32
            "20 ppm/${DEGREE}C" -> R.color.green32
            "10 ppm/${DEGREE}C" -> R.color.blue32
            "5 ppm/${DEGREE}C" -> R.color.violet32
            "1 ppm/${DEGREE}C" -> R.color.gray32
            else ->{ R.color.resistor_blank }
        }
    }

    // picks a random color
    fun randomColor() : Int {
        return when((1..12).random()) {
            1 -> R.color.red32
            2 -> R.color.orange32
            3 -> R.color.yellow32
            4 -> R.color.gold32
            5 -> R.color.green32
            6 -> R.color.blue32
            7 -> R.color.violet32
            8 -> R.color.white32
            9 -> R.color.silver32
            10 -> R.color.gray
            11 -> R.color.black32
            12 -> R.color.brown32
            else -> { R.color.black32 }
        }
    }
}

package com.brandoncano.resistancecalculator.util

object MultiplierFromDigit {

    fun execute(digit: Char): Double {
        return when (digit) {
            'Z' -> 0.001
            'R', 'Y' -> 0.01
            'S', 'X' -> 0.1
            '0', 'A' -> 1.0
            '1', 'B', 'H' -> 10.0
            '2', 'C' -> 100.0
            '3', 'D' -> 1000.0
            '4', 'E' -> 10000.0
            '5', 'F' -> 100000.0
            '6' -> 1000000.0
            '7' -> 10000000.0
            '8' -> 100000000.0
            '9' -> 1000000000.0
            else -> 1.0
        }
    }
}

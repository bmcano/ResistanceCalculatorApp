package com.brandoncano.resistancecalculator.util

import org.junit.Assert.assertEquals
import org.junit.Test

class MultiplierFromDigitTest {

    @Test
    fun `multiplier value from number`() {
        assertEquals(1.0, MultiplierFromDigit.execute('0'), 0.0)
        assertEquals(10.0, MultiplierFromDigit.execute('1'), 0.0)
        assertEquals(100.0, MultiplierFromDigit.execute('2'), 0.0)
        assertEquals(1000.0, MultiplierFromDigit.execute('3'), 0.0)
        assertEquals(10000.0, MultiplierFromDigit.execute('4'), 0.0)
        assertEquals(100000.0, MultiplierFromDigit.execute('5'), 0.0)
        assertEquals(1000000.0, MultiplierFromDigit.execute('6'), 0.0)
        assertEquals(10000000.0, MultiplierFromDigit.execute('7'), 0.0)
        assertEquals(100000000.0, MultiplierFromDigit.execute('8'), 0.0)
        assertEquals(1000000000.0, MultiplierFromDigit.execute('9'), 0.0)
    }

    @Test
    fun `multiplier value from letter`() {
        assertEquals(0.001, MultiplierFromDigit.execute('Z'), 0.0)
        assertEquals(0.01, MultiplierFromDigit.execute('Y'), 0.0)
        assertEquals(0.01, MultiplierFromDigit.execute('R'), 0.0)
        assertEquals(0.1, MultiplierFromDigit.execute('X'), 0.0)
        assertEquals(0.1, MultiplierFromDigit.execute('S'), 0.0)
        assertEquals(1.0, MultiplierFromDigit.execute('A'), 0.0)
        assertEquals(10.0, MultiplierFromDigit.execute('B'), 0.0)
        assertEquals(10.0, MultiplierFromDigit.execute('H'), 0.0)
        assertEquals(100.0, MultiplierFromDigit.execute('C'), 0.0)
        assertEquals(1000.0, MultiplierFromDigit.execute('D'), 0.0)
        assertEquals(10000.0, MultiplierFromDigit.execute('E'), 0.0)
        assertEquals(100000.0, MultiplierFromDigit.execute('F'), 0.0)
    }

    @Test
    fun `not valid letters`() {
        assertEquals(1.0, MultiplierFromDigit.execute('J'), 0.0)
        assertEquals(1.0, MultiplierFromDigit.execute('.'), 0.0)
        assertEquals(1.0, MultiplierFromDigit.execute('K'), 0.0)
    }
}
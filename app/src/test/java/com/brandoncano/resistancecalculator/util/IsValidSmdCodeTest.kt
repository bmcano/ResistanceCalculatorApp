package com.brandoncano.resistancecalculator.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IsValidSmdCodeTest {

    @Test
    fun `valid 3 digit EIA regex test`() {
        assertTrue(IsValidSmdCode.execute("063", 3))
        assertTrue(IsValidSmdCode.execute("174", 3))
        assertTrue(IsValidSmdCode.execute("285", 3))
        assertTrue(IsValidSmdCode.execute("396", 3))
        assertTrue(IsValidSmdCode.execute("407", 3))
        assertTrue(IsValidSmdCode.execute("518", 3))
        assertTrue(IsValidSmdCode.execute("629", 3))
        assertTrue(IsValidSmdCode.execute("730", 3))
        assertTrue(IsValidSmdCode.execute("841", 3))
        assertTrue(IsValidSmdCode.execute("952", 3))

        assertTrue(IsValidSmdCode.execute("0R4", 3))
        assertTrue(IsValidSmdCode.execute("1R5", 3))
        assertTrue(IsValidSmdCode.execute("2R6", 3))
        assertTrue(IsValidSmdCode.execute("3R7", 3))
    }

    @Test
    fun `invalid 3 digit EIA regex test`() {
        assertFalse(IsValidSmdCode.execute("0RR", 3))
        assertFalse(IsValidSmdCode.execute("1R", 3))
        assertFalse(IsValidSmdCode.execute("2R64", 3))
        assertFalse(IsValidSmdCode.execute("", 3))
        assertFalse(IsValidSmdCode.execute("random_string", 3))
    }

    @Test
    fun `valid 4 digit EIA regex test`() {
        assertTrue(IsValidSmdCode.execute("0632", 4))
        assertTrue(IsValidSmdCode.execute("1743", 4))
        assertTrue(IsValidSmdCode.execute("2854", 4))
        assertTrue(IsValidSmdCode.execute("3965", 4))
        assertTrue(IsValidSmdCode.execute("4076", 4))
        assertTrue(IsValidSmdCode.execute("5187", 4))
        assertTrue(IsValidSmdCode.execute("6298", 4))
        assertTrue(IsValidSmdCode.execute("7309", 4))
        assertTrue(IsValidSmdCode.execute("8410", 4))
        assertTrue(IsValidSmdCode.execute("9521", 4))

        assertTrue(IsValidSmdCode.execute("0R34", 4))
        assertTrue(IsValidSmdCode.execute("12R5", 4))
        assertTrue(IsValidSmdCode.execute("27R6", 4))
        assertTrue(IsValidSmdCode.execute("3R77", 4))
    }

    @Test
    fun `invalid 4 digit EIA regex test`() {
        assertFalse(IsValidSmdCode.execute("1RR3", 4))
        assertFalse(IsValidSmdCode.execute("1R", 4))
        assertFalse(IsValidSmdCode.execute("2RRR", 4))
        assertFalse(IsValidSmdCode.execute("", 4))
        assertFalse(IsValidSmdCode.execute("random_string", 4))
    }

    @Test
    fun `EIA-96 regex test`() {

    }
}
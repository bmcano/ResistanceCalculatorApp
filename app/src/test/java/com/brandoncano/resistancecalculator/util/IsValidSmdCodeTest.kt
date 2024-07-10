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
    fun `4 digit EIA regex test`() {

    }

    @Test
    fun `EIA-96 regex test`() {

    }
}
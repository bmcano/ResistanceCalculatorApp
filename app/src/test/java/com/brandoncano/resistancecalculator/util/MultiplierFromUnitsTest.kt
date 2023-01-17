package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.OHMS
import org.junit.Assert.assertEquals
import org.junit.Test

class MultiplierFromUnitsTest {

    @Test
    fun `test units for value`() {
        assertEquals(1, MultiplierFromUnits.execute(OHMS))
        assertEquals(1000, MultiplierFromUnits.execute("k$OHMS"))
        assertEquals(1000000, MultiplierFromUnits.execute("M$OHMS"))
        assertEquals(1000000000, MultiplierFromUnits.execute("G$OHMS"))
    }

    @Test
    fun `else clause testing`() {
        assertEquals(1, MultiplierFromUnits.execute("m$OHMS"))
        assertEquals(1, MultiplierFromUnits.execute("units"))
        assertEquals(1, MultiplierFromUnits.execute("some string"))
    }
}
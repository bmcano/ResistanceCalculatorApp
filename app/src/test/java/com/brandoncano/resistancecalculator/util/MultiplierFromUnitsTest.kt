package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S
import org.junit.Assert.assertEquals
import org.junit.Test

class MultiplierFromUnitsTest {

    @Test
    fun `test units for value`() {
        assertEquals(1, MultiplierFromUnits.execute(S.OHMS))
        assertEquals(1000, MultiplierFromUnits.execute(S.KOHMS))
        assertEquals(1000000, MultiplierFromUnits.execute(S.MOHMS))
        assertEquals(1000000000, MultiplierFromUnits.execute(S.GOHMS))
    }

    @Test
    fun `else clause testing`() {
        assertEquals(1, MultiplierFromUnits.execute("m${S.OHMS}"))
        assertEquals(1, MultiplierFromUnits.execute("units"))
        assertEquals(1, MultiplierFromUnits.execute("some string"))
    }
}

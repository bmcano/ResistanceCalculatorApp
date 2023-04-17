package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S
import org.junit.Assert.assertEquals
import org.junit.Test

class MultiplierFromUnitsTest {

    @Test
    fun `test units for value`() {
        assertEquals(1, MultiplierFromUnits.execute(S.Ohms))
        assertEquals(1000, MultiplierFromUnits.execute(S.kOhms))
        assertEquals(1000000, MultiplierFromUnits.execute(S.MOhms))
        assertEquals(1000000000, MultiplierFromUnits.execute(S.GOhms))
    }

    @Test
    fun `else clause testing`() {
        assertEquals(1, MultiplierFromUnits.execute("m${S.Ohms}"))
        assertEquals(1, MultiplierFromUnits.execute("units"))
        assertEquals(1, MultiplierFromUnits.execute("some string"))
    }
}

package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S
import org.junit.Assert.assertEquals
import org.junit.Test

class UnitsFromMultiplierTest {

    @Test
    fun `values within range for each unit`() {
        assertEquals(S.Ohms, UnitsFromMultiplier.execute(1.0))
        assertEquals(S.kOhms, UnitsFromMultiplier.execute(1000.0))
        assertEquals(S.MOhms, UnitsFromMultiplier.execute(1000000.0))
        assertEquals(S.GOhms, UnitsFromMultiplier.execute(1000000000.0))
    }

    @Test
    fun `else clause testing`() {
        assertEquals(S.Ohms, UnitsFromMultiplier.execute(12.01))
        assertEquals(S.Ohms, UnitsFromMultiplier.execute(-12.4))
        assertEquals(S.Ohms, UnitsFromMultiplier.execute(-565652.156))
    }
}
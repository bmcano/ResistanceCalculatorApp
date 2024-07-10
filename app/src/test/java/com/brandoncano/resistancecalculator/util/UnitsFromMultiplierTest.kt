package com.brandoncano.resistancecalculator.util

import com.brandoncano.resistancecalculator.constants.Symbols as S
import org.junit.Assert.assertEquals
import org.junit.Test

class UnitsFromMultiplierTest {

    @Test
    fun `values within range for each unit`() {
        assertEquals(S.OHMS, UnitsFromMultiplier.execute(1.0))
        assertEquals(S.KOHMS, UnitsFromMultiplier.execute(1000.0))
        assertEquals(S.MOHMS, UnitsFromMultiplier.execute(1000000.0))
        assertEquals(S.GOHMS, UnitsFromMultiplier.execute(1000000000.0))
    }

    @Test
    fun `else clause testing`() {
        assertEquals(S.OHMS, UnitsFromMultiplier.execute(12.01))
        assertEquals(S.OHMS, UnitsFromMultiplier.execute(-12.4))
        assertEquals(S.OHMS, UnitsFromMultiplier.execute(-565652.156))
    }
}
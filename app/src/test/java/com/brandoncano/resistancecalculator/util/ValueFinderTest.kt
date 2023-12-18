package com.brandoncano.resistancecalculator.util

import org.junit.Assert.assertEquals
import org.junit.Test
import com.brandoncano.resistancecalculator.constants.Colors as C
import com.brandoncano.resistancecalculator.constants.Symbols as S

class ValueFinderTest {

    @Test
    fun `check all multiplier values`() {
        assertEquals(1.0, ValueFinder.getMultiplier(C.BLACK), 1.0)
        assertEquals(10.0, ValueFinder.getMultiplier(C.BROWN), 1.0)
        assertEquals(100.0, ValueFinder.getMultiplier(C.RED), 1.0)
        assertEquals(1000.0, ValueFinder.getMultiplier(C.ORANGE), 1.0)
        assertEquals(10000.0, ValueFinder.getMultiplier(C.YELLOW), 1.0)
        assertEquals(100000.0, ValueFinder.getMultiplier(C.GREEN), 1.0)
        assertEquals(1000000.0, ValueFinder.getMultiplier(C.BLUE), 1.0)
        assertEquals(10000000.0, ValueFinder.getMultiplier(C.VIOLET), 1.0)
        assertEquals(100000000.0, ValueFinder.getMultiplier(C.GRAY), 1.0)
        assertEquals(1000000000.0, ValueFinder.getMultiplier(C.WHITE), 1.0)
        assertEquals(0.1, ValueFinder.getMultiplier(C.GOLD), 1.0)
        assertEquals(0.01, ValueFinder.getMultiplier(C.SILVER), 1.0)
        // else
        assertEquals(1.0, ValueFinder.getMultiplier("SOME STRING"), 1.0)
    }

    @Test
    fun `check all sigfig values`() {
        assertEquals("0", ValueFinder.getSigFig(C.BLACK))
        assertEquals("1", ValueFinder.getSigFig(C.BROWN))
        assertEquals("2", ValueFinder.getSigFig(C.RED))
        assertEquals("3", ValueFinder.getSigFig(C.ORANGE))
        assertEquals("4", ValueFinder.getSigFig(C.YELLOW))
        assertEquals("5", ValueFinder.getSigFig(C.GREEN))
        assertEquals("6", ValueFinder.getSigFig(C.BLUE))
        assertEquals("7", ValueFinder.getSigFig(C.VIOLET))
        assertEquals("8", ValueFinder.getSigFig(C.GRAY))
        assertEquals("9", ValueFinder.getSigFig(C.WHITE))
        // colors that *should not* be reachable
        assertEquals("", ValueFinder.getSigFig(C.GOLD))
        assertEquals("", ValueFinder.getSigFig(C.SILVER))
        // else
        assertEquals("", ValueFinder.getSigFig(C.BLANK))
        assertEquals("", ValueFinder.getSigFig("SOME STRING"))
    }

    @Test
    fun `check all tolerance values`() {
        assertEquals("${S.PM}1%", ValueFinder.getTolerance(C.BROWN))
        assertEquals("${S.PM}2%", ValueFinder.getTolerance(C.RED))
        assertEquals("${S.PM}0.5%", ValueFinder.getTolerance(C.GREEN))
        assertEquals("${S.PM}0.25%", ValueFinder.getTolerance(C.BLUE))
        assertEquals("${S.PM}0.1%", ValueFinder.getTolerance(C.VIOLET))
        assertEquals("${S.PM}0.05%", ValueFinder.getTolerance(C.GRAY))
        assertEquals("${S.PM}5%", ValueFinder.getTolerance(C.GOLD))
        assertEquals("${S.PM}10%", ValueFinder.getTolerance(C.SILVER))
        assertEquals("${S.PM}20%", ValueFinder.getTolerance(C.BLANK))
        // colors that *should not* be reachable
        assertEquals("", ValueFinder.getTolerance(C.BLACK))
        assertEquals("", ValueFinder.getTolerance(C.ORANGE))
        assertEquals("", ValueFinder.getTolerance(C.YELLOW))
        assertEquals("", ValueFinder.getTolerance(C.WHITE))
        // else
        assertEquals("", ValueFinder.getTolerance("SOME STRING"))
    }

    @Test
    fun `check all ppm values`() {
        assertEquals("250 ${S.PPM}", ValueFinder.getPPM(C.BLACK, 6))
        assertEquals("100 ${S.PPM}", ValueFinder.getPPM(C.BROWN, 6))
        assertEquals("50 ${S.PPM}", ValueFinder.getPPM(C.RED, 6))
        assertEquals("15 ${S.PPM}", ValueFinder.getPPM(C.ORANGE, 6))
        assertEquals("25 ${S.PPM}", ValueFinder.getPPM(C.YELLOW, 6))
        assertEquals("20 ${S.PPM}", ValueFinder.getPPM(C.GREEN, 6))
        assertEquals("10 ${S.PPM}", ValueFinder.getPPM(C.BLUE, 6))
        assertEquals("5 ${S.PPM}", ValueFinder.getPPM(C.VIOLET, 6))
        assertEquals("1 ${S.PPM}", ValueFinder.getPPM(C.GRAY, 6))
        // colors that *should not* be reachable
        assertEquals("", ValueFinder.getPPM(C.WHITE, 6))
        assertEquals("", ValueFinder.getPPM(C.GOLD, 6))
        assertEquals("", ValueFinder.getPPM(C.SILVER, 6))
        // else
        assertEquals("", ValueFinder.getPPM(C.BLANK, 6))
        assertEquals("", ValueFinder.getPPM("SOME STRING", 6))
        // 4 or 5 bands
        assertEquals("", ValueFinder.getPPM(C.BLACK, 4))
        assertEquals("", ValueFinder.getPPM(C.BROWN, 4))
        assertEquals("", ValueFinder.getPPM(C.RED, 5))
        assertEquals("", ValueFinder.getPPM(C.ORANGE, 5))
    }
}
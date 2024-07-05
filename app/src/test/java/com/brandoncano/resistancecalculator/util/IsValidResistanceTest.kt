package com.brandoncano.resistancecalculator.util

import android.content.Context
import com.brandoncano.resistancecalculator.components.SharedPreferences
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import com.brandoncano.resistancecalculator.constants.Symbols as S

/**
 * Notes:
 *   EditText already limits this to decimal and whole numbers and a max of 5 characters
 */
class IsValidResistanceTest {

    private val context: Context = mockk<Context>()

    @Before
    fun setup() {
        every { SharedPreferences.NAVBAR_SELECTION_VTC.saveData(context, "5") } answers { }
        every { SharedPreferences.NAVBAR_SELECTION_VTC.saveData(context, "6") } answers { }
    }

    @Test
    fun invalidInputs() {
        val resistor = ResistorVtc()
        // four band
        resistor.units = S.OHMS
        assertFalse(IsValidResistance.execute(resistor, "InValid"))

        assertFalse(IsValidResistance.execute(resistor, "133"))
        assertFalse(IsValidResistance.execute(resistor, "011"))
        assertFalse(IsValidResistance.execute(resistor, "001"))

        assertFalse(IsValidResistance.execute(resistor, ".1"))
        assertFalse(IsValidResistance.execute(resistor, ".12"))
        assertFalse(IsValidResistance.execute(resistor, ".123"))

        assertFalse(IsValidResistance.execute(resistor, "0.0"))
        assertFalse(IsValidResistance.execute(resistor, "0.006"))
        assertFalse(IsValidResistance.execute(resistor, "0.056"))
        assertFalse(IsValidResistance.execute(resistor, "1.02"))
        assertFalse(IsValidResistance.execute(resistor, "1.023"))

        resistor.units = S.GOHMS
        assertFalse(IsValidResistance.execute(resistor, "130"))

        // five/six band
        resistor.units = S.OHMS
        resistor.navBarSelection = 5

        assertFalse(IsValidResistance.execute(resistor, "1234"))
        assertFalse(IsValidResistance.execute(resistor, "0123"))
        assertFalse(IsValidResistance.execute(resistor, "0012"))
        assertFalse(IsValidResistance.execute(resistor, "012"))
        assertFalse(IsValidResistance.execute(resistor, "001"))

        assertFalse(IsValidResistance.execute(resistor, ".12"))
        assertFalse(IsValidResistance.execute(resistor, ".01"))

        assertFalse(IsValidResistance.execute(resistor, "0.123"))
        assertFalse(IsValidResistance.execute(resistor, "0.0123"))
        assertFalse(IsValidResistance.execute(resistor, "1.023"))
        assertFalse(IsValidResistance.execute(resistor, "1.203"))
        assertFalse(IsValidResistance.execute(resistor, "0.001"))
        resistor.units = S.GOHMS
        assertFalse(IsValidResistance.execute(resistor, "1230"))
        assertFalse(IsValidResistance.execute(resistor, "0.001"))
    }

    @Test
    fun validInputs() {
        val resistor = ResistorVtc()
        // four band
        resistor.units = S.OHMS
        assertTrue(IsValidResistance.execute(resistor, "0"))
        assertTrue(IsValidResistance.execute(resistor, "12"))
        assertTrue(IsValidResistance.execute(resistor, "10"))
        assertTrue(IsValidResistance.execute(resistor, "5"))
        assertTrue(IsValidResistance.execute(resistor, "12.0"))
        assertTrue(IsValidResistance.execute(resistor, "0.67"))
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "0.05"))

        resistor.units = S.GOHMS
        assertTrue(IsValidResistance.execute(resistor, "13.0"))
        assertTrue(IsValidResistance.execute(resistor, "0.67"))
        assertTrue(IsValidResistance.execute(resistor, "0.6"))
        assertTrue(IsValidResistance.execute(resistor, "6.7"))

        // five band
        resistor.units = S.OHMS
        resistor.navBarSelection = 5
        assertTrue(IsValidResistance.execute(resistor, "0"))
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "6.23"))
        assertTrue(IsValidResistance.execute(resistor, "63.2"))
        assertTrue(IsValidResistance.execute(resistor, "663.0"))
        assertTrue(IsValidResistance.execute(resistor, "0.01"))

        resistor.units = S.GOHMS
        resistor.navBarSelection = 5
        assertTrue(IsValidResistance.execute(resistor, "6.7"))
        assertTrue(IsValidResistance.execute(resistor, "6.23"))
        assertTrue(IsValidResistance.execute(resistor, "63.2"))
        assertTrue(IsValidResistance.execute(resistor, "663.0"))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
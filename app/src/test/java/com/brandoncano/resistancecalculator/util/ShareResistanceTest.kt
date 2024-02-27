package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import com.brandoncano.resistancecalculator.resistor.Resistor
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Test

class ShareResistanceTest {

    private val context: Context = mockk<Context>()
    private val resistor: Resistor = mockk<Resistor>()
    private val intent: Intent = mockk<Intent>()

    @Test
    fun `verify that the intent is created and activity starts`() {
        every { intent.type = "plain/text" } just Runs
        every { resistor.resistance } returns "123"
        every { context.startActivity(any()) } returns Unit

        ShareResistance.execute(context, resistor)
        verify { context.startActivity(any()) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class OpenLinkTest {

    private val context: Context = mockk<Context>()
    private val uri: Uri = mockk<Uri>()

    @Before
    fun setup() {
        mockkStatic(Uri::class)
        mockkConstructor(Intent::class)
    }

    @Test
    fun `verify openCapacitorApp`() {
        every { anyConstructed<Intent>().action = Intent.ACTION_VIEW } just Runs
        every { Uri.parse("https://play.google.com/store/apps/details?id=com.brandoncano.capacitorcalculator") } returns uri
        every { context.startActivity(any()) } returns Unit

        OpenLink.openCapacitorApp(context)
        verify { context.startActivity(any()) }
    }

    @Test
    fun `verify openResistorApp`() {
        every { anyConstructed<Intent>().action = Intent.ACTION_VIEW } just Runs
        every { Uri.parse("https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator") } returns uri
        every { context.startActivity(any()) } returns Unit

        OpenLink.openResistorApp(context)
        verify { context.startActivity(any()) }
    }

    @Test
    fun `verify openIECWebpage`() {
        every { anyConstructed<Intent>().action = Intent.ACTION_VIEW } just Runs
        every { Uri.parse("https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-color-code/#") } returns uri
        every { context.startActivity(any()) } returns Unit

        OpenLink.openIECWebpage(context)
        verify { context.startActivity(any()) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
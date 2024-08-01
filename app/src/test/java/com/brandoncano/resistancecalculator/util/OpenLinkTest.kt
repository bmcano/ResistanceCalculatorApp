package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.brandoncano.resistancecalculator.util.external.OpenLink
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
        every { context.startActivity(any(), null) } returns Unit

        OpenLink.openCapacitorApp(context)
        verify { context.startActivity(any(), null) }
    }

    @Test
    fun `verify openResistorApp`() {
        every { anyConstructed<Intent>().action = Intent.ACTION_VIEW } just Runs
        every { Uri.parse("https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator") } returns uri
        every { context.startActivity(any(), null) } returns Unit

        OpenLink.openResistorApp(context)
        verify { context.startActivity(any(), null) }
    }

    @Test
    fun `verify openColorIECWebpage`() {
        every { anyConstructed<Intent>().action = Intent.ACTION_VIEW } just Runs
        every { Uri.parse("https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-color-code/") } returns uri
        every { context.startActivity(any(), null) } returns Unit

        OpenLink.openColorIECWebpage(context)
        verify { context.startActivity(any(), null) }
    }

    @Test
    fun `verify openSmdIECWebpage`() {
        every { anyConstructed<Intent>().action = Intent.ACTION_VIEW } just Runs
        every { Uri.parse("https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-smd-code/") } returns uri
        every { context.startActivity(any(), null) } returns Unit

        OpenLink.openSmdIECWebpage(context)
        verify { context.startActivity(any(), null) }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}
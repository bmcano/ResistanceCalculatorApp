package com.brandoncano.resistancecalculator.util.external

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.brandoncano.resistancecalculator.constants.Links

object OpenLink {

    fun openCapacitorApp(context: Context) {
        open(context, Links.CAPACITOR_PLAYSTORE)
    }

    fun openResistorApp(context: Context) {
        open(context, Links.RESISTOR_PLAYSTORE)
    }

    fun openColorIECWebpage(context: Context) {
        open(context, Links.COLOR_IEC)
    }

    fun openSmdIECWebpage(context: Context) {
        open(context, Links.SMD_IEC)
    }

    fun openPrivacyPolicy(context: Context) {
        open(context, Links.PRIVACY_POLICY)
    }

    private fun open(context: Context, link: String) {
        try {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(link))
        } catch (e: Exception) {
            e.printStackTrace()
            ErrorDialog.build(context, "A problem occurred while trying to open the link.")
        }
    }
}

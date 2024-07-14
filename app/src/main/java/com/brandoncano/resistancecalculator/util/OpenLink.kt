package com.brandoncano.resistancecalculator.util

import android.app.AlertDialog
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

    private fun open(context: Context, link: String) {
        try {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(link))
        } catch (e: Exception) {
            e.printStackTrace()
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
                .setMessage("A problem occurred while trying to open the link.")
                .setPositiveButton("OK", null)
                .show()
        }
    }
}
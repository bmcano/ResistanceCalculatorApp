package com.brandoncano.resistancecalculator.util

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object OpenLink {

    private const val capacitorPlayStoreLink = "https://play.google.com/store/apps/details?id=com.brandoncano.capacitorcalculator"
    private const val resistorPlayStoreLink = "https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator"
    private const val iecLink = "https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-color-code/#"

    fun openCapacitorApp(context: Context) {
        open(context, capacitorPlayStoreLink)
    }

    fun openResistorApp(context: Context) {
        open(context, resistorPlayStoreLink)
    }

    fun openIECWebpage(context: Context) {
        open(context, iecLink)
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
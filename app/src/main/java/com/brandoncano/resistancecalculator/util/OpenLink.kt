package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

object OpenLink {

    private const val capacitorPlayStoreLink = "https://play.google.com/store/apps/details?id=com.brandoncano.capacitorcalculator"
    private const val resistorPlayStoreLink = "https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator"
    private const val iecLink = "https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-color-code/#"

    fun openCapacitorApp(context: Context) {
        val uri = Uri.parse(capacitorPlayStoreLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(context, intent, null)
    }

    fun openResistorApp(context: Context) {
        val uri = Uri.parse(resistorPlayStoreLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(context, intent, null)
    }

    fun openIECWebpage(context: Context) {
        val uri = Uri.parse(iecLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(context, intent, null)
    }
}
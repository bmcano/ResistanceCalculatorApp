package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import com.brandoncano.resistancecalculator.resistor.Resistor

/**
 * Job: This will take the information of the resistor and make it shareable text.
 */
object ShareResistance {

    fun execute(context: Context, resistor: Resistor) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_TEXT, "${resistor.resistance}\n$resistor")
        context.startActivity(Intent.createChooser(intent, ""))
    }
}

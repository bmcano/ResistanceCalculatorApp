package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.brandoncano.resistancecalculator.resistor.Resistor

/**
 * Job: This will take teh string from the TextView so it can be shared/copied.
 */
object ShareResistance {

    fun execute(context: Context, resistor: Resistor) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        val text = "${resistor.resistance}\n" + resistor.toString()
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(context, Intent.createChooser(intent, ""), null)
    }
}

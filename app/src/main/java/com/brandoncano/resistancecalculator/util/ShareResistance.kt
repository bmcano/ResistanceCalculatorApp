package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.brandoncano.resistancecalculator.resistor.Resistor

/**
 * Job: This will take teh string from the TextView so it can be shared/copied.
 */
object ShareResistance {

    fun execute(context: Context, resistor: Resistor, resistance: TextView, isVtC: Boolean = false) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        val text = "${resistance.text}\n" + resistor.toColorBandString(isVtC)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(context, Intent.createChooser(intent, ""), null)
    }
}

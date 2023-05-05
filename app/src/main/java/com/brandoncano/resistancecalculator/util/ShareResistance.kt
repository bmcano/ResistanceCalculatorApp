package com.brandoncano.resistancecalculator.util

import android.content.Intent
import android.widget.TextView
import com.brandoncano.resistancecalculator.resistor.Resistor

/**
 * Job: This will take teh string from the TextView so it can be shared/copied.
 */
object ShareResistance {

    fun execute(resistor: Resistor, resistance: TextView, isVtC: Boolean = false): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        val text = "${resistance.text}\n" + resistor.toColorBandString(isVtC)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }
}

package com.brandoncano.resistancecalculator.util

import android.content.Intent
import android.widget.TextView
import com.brandoncano.resistancecalculator.components.Resistor

/**
 * Job: This will get the String built in order to share or copy it.
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

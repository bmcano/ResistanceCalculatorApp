package com.brandoncano.resistancecalculator.util

import android.content.Intent
import android.widget.TextView
import com.brandoncano.resistancecalculator.Resistor

/**
 * @author: Brandon
 *
 * Job: holds all the logic for the menu items, to keep activities cleaner and more condense
 */
object ShareResistance {

    // generates to text to copy/share for CTV
    fun shareCTV(resistor: Resistor, resistance: TextView): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        val text = "${resistance.text}\n" + resistor.toColorBandString()
        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }

    // generates to text to copy/share for VTC
    fun shareVTC(resistor: Resistor, resistance: TextView): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        val text = "${resistance.text}\n" + resistor.toColorBandString(isVtC = true)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }
}

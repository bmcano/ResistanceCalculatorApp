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

    private const val EMPTY_STRING = ""

    // generates to text to copy/share for CTV
    fun shareCTV(resistance: TextView, resistor: Resistor): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        val text = "${resistance.text}\n" + resistor.toString()
        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }

    // generates to text to copy/share for VTC
    fun shareItemVTC(resistor: Resistor, screenText: TextView): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        var text = EMPTY_STRING

        if (!resistor.isEmpty()) {
            val nb1 = resistor.sigFigBandOne
            val nb2 = resistor.sigFigBandTwo
            val nb3 = resistor.sigFigBandThree
            val multi = resistor.multiplierBand
            val tol = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(resistor.toleranceValue))
            val ppm = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(resistor.ppmValue))

            text = when (resistor.getNumberOfBands()) {
                4 -> "${screenText.text}\n[ $nb1, $nb2, $multi, $tol ]"
                5 -> "${screenText.text}\n[ $nb1, $nb2, $nb3, $multi, $tol ]"
                6 -> "${screenText.text}\n[ $nb1, $nb2, $nb3, $multi, $tol, $ppm ]"
                else -> EMPTY_STRING
            }
        }

        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }
}

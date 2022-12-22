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
    fun shareItemVTC(
        imageSelection: Int, shareColors: Array<Int>, screenText: TextView,
        toleranceBand: String, ppmBand: String
    ): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        var text = EMPTY_STRING

        if (shareColors.isNotEmpty()) {
            val nb1 = ColorFinder.idToColorText(shareColors[0])
            val nb2 = ColorFinder.idToColorText(shareColors[1])
            val nb3 = ColorFinder.idToColorText(shareColors[2])
            val multi = ColorFinder.idToColorText(shareColors[3])
            val tol = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(toleranceBand))
            val ppm = ColorFinder.idToColorText(ColorFinder.textToColoredDrawable(ppmBand))

            text = when (imageSelection) {
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

package com.brandoncano.resistancecalculator.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import com.brandoncano.resistancecalculator.R

/**
 * @author: Brandon
 *
 * Job: holds all the logic for the menu items, to keep activities cleaner and more condense
 */
object MenuFunctions {

    private const val EMPTY_STRING = ""

    // determines which chart to display
    fun showResistorCharts(context: Context, imageSelection: Int): Boolean {
        val chartDialog = Dialog(context)
        chartDialog.setContentView(
            when (imageSelection) {
                4 -> R.layout.popup_chart_4
                5 -> R.layout.popup_chart_5
                6 -> R.layout.popup_chart_6
                else -> R.layout.popup_chart_6
            }
        )
        chartDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        chartDialog.show()
        return true
    }

    // generates to text to copy/share for CTV
    fun shareItemCTV(
        imageSelection: Int, screenText: TextView, numberBand1: String, numberBand2: String,
        numberBand3: String, multiplierBand: String, toleranceBand: String, ppmBand: String
    ): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        val text = when (imageSelection) {
            4 -> "${screenText.text}\n[ $numberBand1, $numberBand2, $multiplierBand, $toleranceBand ]"
            5 -> "${screenText.text}\n[ $numberBand1, $numberBand2, $numberBand3 $multiplierBand, $toleranceBand ]"
            6 -> "${screenText.text}\n[ $numberBand1, $numberBand2, $numberBand3, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> EMPTY_STRING
        }
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

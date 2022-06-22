package com.brandoncano.resistancecalculator

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.widget.TextView

object MenuFunctions {
    fun showResistorCharts(context: Context, imageSelection: Int) {
        val chartDialog = Dialog(context)
        chartDialog.setContentView(
            when(imageSelection) {
                4 -> R.layout.popup_chart_4
                5 -> R.layout.popup_chart_5
                6 -> R.layout.popup_chart_6
                else -> { R.layout.popup_chart_6 }
            }
        )
        chartDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        chartDialog.show()
    }

    fun shareItemCTV(imageSelection: Int, screenText: TextView, numberBand1: String, numberBand2: String,
                     numberBand3: String, multiplierBand: String, toleranceBand: String, ppmBand: String ) : Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        val text = when (imageSelection) {
            4 -> "${screenText.text}\n[ $numberBand1, $numberBand2, $multiplierBand, $toleranceBand ]"
            5 -> "${screenText.text}\n[ $numberBand1, $numberBand2, $numberBand3 $multiplierBand, $toleranceBand ]"
            6 -> "${screenText.text}\n[ $numberBand1, $numberBand2, $numberBand3, $multiplierBand, $toleranceBand, $ppmBand ]"
            else -> ""
        }
        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }

    fun shareItemVTC(imageSelection: Int, shareColors: Array<Int>, screenText: TextView, toleranceBand: String, ppmBand: String) : Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"

        var nb1 = ""; var nb2 = ""; var nb3 = ""; var multi= ""; var tol = ""; var ppm = ""
        if(shareColors.isNotEmpty()) {
            nb1 = ColorFinder.idToColorText(shareColors[0])
            nb2 = ColorFinder.idToColorText(shareColors[1])
            nb3 = ColorFinder.idToColorText(shareColors[2])
            multi = ColorFinder.idToColorText(shareColors[3])
            tol = ColorFinder.idToColorText(ColorFinder.toleranceImage(toleranceBand))
            ppm = ColorFinder.idToColorText(ColorFinder.ppmImage(ppmBand))
        }

        val text = when (imageSelection) {
            4 -> "${screenText.text}\n[ $nb1, $nb2, $multi, $tol ]"
            5 -> "${screenText.text}\n[ $nb1, $nb2, $nb3, $multi, $tol ]"
            6 -> "${screenText.text}\n[ $nb1, $nb2, $nb3, $multi, $tol, $ppm ]"
            else -> ""
        }

        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }

    fun feedback(): Uri {
        return Uri.parse(
            "mailto:brandoncano.development@gmail.com?subject="
                    + Uri.encode("[Feedback] - Resistance Calculator")
                .toString() + "&body=" + Uri.encode("")
        )
    }
}
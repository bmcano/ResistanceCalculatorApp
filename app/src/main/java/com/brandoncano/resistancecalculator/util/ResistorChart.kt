package com.brandoncano.resistancecalculator.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.brandoncano.resistancecalculator.R

/**
 * Job: Will show a popup dialog of the corresponding resistor chart.
 */
object ResistorChart {

    fun show(context: Context, imageSelection: Int) {
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
    }
}
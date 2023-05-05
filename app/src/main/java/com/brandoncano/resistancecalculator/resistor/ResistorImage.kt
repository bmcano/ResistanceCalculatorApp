package com.brandoncano.resistancecalculator.resistor

import android.content.Context
import android.widget.ImageView
import com.brandoncano.resistancecalculator.util.setBandColor

/**
 * Job: Hold the contents for the resistor image.
 */
data class ResistorImage(
    val band1: ImageView,
    val band2: ImageView,
    val band3: ImageView,
    val band4: ImageView,
    val band5: ImageView,
    val band6: ImageView
) {
    fun clearResistor(context: Context) {
        band1.setBandColor(context)
        band2.setBandColor(context)
        band3.setBandColor(context)
        band4.setBandColor(context)
        band5.setBandColor(context)
        band6.setBandColor(context)
    }
}

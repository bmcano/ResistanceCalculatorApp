package com.brandoncano.resistancecalculator.resistor

import android.content.Context
import android.widget.ImageView
import com.brandoncano.resistancecalculator.components.Resistor
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
    fun setImageColors(context: Context, resistor: Resistor, numberOfBands: Int = 4, isVtC: Boolean = false) {
        var sigFigThree = resistor.sigFigBandThree
        var tolerance = resistor.toleranceBand
        var ppm = resistor.ppmBand
        if (isVtC) {
            tolerance = resistor.toleranceValue
            ppm = resistor.ppmValue
        }
        if (numberOfBands != 6) ppm = ""
        if (numberOfBands == 4) sigFigThree = ""
        band1.setBandColor(context, resistor.sigFigBandOne)
        band2.setBandColor(context, resistor.sigFigBandTwo)
        band3.setBandColor(context, sigFigThree)
        band4.setBandColor(context, resistor.multiplierBand)
        band5.setBandColor(context, tolerance)
        band6.setBandColor(context, ppm)
    }

    fun clearResistor(context: Context) {
        band1.setBandColor(context)
        band2.setBandColor(context)
        band3.setBandColor(context)
        band4.setBandColor(context)
        band5.setBandColor(context)
        band6.setBandColor(context)
    }
}

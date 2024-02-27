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
    fun setImageColors(context: Context, resistor: ResistorVtC) {
        setBands(context, resistor, resistor.toleranceValue, resistor.ppmValue)
    }

    fun setImageColors(context: Context, resistor: ResistorCtV) {
        setBands(context, resistor, resistor.toleranceBand, resistor.ppmBand)
    }

    private fun setBands(context: Context, resistor: Resistor, tolerance: String, ppm: String) {
        band1.setBandColor(context, resistor.sigFigBandOne)
        band2.setBandColor(context, resistor.sigFigBandTwo)
        band3.setBandColor(context, if (resistor.isThreeFourBand()) "" else resistor.sigFigBandThree)
        band4.setBandColor(context, resistor.multiplierBand)
        band5.setBandColor(context, if (resistor.isThreeBand()) "" else tolerance)
        band6.setBandColor(context, if (resistor.isSixBand()) ppm else "")
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

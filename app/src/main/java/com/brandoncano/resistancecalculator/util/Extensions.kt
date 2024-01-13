package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.ImageTextArrayAdapter
import com.brandoncano.resistancecalculator.components.SpinnerItem
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.resistor.ResistorCtV
import com.brandoncano.resistancecalculator.resistor.ResistorImage
import com.brandoncano.resistancecalculator.resistor.ResistorVtC
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Job: Provide extension functions to different objects.
 */
fun ImageView.setBandColor(context: Context, colorText: String = "") {
    val color = ColorFinder.textToColor(colorText)
    this.setColorFilter(ContextCompat.getColor(context, color))
}

fun TextView.updateResistance(resistor: ResistorCtV) {
    this.text = ResistanceFormatter.calculate(resistor)
    resistor.updateResistance(this.text.toString())
}

fun TextView.updateResistance(resistor: ResistorVtC) {
    ResistorFormatter.generateResistor(resistor)
    this.text = resistor.getResistanceText()
    resistor.updateResistance(this.text.toString())
}

fun AutoCompleteTextView.setDropDownDrawable(color: String) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        ColorFinder.textToColoredDrawable(color), 0, 0, 0
    )
}

fun AutoCompleteTextView.setDropdownOnClickListener(
    context: Context,
    spinnerItemArray: Array<SpinnerItem>,
    resistor: ResistorCtV,
    resistorImage: ResistorImage,
    textView: TextView,
) {
    val (band: String, image: ImageView, stateData: StateData) = when (this.id) {
        R.id.spinner1 -> Triple(resistor.sigFigBandOne, resistorImage.band1, StateData.SIGFIG_BAND_ONE_CTV)
        R.id.spinner2 -> Triple(resistor.sigFigBandTwo, resistorImage.band2, StateData.SIGFIG_BAND_TWO_CTV)
        R.id.spinner3 -> Triple(resistor.sigFigBandThree, resistorImage.band3, StateData.SIGFIG_BAND_THREE_CTV)
        R.id.spinner4 -> Triple(resistor.multiplierBand, resistorImage.band4, StateData.MULTIPLIER_BAND_CTV)
        R.id.spinner5 -> Triple(resistor.toleranceBand, resistorImage.band5, StateData.TOLERANCE_BAND_CTV)
        R.id.spinner6 -> Triple(resistor.ppmBand, resistorImage.band6, StateData.PPM_BAND_CTV)
        else -> Triple(resistor.sigFigBandOne, resistorImage.band1, StateData.SIGFIG_BAND_ONE_CTV)
    }

    this.setText(band)
    this.setDropDownDrawable(band)
    val adapter = ImageTextArrayAdapter(context, spinnerItemArray)
    this.setAdapter(adapter)
    this.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
        val selection = this.adapter.getItem(position).toString()
        when (this.id) {
            R.id.spinner1 -> resistor.sigFigBandOne = selection
            R.id.spinner2 -> resistor.sigFigBandTwo = selection
            R.id.spinner3 -> resistor.sigFigBandThree = selection
            R.id.spinner4 -> resistor.multiplierBand = selection
            R.id.spinner5 -> resistor.toleranceBand = selection
            R.id.spinner6 -> resistor.ppmBand = selection
        }
        this.setDropDownDrawable(selection)
        image.setBandColor(context, selection)
        textView.updateResistance(resistor)
        stateData.saveData(context, this.text.toString())
        this.clearFocus()
    }
}

fun BottomNavigationView.updateNavigationView(buttonSelection: String) {
    this.selectedItemId = when (buttonSelection) {
        "3" -> R.id.selected_three_nav
        "5" -> R.id.selected_five_nav
        "6" -> R.id.selected_six_nav
        else -> R.id.selected_four_nav
    }
}

package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.core.content.ContextCompat

/**
 * Job: Provide extension functions to different objects.
 */
fun AutoCompleteTextView.setDropDownDrawable(color: String) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        ColorFinder.textToColoredDrawable(color), 0, 0, 0
    )
}

fun ImageView.setBandColor(context: Context, colorText: String = "") {
    val color = ColorFinder.textToColor(colorText)
    this.setColorFilter(ContextCompat.getColor(context, color))
}

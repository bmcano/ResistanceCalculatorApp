package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.view.MenuItem
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.brandoncano.resistancecalculator.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Job: Provide extension functions to different objects.
 */
fun ImageView.setBandColor(context: Context, colorText: String = "") {
    val color = ColorFinder.textToColor(colorText)
    this.setColorFilter(ContextCompat.getColor(context, color))
}

fun AutoCompleteTextView.setDropDownDrawable(color: String) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(
        ColorFinder.textToColoredDrawable(color), 0, 0, 0
    )
}

fun BottomNavigationView.updateNavigationView(buttonSelection: Int) {
    this.selectedItemId = when (buttonSelection) {
        3 -> R.id.selected_three_nav
        5 -> R.id.selected_five_nav
        6 -> R.id.selected_six_nav
        else -> R.id.selected_four_nav
    }
}

fun MenuItem.getNavigationValue(): Int {
    return when (this.itemId) {
        R.id.selected_three_nav -> 3
        R.id.selected_five_nav -> 5
        R.id.selected_six_nav -> 6
        else -> 4
    }
}


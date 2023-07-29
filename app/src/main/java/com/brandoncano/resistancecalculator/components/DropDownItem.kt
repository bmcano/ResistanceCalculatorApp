package com.brandoncano.resistancecalculator.components

import androidx.compose.ui.graphics.Color

data class DropDownItem(val name: String, val color: Color) {

    override fun toString(): String {
        return name
    }
}

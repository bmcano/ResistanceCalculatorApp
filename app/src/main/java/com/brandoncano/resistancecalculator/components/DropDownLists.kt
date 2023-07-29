package com.brandoncano.resistancecalculator.components

import androidx.compose.ui.graphics.Color.Companion.Gray
import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.ui.theme.Black
import com.brandoncano.resistancecalculator.ui.theme.Blue
import com.brandoncano.resistancecalculator.ui.theme.Brown
import com.brandoncano.resistancecalculator.ui.theme.Green
import com.brandoncano.resistancecalculator.ui.theme.Orange
import com.brandoncano.resistancecalculator.ui.theme.Red
import com.brandoncano.resistancecalculator.ui.theme.Violet
import com.brandoncano.resistancecalculator.ui.theme.Yellow

object DropDownLists {

    val numberItems = listOf(
        DropDownItem(Colors.BLACK, Black),
        DropDownItem(Colors.BROWN, Brown),
        DropDownItem(Colors.RED, Red),
        DropDownItem(Colors.ORANGE, Orange),
        DropDownItem(Colors.YELLOW, Yellow),
        DropDownItem(Colors.GREEN, Green),
        DropDownItem(Colors.BLUE, Blue),
        DropDownItem(Colors.VIOLET, Violet),
        DropDownItem(Colors.GRAY, Gray),
    )

}
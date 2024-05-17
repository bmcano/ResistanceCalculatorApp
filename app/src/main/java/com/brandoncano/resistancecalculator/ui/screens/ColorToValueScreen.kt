package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownItem
import com.brandoncano.resistancecalculator.ui.composables.OutlinedDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

@Composable
fun ColorToValueScreen(context: Context, navController: NavController) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Content(context, navController)
        }
    }
}

@Composable
private fun Content(context: Context, navController: NavController) {
    Column {
        val item1 = DropdownItem(imageResId = R.drawable.square_red, name = "Item 1", value = "Value 1")
        val item2 = DropdownItem(imageResId = R.drawable.square_orange, name = "Item 2", value = "Value 2")
        val item3 = DropdownItem(imageResId = R.drawable.square_yellow, name = "Item 3", value = "Value 3")
        val item4 = DropdownItem(imageResId = R.drawable.square_green, name = "Item 4", value = "Value 4")
        val item5 = DropdownItem(imageResId = R.drawable.square_blue, name = "Item 5", value = "Value 5")
        val item6 = DropdownItem(imageResId = R.drawable.square_violet, name = "Item 6", value = "Value 6")
        val list = listOf(item1, item2, item3, item4, item5, item6)
        OutlinedDropDownMenu("test", list)
    }
}
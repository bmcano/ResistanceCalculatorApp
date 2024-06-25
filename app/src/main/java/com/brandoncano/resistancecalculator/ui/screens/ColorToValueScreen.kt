package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownItem
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.MenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.OutlinedDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.ResistorLayout
import com.brandoncano.resistancecalculator.ui.composables.ValueToColorMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

@Composable
fun ColorToValueScreen(context: Context, navController: NavController) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController)
        }
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuTopAppBar(stringResource(R.string.color_to_value), interactionSource) {
            ValueToColorMenuItem(navController, interactionSource)
            FeedbackMenuItem(context, interactionSource)
            ClearSelectionsMenuItem(interactionSource) {
                // will add later
            }
            AboutAppMenuItem(navController, interactionSource)
        }

//        val item1 = DropdownItem(imageResId = R.drawable.square_red, name = "Item 1", value = "Value 1")
//        val item2 = DropdownItem(imageResId = R.drawable.square_orange, name = "Item 2", value = "Value 2")
//        val item3 = DropdownItem(imageResId = R.drawable.square_yellow, name = "Item 3", value = "Value 3")
//        val item4 = DropdownItem(imageResId = R.drawable.square_green, name = "Item 4", value = "Value 4")
//        val item5 = DropdownItem(imageResId = R.drawable.square_blue, name = "Item 5", value = "Value 5")
//        val item6 = DropdownItem(imageResId = R.drawable.square_violet, name = "Item 6", value = "Value 6")
//        val list = listOf(item1, item2, item3, item4, item5, item6)
//        OutlinedDropDownMenu("test", list)
        ResistorLayout(band1 = "", band2 = "", band3 = "", band4 = "", band5 = "", band6 = "")
    }
}

@AppScreenPreviews
@Composable
fun ColorToValueScreenPreview() {
    val app = HomeActivity()
    ColorToValueScreen(app, NavController(app))
}
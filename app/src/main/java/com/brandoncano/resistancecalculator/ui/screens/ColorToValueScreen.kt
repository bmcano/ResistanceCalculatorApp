package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownLists
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.CalculatorNavigationBar
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
    var navBarSelection by remember { mutableIntStateOf(1) }

    Scaffold(
        bottomBar = {
            CalculatorNavigationBar(navBarSelection) {
                navBarSelection = it
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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

            ResistorLayout(band1 = "", band2 = "", band3 = "", band4 = "", band5 = "", band6 = "")

            OutlinedDropDownMenu(
                label = R.string.number_band_hint1,
                leadingIcon = -1,
                items = DropdownLists.NUMBER_LIST_NO_BLACK
            ) {

            }
            OutlinedDropDownMenu(
                label = R.string.number_band_hint2,
                leadingIcon = -1,
                items = DropdownLists.NUMBER_LIST
            ) {

            }
            if (navBarSelection == 2 || navBarSelection == 3) {
                OutlinedDropDownMenu(
                    label = R.string.number_band_hint3,
                    leadingIcon = -1,
                    items = DropdownLists.NUMBER_LIST
                ) {

                }
            }
            OutlinedDropDownMenu(
                label = R.string.multiplier_band_hint,
                leadingIcon = -1,
                items = DropdownLists.MULTIPLIER_LIST
            ) {

            }
            if (navBarSelection != 0) {
                OutlinedDropDownMenu(
                    label = R.string.tolerance_band_hint,
                    leadingIcon = -1,
                    items = DropdownLists.NUMBER_LIST
                ) {

                }
            }
            if (navBarSelection == 3) {
                OutlinedDropDownMenu(
                    label = R.string.ppm_band_hint,
                    leadingIcon = -1,
                    items = DropdownLists.NUMBER_LIST
                ) {

                }
            }
        }
    }
}

@AppScreenPreviews
@Composable
fun ColorToValueScreenPreview() {
    val app = HomeActivity()
    ColorToValueScreen(app, NavController(app))
}
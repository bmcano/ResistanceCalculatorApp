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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownLists
import com.brandoncano.resistancecalculator.model.ResistorViewModel
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
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
fun ColorToValueScreen(context: Context, navController: NavController, viewModel: ResistorViewModel) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController, viewModel)
        }
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController, viewModel: ResistorViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    var navBarSelection by remember { mutableIntStateOf(viewModel.getNavBarSelection()) }
    val resistor by remember { mutableStateOf(viewModel.resistor) }
    var band1 by remember { mutableStateOf(resistor.band1) }
    var band2 by remember { mutableStateOf(resistor.band2) }
    var band3 by remember { mutableStateOf(resistor.band3) }
    var band4 by remember { mutableStateOf(resistor.band4) }
    var band5 by remember { mutableStateOf(resistor.band5) }
    var band6 by remember { mutableStateOf(resistor.band6) }

    Scaffold(
        bottomBar = {
            CalculatorNavigationBar(navBarSelection) {
                navBarSelection = it
                // temp adding +3 until new logic is written to account for navbar
                viewModel.saveNumberOfBands(it + 3)
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

            ResistorLayout(band1, band2, band3, band4, band5, band6)

            OutlinedDropDownMenu(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = R.string.number_band_hint1,
                selectedOption = band1,
                items = DropdownLists.NUMBER_LIST_NO_BLACK
            ) {
                resistor.band1 = it
                band1 = it
                viewModel.saveResistorColors(resistor)
            }
            OutlinedDropDownMenu(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                label = R.string.number_band_hint2,
                selectedOption = band2,
                items = DropdownLists.NUMBER_LIST
            ) {
                resistor.band2 = it
                band2 = it
                viewModel.saveResistorColors(resistor)
            }
            if (navBarSelection == 2 || navBarSelection == 3) {
                OutlinedDropDownMenu(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    label = R.string.number_band_hint3,
                    selectedOption = band3,
                    items = DropdownLists.NUMBER_LIST
                ) {
                    resistor.band3 = it
                    band3 = it
                    viewModel.saveResistorColors(resistor)
                }
            }
            OutlinedDropDownMenu(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                label = R.string.multiplier_band_hint,
                selectedOption = band4,
                items = DropdownLists.MULTIPLIER_LIST
            ) {
                resistor.band4 = it
                band4 = it
                viewModel.saveResistorColors(resistor)
            }
            if (navBarSelection != 0) {
                OutlinedDropDownMenu(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = band5,
                    items = DropdownLists.NUMBER_LIST
                ) {
                    resistor.band5 = it
                    band5 = it
                    viewModel.saveResistorColors(resistor)
                }
            }
            if (navBarSelection == 3) {
                OutlinedDropDownMenu(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    label = R.string.ppm_band_hint,
                    selectedOption = band6,
                    items = DropdownLists.NUMBER_LIST
                ) {
                    resistor.band6 = it
                    band6 = it
                    viewModel.saveResistorColors(resistor)
                }
            }
        }
    }
}

@AppScreenPreviews
@Composable
fun ColorToValueScreenPreview() {
    val app = HomeActivity()
    val viewModel = viewModel<ResistorViewModel>(factory = ResistorViewModelFactory(app))
    ColorToValueScreen(app, NavController(app), viewModel)
}

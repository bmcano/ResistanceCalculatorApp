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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownLists
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppButton
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppTextField
import com.brandoncano.resistancecalculator.ui.composables.CalculatorNavigationBar
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ColorToValueMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.MenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.OutlinedDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.ResistorLayout
import com.brandoncano.resistancecalculator.ui.composables.TextDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.formatResistor

@Composable
fun ValueToColorScreen(
    context: Context,
    navController: NavController,
    viewModel: ResistorVtcViewModel,
    navBarPosition: Int,
    resistorVtc: LiveData<ResistorVtc>
) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController, viewModel, navBarPosition, resistorVtc)
        }
    }
}

@Composable
private fun ContentView(
    context: Context,
    navController: NavController,
    viewModel: ResistorVtcViewModel,
    navBarPosition: Int,
    resistorVtc: LiveData<ResistorVtc>
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    var resetDropdown by remember { mutableStateOf(false) }
    val resistor by resistorVtc.observeAsState(ResistorVtc())
    var resistance by remember { mutableStateOf(resistor.resistance) }
    var units by remember { mutableStateOf(resistor.units) }
    var band5 by remember { mutableStateOf(resistor.band5) }
    var band6 by remember { mutableStateOf(resistor.band6) }

    Scaffold(
        bottomBar = {
            CalculatorNavigationBar(navBarSelection) {
                navBarSelection = it
                resistor.numberOfBands = it + 3
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
                ColorToValueMenuItem(navController, interactionSource)
                FeedbackMenuItem(context, interactionSource)
                ClearSelectionsMenuItem(interactionSource) {
                    viewModel.clear()
                    resetDropdown = true
                    focusManager.clearFocus()
                }
                AboutAppMenuItem(navController, interactionSource)
            }

            ResistorLayout(resistor)
            AppTextField(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = R.string.type_resistance_hint,
                text = "",
                reset = resetDropdown,
                resistor = resistor,
            ) {
                resistor.resistance = it
                resistance = it
                viewModel.saveResistorValues(resistor)
                resetDropdown = false
            }

            AppButton(
                modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp),
                text = stringResource(id = R.string.calculate_btn)
            ) {
                resistor.formatResistor()
                viewModel.saveResistorValues(resistor)
                resetDropdown = false
            }

            TextDropDownMenu(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = R.string.units_hint,
                selectedOption = units,
                items = DropdownLists.UNITS_LIST,
                reset = resetDropdown,
            ) {
                resistor.units = it
                units = it
                resistor.formatResistor()
                viewModel.saveResistorValues(resistor)
                resetDropdown = false
            }
            if (navBarSelection != 0) {
                OutlinedDropDownMenu(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = band5,
                    items = DropdownLists.TOLERANCE_LIST,
                    reset = resetDropdown,
                ) {
                    resistor.band5 = it
                    band5 = it
                    viewModel.saveResistorValues(resistor)
                    resetDropdown = false
                }
            }
            if (navBarSelection == 3) {
                OutlinedDropDownMenu(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    label = R.string.ppm_band_hint,
                    selectedOption = band6,
                    items = DropdownLists.PPM_LIST,
                    reset = resetDropdown,
                ) {
                    resistor.band6 = it
                    band6 = it
                    viewModel.saveResistorValues(resistor)
                    resetDropdown = false
                }
            }
        }
    }
}

@AppScreenPreviews
@Composable
fun ValueToColorScreenPreview() {
    val app = HomeActivity()
    val viewModel = viewModel<ResistorVtcViewModel>(factory = ResistorViewModelFactory(app))
    val resistor = MutableLiveData<ResistorVtc>()
    ValueToColorScreen(app, NavController(app), viewModel, 1, resistor)
}

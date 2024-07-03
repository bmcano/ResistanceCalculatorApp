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
import com.brandoncano.resistancecalculator.ui.RcvActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppTextField
import com.brandoncano.resistancecalculator.ui.composables.CalculatorNavigationBar
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ColorToValueMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.RcvMenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.OutlinedDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.ResistorLayout
import com.brandoncano.resistancecalculator.ui.composables.TextDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.IsValidResistance
import com.brandoncano.resistancecalculator.util.formatResistor
import com.brandoncano.resistancecalculator.util.getDisplayableValue

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
    var reset by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
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
                viewModel.saveNumberOfBands(it + 3)
                isError = !IsValidResistance.execute(resistor, resistance)
                if (isError) return@CalculatorNavigationBar
                resistor.formatResistor()
                viewModel.saveResistorValues(resistor)
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
            RcvMenuTopAppBar(stringResource(R.string.manu_value_to_color), interactionSource) {
                ColorToValueMenuItem(navController, interactionSource)
                FeedbackMenuItem(context, interactionSource)
                ClearSelectionsMenuItem(interactionSource) {
                    viewModel.clear()
                    reset = true
                    focusManager.clearFocus()
                    band5 = ""
                    band6 = ""
                }
                AboutAppMenuItem(navController, interactionSource)
            }

            ResistorLayout(resistor, resistor.getDisplayableValue(context))
            AppTextField(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = R.string.type_resistance_hint,
                text = resistance,
                reset = reset,
                isError = isError,
            ) {
                reset = false
                resistor.resistance = it
                resistance = it
                isError = !IsValidResistance.execute(resistor, resistance)
                if (isError) return@AppTextField
                resistor.formatResistor()
                viewModel.saveResistorValues(resistor)
            }
            TextDropDownMenu(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = R.string.units_hint,
                selectedOption = units,
                items = DropdownLists.UNITS_LIST,
                reset = reset,
            ) {
                resistor.units = it
                units = it
                resistor.formatResistor()
                viewModel.saveResistorValues(resistor)
                reset = false
            }
            if (navBarSelection != 0) {
                OutlinedDropDownMenu(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = band5,
                    items = DropdownLists.TOLERANCE_LIST,
                    reset = reset,
                    isVtC = true
                ) {
                    resistor.band5 = it
                    band5 = it
                    viewModel.saveResistorValues(resistor)
                    reset = false
                }
            }
            if (navBarSelection == 3) {
                OutlinedDropDownMenu(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    label = R.string.ppm_band_hint,
                    selectedOption = band6,
                    items = DropdownLists.PPM_LIST,
                    reset = reset,
                    isVtC = true,
                ) {
                    resistor.band6 = it
                    band6 = it
                    viewModel.saveResistorValues(resistor)
                    reset = false
                }
            }
        }
    }
}

@AppScreenPreviews
@Composable
fun ValueToColorScreenPreview() {
    val app = RcvActivity()
    val viewModel = viewModel<ResistorVtcViewModel>(factory = ResistorViewModelFactory(app))
    val resistor = MutableLiveData<ResistorVtc>()
    ValueToColorScreen(app, NavController(app), viewModel, 1, resistor)
}

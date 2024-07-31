package com.brandoncano.resistancecalculator.ui.screens.resistor

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.brandoncano.resistancecalculator.components.BandKey
import com.brandoncano.resistancecalculator.components.DropdownLists
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtv
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppMenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.CalculatorNavigationBar
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.ShareMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ValueToColorMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.formatResistance

@Composable
fun ColorToValueScreen(
    context: Context,
    navController: NavController,
    viewModel: ResistorCtvViewModel,
    navBarPosition: Int,
    resistorCtv: LiveData<ResistorCtv>,
) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController, viewModel, navBarPosition, resistorCtv)
        }
    }
}

@Composable
private fun ContentView(
    context: Context,
    navController: NavController,
    viewModel: ResistorCtvViewModel,
    navBarPosition: Int,
    resistorCtv: LiveData<ResistorCtv>,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    var resetDropdown by remember { mutableStateOf(false) }
    val resistor by resistorCtv.observeAsState(ResistorCtv())
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
                viewModel.saveNavBarSelection(it)
            }
        }
    ) { paddingValues ->
        fun postSelectionActions() {
            resetDropdown = false
            focusManager.clearFocus()
            viewModel.saveResistorColors(resistor)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppMenuTopAppBar(stringResource(R.string.title_color_to_value), interactionSource) {
                ValueToColorMenuItem(navController, interactionSource)
                val shareableText = "${resistor.formatResistance()}\n$resistor"
                ShareMenuItem(context, shareableText, interactionSource)
                FeedbackMenuItem(context, interactionSource)
                ClearSelectionsMenuItem(interactionSource) {
                    viewModel.clear()
                    resetDropdown = true
                    focusManager.clearFocus()
                    // these are needed since the viewModel doesn't update them automatically
                    band3 = ""
                    band5 = ""
                    band6 = ""
                }
                AboutAppMenuItem(navController, interactionSource)
            }

            ResistorLayout(resistor)

            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
                label = R.string.number_band_hint1,
                selectedOption = band1,
                items = DropdownLists.NUMBER_LIST_NO_BLACK,
                reset = resetDropdown,
            ) {
                band1 = it
                viewModel.updateBand(BandKey.Band1, it)
                postSelectionActions()
            }
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                label = R.string.number_band_hint2,
                selectedOption = band2,
                items = DropdownLists.NUMBER_LIST,
                reset = resetDropdown,
            ) {
                band2 = it
                viewModel.updateBand(BandKey.Band2, it)
                postSelectionActions()
            }
            if (navBarSelection == 2 || navBarSelection == 3) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    label = R.string.number_band_hint3,
                    selectedOption = band3,
                    items = DropdownLists.NUMBER_LIST,
                    reset = resetDropdown,
                ) {
                    band3 = it
                    viewModel.updateBand(BandKey.Band3, it)
                    postSelectionActions()
                }
            }
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                label = R.string.multiplier_band_hint,
                selectedOption = band4,
                items = DropdownLists.MULTIPLIER_LIST,
                reset = resetDropdown,
            ) {
                band4 = it
                viewModel.updateBand(BandKey.Band4, it)
                postSelectionActions()
            }
            if (navBarSelection != 0) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = band5,
                    items = DropdownLists.TOLERANCE_LIST,
                    reset = resetDropdown,
                ) {
                    band5 = it
                    viewModel.updateBand(BandKey.Band5, it)
                    postSelectionActions()
                }
            }
            if (navBarSelection == 3) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    label = R.string.ppm_band_hint,
                    selectedOption = band6,
                    items = DropdownLists.PPM_LIST,
                    reset = resetDropdown,
                ) {
                    band6 = it
                    viewModel.updateBand(BandKey.Band6, it)
                    postSelectionActions()
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@AppScreenPreviews
@Composable
private fun ColorToValueScreen4BandPreview() {
    val app = MainActivity()
    val viewModel = viewModel<ResistorCtvViewModel>(factory = ResistorViewModelFactory(app))
    val resistor = MutableLiveData<ResistorCtv>()
    ColorToValueScreen(app, NavController(app), viewModel, 1, resistor)
}

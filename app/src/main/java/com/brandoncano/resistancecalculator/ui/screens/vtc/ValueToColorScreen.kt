package com.brandoncano.resistancecalculator.ui.screens.vtc

import android.content.Context
import android.graphics.Picture
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material.icons.outlined.Looks5
import androidx.compose.material.icons.outlined.Looks6
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
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ColorToValueMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ImageTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.formatResistor
import com.brandoncano.resistancecalculator.util.isInputInvalid
import com.brandoncano.resistancecalculator.util.shareableText
import com.brandoncano.sharedcomponents.composables.AppDropDownMenu
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppNavigationBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTextField
import com.brandoncano.sharedcomponents.composables.ClearSelectionsMenuItem
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.composables.ShareImageMenuItem
import com.brandoncano.sharedcomponents.composables.ShareTextMenuItem
import com.brandoncano.sharedcomponents.data.NavigationBarOptions

@Composable
fun ValueToColorScreen(
    context: Context,
    navController: NavController,
    viewModel: ResistorVtcViewModel,
    navBarPosition: Int,
    resistorVtc: LiveData<ResistorVtc>
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ContentView(context, navController, viewModel, navBarPosition, resistorVtc)
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
    val showMenu = remember { mutableStateOf(false) }
    var reset by remember { mutableStateOf(false) }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    val resistor by resistorVtc.observeAsState(ResistorVtc())
    val resistance = remember { mutableStateOf(resistor.resistance) }
    var units by remember { mutableStateOf(resistor.units) }
    var band5 by remember { mutableStateOf(resistor.band5) }
    var band6 by remember { mutableStateOf(resistor.band6) }
    var isError by remember { mutableStateOf(resistor.isInputInvalid()) }
    var picture = remember { Picture() }

    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_value_to_color),
                interactionSource = interactionSource,
                showMenu = showMenu,
            ) {
                ColorToValueMenuItem(navController, showMenu)
                ClearSelectionsMenuItem {
                    showMenu.value = false
                    reset = true
                    isError = false
                    viewModel.clear()
                    focusManager.clearFocus()
                    band5 = ""; band6 = ""
                }
                ShareTextMenuItem(context, resistor.shareableText(), showMenu)
                ShareImageMenuItem(context, Symbols.APPLICATION_ID, showMenu, picture)
                FeedbackMenuItem(context, Symbols.APP_NAME, showMenu)
                AboutAppMenuItem(navController, showMenu)
            }
        },
        bottomBar = {
            AppNavigationBar(
                selection = navBarSelection,
                onClick = {
                    navBarSelection = it
                    viewModel.saveNavBarSelection(it)
                    isError = resistor.isInputInvalid()
                    if (!isError) {
                        viewModel.saveResistorValues(resistor)
                        resistor.formatResistor()
                    }
                },
                options = listOf(
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_three_band),
                        imageVector = Icons.Outlined.Looks3,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_four_band),
                        imageVector = Icons.Outlined.Looks4,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_five_band),
                        imageVector = Icons.Outlined.Looks5,
                    ),
                    NavigationBarOptions(
                        label = stringResource(id = R.string.navbar_six_band),
                        imageVector = Icons.Outlined.Looks6,
                    ),
                ),
            )
        }
    ) { paddingValues ->
        fun postSelectionActions() {
            reset = false
            viewModel.updateValues(resistance.value, units, band5, band6)
            isError = resistor.isInputInvalid()
            if (!isError) {
                viewModel.saveResistorValues(resistor)
                resistor.formatResistor()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            picture = resistorPicture(resistor, isError)
            AppTextField(
                label = stringResource(id = R.string.type_resistance_hint),
                modifier = Modifier.padding(top = 24.dp, start = 32.dp, end = 32.dp),
                value = resistance,
                reset = reset,
                isError = isError,
                errorMessage = stringResource(id = R.string.error_invalid_resistance)
            ) {
                resistance.value = it
                postSelectionActions()
            }
            AppDropDownMenu(
                label = stringResource(id = R.string.units_hint),
                modifier = Modifier.padding(top = 12.dp, start = 32.dp, end = 32.dp),
                selectedOption = units,
                items = DropdownLists.UNITS_LIST,
                reset = reset,
            ) {
                units = it
                focusManager.clearFocus()
                postSelectionActions()
            }
            if (navBarSelection != 0) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = band5,
                    items = DropdownLists.TOLERANCE_LIST,
                    reset = reset,
                    isVtC = true
                ) {
                    band5 = it
                    focusManager.clearFocus()
                    postSelectionActions()
                }
            }
            if (navBarSelection == 3) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.ppm_band_hint,
                    selectedOption = band6,
                    items = DropdownLists.PPM_LIST,
                    reset = reset,
                    isVtC = true,
                ) {
                    band6 = it
                    focusManager.clearFocus()
                    postSelectionActions()
                }
            }
            // this condition and spacer is here so the resistor can update between 4 and 5 bands
            if (navBarSelection == 2) Spacer(modifier = Modifier.height(0.dp))
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@AppScreenPreviews
@Composable
private fun ValueToColorScreenPreview() {
    val app = MainActivity()
    val viewModel = viewModel<ResistorVtcViewModel>(factory = ResistorViewModelFactory(app))
    val resistor = MutableLiveData<ResistorVtc>()
    ResistorCalculatorTheme {
        ValueToColorScreen(app, NavController(app), viewModel, 1, resistor)
    }
}

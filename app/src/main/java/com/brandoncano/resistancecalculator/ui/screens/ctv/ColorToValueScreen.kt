package com.brandoncano.resistancecalculator.ui.screens.ctv

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
import com.brandoncano.resistancecalculator.ui.composables.ShareImageMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ShareTextMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ValueToColorMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.shareableText

@Composable
fun ColorToValueScreen(
    context: Context,
    navController: NavController,
    viewModel: ResistorCtvViewModel,
    navBarPosition: Int,
    resistorCtv: LiveData<ResistorCtv>,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ContentView(context, navController, viewModel, navBarPosition, resistorCtv)
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
    val showMenu = remember { mutableStateOf(false) }
    var reset by remember { mutableStateOf(false) }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    val resistor by resistorCtv.observeAsState(ResistorCtv())
    var band1 by remember { mutableStateOf(resistor.band1) }
    var band2 by remember { mutableStateOf(resistor.band2) }
    var band3 by remember { mutableStateOf(resistor.band3) }
    var band4 by remember { mutableStateOf(resistor.band4) }
    var band5 by remember { mutableStateOf(resistor.band5) }
    var band6 by remember { mutableStateOf(resistor.band6) }
    var picture = remember { Picture() }

    fun postSelectionActions() {
        reset = false
        focusManager.clearFocus()
        viewModel.updateBands(band1, band2, band3, band4, band5, band6)
        viewModel.saveResistorColors(resistor)
    }

    Scaffold(
        topBar = {
            AppMenuTopAppBar(
                titleText = stringResource(R.string.title_color_to_value),
                interactionSource = interactionSource,
                showMenu = showMenu,
            ) {
                ValueToColorMenuItem(navController, showMenu)
                ClearSelectionsMenuItem {
                    showMenu.value = false
                    reset = true
                    viewModel.clear()
                    focusManager.clearFocus()
                    // Bug: these are needed since the viewModel doesn't update them for clearing
                    band3 = ""; band5 = ""; band6 = ""
                }
                ShareTextMenuItem(context, resistor.shareableText(), showMenu)
                ShareImageMenuItem(context, showMenu, picture)
                FeedbackMenuItem(context, showMenu)
                AboutAppMenuItem(navController, showMenu)
            }
        },
        bottomBar = {
            CalculatorNavigationBar(navBarSelection) {
                navBarSelection = it
                viewModel.saveNavBarSelection(it)
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
            picture = resistorPicture(resistor)
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 24.dp),
                label = R.string.number_band_hint1,
                selectedOption = band1,
                items = DropdownLists.NUMBER_LIST_NO_BLACK,
                reset = reset,
            ) {
                band1 = it
                postSelectionActions()
            }
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = R.string.number_band_hint2,
                selectedOption = band2,
                items = DropdownLists.NUMBER_LIST,
                reset = reset,
            ) {
                band2 = it
                postSelectionActions()
            }
            if (navBarSelection == 2 || navBarSelection == 3) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.number_band_hint3,
                    selectedOption = band3,
                    items = DropdownLists.NUMBER_LIST,
                    reset = reset,
                ) {
                    band3 = it
                    postSelectionActions()
                }
            }
            ImageTextDropDownMenu(
                modifier = Modifier.padding(top = 12.dp),
                label = R.string.multiplier_band_hint,
                selectedOption = band4,
                items = DropdownLists.MULTIPLIER_LIST,
                reset = reset,
            ) {
                band4 = it
                postSelectionActions()
            }
            if (navBarSelection != 0) {
                ImageTextDropDownMenu(
                    modifier = Modifier.padding(top = 12.dp),
                    label = R.string.tolerance_band_hint,
                    selectedOption = band5,
                    items = DropdownLists.TOLERANCE_LIST,
                    reset = reset,
                ) {
                    band5 = it
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
                ) {
                    band6 = it
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
    ResistorCalculatorTheme {
        ColorToValueScreen(app, NavController(app), viewModel, 1, resistor)
    }
}

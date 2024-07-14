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
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.components.SmdResistorLayout
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppTextField
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ColorToValueMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppMenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.ShareMenuItem
import com.brandoncano.resistancecalculator.ui.composables.SmdNavigationBar
import com.brandoncano.resistancecalculator.ui.composables.AppTextDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.ValueToColorMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.formatResistance
import com.brandoncano.resistancecalculator.util.isInputInvalid

@Composable
fun SmdScreen(
    context: Context,
    navController: NavController,
    viewModel: SmdResistorViewModel,
    navBarPosition: Int,
    smdResistor: LiveData<SmdResistor>
) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController, viewModel, navBarPosition, smdResistor)
        }
    }
}

@Composable
private fun ContentView(
    context: Context,
    navController: NavController,
    viewModel: SmdResistorViewModel,
    navBarPosition: Int,
    smdResistor: LiveData<SmdResistor>
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var navBarSelection by remember { mutableIntStateOf(navBarPosition) }
    var reset by remember { mutableStateOf(false) }
    val resistor by smdResistor.observeAsState(SmdResistor())
    var code by remember { mutableStateOf(resistor.code) }
    var units by remember { mutableStateOf(resistor.units) }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            SmdNavigationBar(navBarSelection) {
                navBarSelection = it
                viewModel.saveNavBarSelection(it)
                isError = resistor.isInputInvalid()
                if (!isError) {
                    viewModel.saveResistorValues(resistor)
                    resistor.formatResistance()
                }
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
            AppMenuTopAppBar(stringResource(R.string.menu_smd), interactionSource) {
                ColorToValueMenuItem(navController, interactionSource)
                ValueToColorMenuItem(navController, interactionSource)
                ShareMenuItem(context, resistor.toString(), interactionSource)
                FeedbackMenuItem(context, interactionSource)
                ClearSelectionsMenuItem(interactionSource) {
                    viewModel.clear()
                    reset = true
                    focusManager.clearFocus()
                }
                AboutAppMenuItem(navController, interactionSource)
            }

            SmdResistorLayout(resistor)
            AppTextField(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
                label = R.string.hint_smd_code,
                text = code,
                reset = reset,
                isError = isError,
            ) {
                reset = false
                code = it
                viewModel.updateCode(it)
                isError = resistor.isInputInvalid()
                if (!isError) {
                    viewModel.saveResistorValues(resistor)
                    resistor.formatResistance()
                }
            }
            AppTextDropDownMenu(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = R.string.units_hint,
                selectedOption = resistor.units,
                items = DropdownLists.UNITS_LIST,
                reset = reset,
            ) {
                units = it
                viewModel.updateUnits(it)
                reset = false
                focusManager.clearFocus()
                viewModel.saveResistorValues(resistor)
            }
        }
    }
}

@AppScreenPreviews
@Composable
private fun SmdScreenPreview() {
    val app = MainActivity()
    val viewModel = viewModel<SmdResistorViewModel>(factory = ResistorViewModelFactory(app))
    val resistor = MutableLiveData<SmdResistor>()
    SmdScreen(app, NavController(app), viewModel, 0, resistor)
}

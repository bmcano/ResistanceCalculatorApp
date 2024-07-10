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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownLists
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.ui.RcvActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppTextField
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ColorToValueMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.RcvMenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.SmdNavigationBar
import com.brandoncano.resistancecalculator.ui.composables.TextDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

@Composable
fun SmdScreen(
    context: Context,
    navController: NavController,
    viewModel: SmdResistorViewModel,
    navBarPosition: Int,
    resistorVtc: LiveData<SmdResistor>
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
    viewModel: SmdResistorViewModel,
    navBarPosition: Int,
    resistorVtc: LiveData<SmdResistor>
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        bottomBar = {
            SmdNavigationBar {

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
            RcvMenuTopAppBar(stringResource(R.string.menu_smd), interactionSource) {
                ColorToValueMenuItem(navController, interactionSource)
                // ShareMenuItem(context, shareableText, interactionSource)
                FeedbackMenuItem(context, interactionSource)
                ClearSelectionsMenuItem(interactionSource) {
                    // TODO - will add later
                }
                AboutAppMenuItem(navController, interactionSource)
            }


            // components plan (wip)
            // SMD resistor that shows code text - probably uses a box
            // - right below have resistance text, similar to CtV and VtC

            // text field for code - will validate similar to VtC
            AppTextField(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
                label = R.string.hint_smd_code,
                text = "", // will add with VM
                reset = false,
                isError = false,
            ) {
                // validation done here
            }
            // text field for resistance (?)
            AppTextField(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = R.string.type_resistance_hint,
                text = "", // will add with VM
                reset = false,
                isError = false,
            ) {
                // validation done here
            }
            // dropdown for units
            TextDropDownMenu(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                label = R.string.units_hint,
                selectedOption = "",
                items = DropdownLists.UNITS_LIST,
                reset = false,
            ) {

            }
        }
    }
}

@AppScreenPreviews
@Composable
private fun SmdScreenPreview() {
    val app = RcvActivity()
    val viewModel = viewModel<SmdResistorViewModel>(factory = ResistorViewModelFactory(app))
    val resistor = viewModel.getResistorLiveData()
    SmdScreen(app, NavController(app), viewModel, 0, resistor)
}

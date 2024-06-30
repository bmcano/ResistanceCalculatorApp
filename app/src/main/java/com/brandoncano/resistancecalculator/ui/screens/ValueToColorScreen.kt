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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.model.vtc.Resistor
import com.brandoncano.resistancecalculator.model.vtc.ResistorViewModel
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.CalculatorNavigationBar
import com.brandoncano.resistancecalculator.ui.composables.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.composables.ColorToValueMenuItem
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.MenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.ResistorLayout
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

@Composable
fun ValueToColorScreen(context: Context, navController: NavController, ) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController, )
        }
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController, ) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    var navBarSelection by remember { mutableIntStateOf(1) }
    var resetDropdown by remember { mutableStateOf(false) }

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
                ColorToValueMenuItem(navController, interactionSource)
                FeedbackMenuItem(context, interactionSource)
                ClearSelectionsMenuItem(interactionSource) {
//                    viewModel.clear()
                    resetDropdown = true
                    focusManager.clearFocus()
                }
                AboutAppMenuItem(navController, interactionSource)
            }

            val resistor = Resistor() // will eventually remove
//            ResistorLayout(resistor)
            // TODO
            // calculate button
            // outlined text field - resistance
            // units dropdown
            // tolerance dropdown
            // ppm dropdown
        }
    }
}

@AppScreenPreviews
@Composable
fun ValueToColorScreenPreview() {
    val app = HomeActivity()
//    val viewModel = viewModel<ResistorViewModel>(factory = ResistorViewModelFactory(app))
    ValueToColorScreen(app, NavController(app))
}

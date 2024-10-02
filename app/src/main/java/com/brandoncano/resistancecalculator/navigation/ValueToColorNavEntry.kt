package com.brandoncano.resistancecalculator.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.ui.screens.vtc.ValueToColorScreen
import com.brandoncano.resistancecalculator.util.formatResistor

fun NavGraphBuilder.valueToColorScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ValueToColor.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val viewModel: ResistorVtcViewModel = viewModel(factory = ResistorViewModelFactory(context))
        val resistor by viewModel.resistor.collectAsState()
        val navBarSelection by viewModel.navBarSelection.collectAsState()
        val isError by viewModel.isError.collectAsState()
        val openMenu = remember { mutableStateOf(false) }
        resistor.formatResistor()

        ValueToColorScreen(
            resistor = resistor,
            navBarPosition = navBarSelection,
            isError = isError,
            openMenu = openMenu,
            onClearSelectionsTapped = {
                openMenu.value = false
                viewModel.clear()
            },
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onColorToValueTapped = {
                openMenu.value = false
                navigateToColorToValue(navHostController)
            },
            onValueChanged = { resistance, units, band5, band6 ->
                viewModel.updateValues(resistance, units, band5, band6)
            },
            onNavBarSelectionChanged = { selection ->
                viewModel.saveNavBarSelection(selection)
            },
        )
    }
}

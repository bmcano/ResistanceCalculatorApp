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
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.ui.screens.ctv.ColorToValueScreen

fun NavGraphBuilder.colorToValueScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.ColorToValue.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val viewModel: ResistorCtvViewModel = viewModel(factory = ResistorViewModelFactory(context))
        val resistor by viewModel.resistor.collectAsState()
        val navBarSelection by viewModel.navBarSelection.collectAsState()
        val openMenu = remember { mutableStateOf(false) }

        ColorToValueScreen(
            openMenu = openMenu,
            resistor = resistor,
            navBarPosition = navBarSelection,
            onClearSelectionsTapped = {
                openMenu.value = false
                viewModel.clear()
            },
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onValueToColorTapped = {
                openMenu.value = false
                navigateToValueToColor(navHostController)
            },
            onUpdateBand = { bandNumber, color ->
                viewModel.updateBand(bandNumber, color)
            },
            onNavBarSelectionChanged = { selection ->
                viewModel.saveNavBarSelection(selection)
            },
        )
    }
}

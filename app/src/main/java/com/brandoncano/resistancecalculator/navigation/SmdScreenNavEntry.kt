package com.brandoncano.resistancecalculator.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.ui.screens.smd.SmdScreen

fun NavGraphBuilder.smdScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.Smd.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        val context = LocalContext.current
        val openMenu = remember { mutableStateOf(false) }
        val viewModel = viewModel<SmdResistorViewModel>(factory = ResistorViewModelFactory(context))
        val resistor = viewModel.getResistorLiveData()

        SmdScreen(
            openMenu = openMenu,
            resistor = resistor,
            onClearSelectionsTapped = {
                openMenu.value = false
                viewModel.clear()
            },
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onValueChanged = { code, units, resistorState ->
                viewModel.updateValues(code, units)
                viewModel.saveResistorValues(resistorState)
            },
            onNavBarSelectionChanged = { selection, resistorState ->
                viewModel.saveNavBarSelection(selection)
                viewModel.saveResistorValues(resistorState)

            },
            navBarPosition = viewModel.getNavBarSelection(),
        )
    }
}

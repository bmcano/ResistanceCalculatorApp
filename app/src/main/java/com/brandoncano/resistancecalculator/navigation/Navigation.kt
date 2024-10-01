package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.model.smd.SmdResistorViewModel
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.ui.screens.ctv.ColorToValueScreen
import com.brandoncano.resistancecalculator.ui.screens.smd.SmdScreen
import com.brandoncano.resistancecalculator.ui.screens.vtc.ValueToColorScreen
import com.brandoncano.resistancecalculator.util.formatResistor
import com.brandoncano.sharedcomponents.data.Apps
import com.brandoncano.sharedcomponents.screen.ViewOurAppsScreen

/**
 * Note: Keep each navigation route in alphabetical order
 */

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        aboutScreen(navController)
        homeScreen(navController)

        composable(
            route = Screen.ColorToValue.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) {
            val viewModel = viewModel<ResistorCtvViewModel>(factory = ResistorViewModelFactory(context))
            val navBarPosition = viewModel.getNavBarSelection()
            val resistor = viewModel.getResistorLiveData()
            ColorToValueScreen(context, navController, viewModel, navBarPosition, resistor)
        }
        composable(
            route = Screen.Smd.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) {
            val viewModel = viewModel<SmdResistorViewModel>(factory = ResistorViewModelFactory(context))
            val navBarPosition = viewModel.getNavBarSelection()
            val resistor = viewModel.getResistorLiveData()
            SmdScreen(context, navController, viewModel, navBarPosition, resistor)
        }
        composable(
            route = Screen.ValueToColor.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
        ) {
            val viewModel = viewModel<ResistorVtcViewModel>(factory = ResistorViewModelFactory(context))
            val navBarPosition = viewModel.getNavBarSelection()
            val resistor = viewModel.getResistorLiveData()
            resistor.value?.formatResistor()
            ValueToColorScreen(context, navController, viewModel, navBarPosition, resistor)
        }
        composable(
            route = Screen.ViewOurApps.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            ViewOurAppsScreen(context, Apps.Resistor)
        }
    }
}

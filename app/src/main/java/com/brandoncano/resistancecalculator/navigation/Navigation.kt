package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtvViewModel
import com.brandoncano.resistancecalculator.model.ResistorViewModelFactory
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtv
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtcViewModel
import com.brandoncano.resistancecalculator.ui.screens.AboutScreen
import com.brandoncano.resistancecalculator.ui.screens.ColorToValueScreen
import com.brandoncano.resistancecalculator.ui.screens.HomeScreen
import com.brandoncano.resistancecalculator.ui.screens.ValueToColorScreen

/**
 * Job: Holds all the navigation information
 */

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            HomeScreen(context, navController)
        }
        composable(
            route = Screen.About.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            AboutScreen(context)
        }
        composable(
            route = Screen.ColorToValue.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            val viewModel = viewModel<ResistorCtvViewModel>(factory = ResistorViewModelFactory(context))
            val navBarPosition = viewModel.getNavBarSelection()
            val resistor = viewModel.getResistorLiveData()
            ColorToValueScreen(context, navController, viewModel, navBarPosition, resistor)
        }
        composable(
            route = Screen.ValueToColor.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            val viewModel = viewModel<ResistorVtcViewModel>(factory = ResistorViewModelFactory(context))
            val navBarPosition = viewModel.getNavBarSelection()
            val resistor = viewModel.getResistorLiveData()
            ValueToColorScreen(context, navController, viewModel, navBarPosition, resistor)
        }
    }
}

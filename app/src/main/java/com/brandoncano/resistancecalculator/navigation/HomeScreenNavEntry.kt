package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.ui.screens.home.HomeScreen
import com.brandoncano.sharedcomponents.utils.OpenLink

fun NavGraphBuilder.homeScreen(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        val context = LocalContext.current
        val openMenu = remember { mutableStateOf(false) }
        HomeScreen(
            openMenu = openMenu,
            onAboutTapped = {
                openMenu.value = false
                navigateToAbout(navHostController)
            },
            onColorToValueTapped = { navigateToColorToValue(navHostController) },
            onValueToColorTapped = { navigateToValueToColor(navHostController) },
            onSmdTapped = { navigateToSmd(navHostController) },
            onRateThisAppTapped = { navigateToGooglePlay(context) },
            onViewOurAppsTapped = { navigateToOurApps(navHostController) },
        )
    }
}

fun navigateToAbout(navController: NavHostController) {
    navController.navigate(Screen.About.route)
}

fun navigateToColorToValue(navController: NavHostController) {
    navController.navigate(Screen.ColorToValue.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToValueToColor(navController: NavHostController) {
    navController.navigate(Screen.ValueToColor.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToSmd(navController: NavHostController) {
    navController.navigate(Screen.Smd.route) {
        popUpTo(Screen.Home.route)
    }
}

fun navigateToGooglePlay(context: Context) {
    OpenLink.execute(context, Links.RESISTOR_PLAYSTORE)
}

fun navigateToOurApps(navController: NavHostController) {
    navController.navigate(Screen.ViewOurApps.route)
}

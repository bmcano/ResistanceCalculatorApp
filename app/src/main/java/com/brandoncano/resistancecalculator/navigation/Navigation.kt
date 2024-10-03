package com.brandoncano.resistancecalculator.navigation

import android.content.Context
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        colorToValueScreen(navController)
        homeScreen(navController)
        learnSmdCodes(navController)
        smdScreen(navController)
        valueToColorScreen(navController)

        composable(
            route = Screen.ViewOurApps.route,
            enterTransition = { slideInVertically(initialOffsetY = { it }) },
            exitTransition = { slideOutVertically(targetOffsetY = { it }) },
        ) {
            ViewOurAppsScreen(context, Apps.Resistor)
        }
    }
}

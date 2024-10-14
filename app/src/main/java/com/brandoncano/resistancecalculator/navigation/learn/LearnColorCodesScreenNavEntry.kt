package com.brandoncano.resistancecalculator.navigation.learn

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.screens.info.LearnColorCodesScreen

fun NavGraphBuilder.learnColorCodes(
    navHostController: NavHostController,
) {
    composable(
        route = Screen.LearnColorCodes.route,
        enterTransition = { slideInVertically(initialOffsetY = { it }) },
        exitTransition = { slideOutVertically(targetOffsetY = { it }) },
    ) {
        LearnColorCodesScreen(
            onNavigateBack = { navHostController.popBackStack() },
        )
    }
}

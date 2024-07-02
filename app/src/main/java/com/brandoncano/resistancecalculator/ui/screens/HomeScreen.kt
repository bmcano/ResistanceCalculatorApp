package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.CalculatorButtons
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.MenuTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.OurAppsButtons
import com.brandoncano.resistancecalculator.ui.composables.RoundAppIcon
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

/**
 * Job: Controls the ui components and interactions of the home screen
 */

@Composable
fun HomeScreen(context: Context, navController: NavController) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context, navController)
        }
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuTopAppBar(stringResource(R.string.app_name), interactionSource) {
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }
        RoundAppIcon()
        CalculatorButtons(navController)
        OurAppsButtons(context)
    }
}

@AppScreenPreviews
@Composable
private fun HomePreview() {
    val app = HomeActivity()
    HomeScreen(app, NavController(app))
}

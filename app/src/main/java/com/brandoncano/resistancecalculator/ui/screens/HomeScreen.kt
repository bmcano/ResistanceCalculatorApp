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
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.composables.CalculatorButtons
import com.brandoncano.resistancecalculator.ui.composables.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.composables.MenuAppBar
import com.brandoncano.resistancecalculator.ui.composables.OurAppsButtons
import com.brandoncano.resistancecalculator.ui.composables.RevertToLegacyUI
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

@Composable
fun HomeScreen(context: Context, navController: NavController) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Content(context, navController)
        }
    }
}

@Composable
private fun Content(context: Context, navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuAppBar(stringResource(R.string.app_name), interactionSource) {
            FeedbackMenuItem(context, interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }
//        CircularAppIcon()
        CalculatorButtons(navController)
        OurAppsButtons(context)

        RevertToLegacyUI(context)
    }
}
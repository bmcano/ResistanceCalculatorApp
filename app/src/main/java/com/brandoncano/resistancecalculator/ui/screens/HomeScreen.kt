package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.ui.composables.CalculatorButtons
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        CircularAppIcon()
        CalculatorButtons(navController)
        OurAppsButtons(context)
        RevertToLegacyUI(context)
    }
}
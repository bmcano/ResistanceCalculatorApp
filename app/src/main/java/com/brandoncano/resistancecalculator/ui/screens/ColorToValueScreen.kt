package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.components.DropDownLists
import com.brandoncano.resistancecalculator.ui.components.DefaultCard
import com.brandoncano.resistancecalculator.ui.components.OutlinedDropDownMenu
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme

@Composable
fun ColorToValueScreen(context: Context, navController: NavController) {
    ResistanceCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) { Content(context, navController) }
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
        DefaultCard {
            OutlinedDropDownMenu("First Number", DropDownLists.numberItems)
            OutlinedDropDownMenu("Second Number", DropDownLists.numberItems)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
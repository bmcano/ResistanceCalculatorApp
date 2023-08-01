package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropDownLists
import com.brandoncano.resistancecalculator.ui.components.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.components.ClearSelectionsMenuItem
import com.brandoncano.resistancecalculator.ui.components.DefaultCard
import com.brandoncano.resistancecalculator.ui.components.FeedbackMenuItem
import com.brandoncano.resistancecalculator.ui.components.MenuAppBar
import com.brandoncano.resistancecalculator.ui.components.OutlinedDropDownMenu
import com.brandoncano.resistancecalculator.ui.components.Resistor
import com.brandoncano.resistancecalculator.ui.components.ShareMenuItem
import com.brandoncano.resistancecalculator.ui.components.ShowChartMenuItem
import com.brandoncano.resistancecalculator.ui.components.TextResistance
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme

@Composable
fun ColorToValueScreen(context: Context, navController: NavController) {
    ResistanceCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) { Content(context, navController) }
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
        MenuAppBar(
            titleText = stringResource(id = R.string.color_to_value) ,
            interactionSource = interactionSource
        ) {
            ShowChartMenuItem(interactionSource)
            ShareMenuItem(interactionSource)
            FeedbackMenuItem(context, interactionSource)
            ClearSelectionsMenuItem(interactionSource)
            AboutAppMenuItem(navController, interactionSource)
        }

        DefaultCard {
            OutlinedDropDownMenu("First Number", DropDownLists.numberItems)
            OutlinedDropDownMenu("Second Number", DropDownLists.numberItems)
            OutlinedDropDownMenu("Third Number", DropDownLists.numberItems)
            OutlinedDropDownMenu("Multiplier", DropDownLists.multiplierItems)
            OutlinedDropDownMenu("Tolerance", DropDownLists.toleranceItems)
            OutlinedDropDownMenu("Temp Coefficient", DropDownLists.ppmItems)
            Spacer(modifier = Modifier.height(16.dp))
        }

        DefaultCard {
            Resistor()
            TextResistance(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth(),
                text = "Select Color",
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // bottom navigation - for reference
        // https://github.com/stevdza-san/BottomNavBarDemo/tree/master/app/src/main/java/com/example/bottomnavbardemo
    }
}
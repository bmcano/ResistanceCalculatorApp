package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.components.ArrowButtonCard
import com.brandoncano.resistancecalculator.ui.components.MenuAppBar
import com.brandoncano.resistancecalculator.ui.navigation.Screen
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme

@Composable
fun HomeScreen(context: Context, navController: NavController) {

    val interactionSource = remember { MutableInteractionSource() }

    ResistanceCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column {
                MenuAppBar(
                    titleText = stringResource(id = R.string.app_name) ,
                    interactionSource = interactionSource
                ) {
                    
                }
                Spacer(modifier = Modifier.height(32.dp))
                //HomeScreenAppIcon()
                ArrowButtonCard(
                    listOf(Icons.Filled.KeyboardArrowRight, Icons.Filled.KeyboardArrowRight),
                    listOf("Color to Value", "Value to Color"),
                    listOf(
                        { navController.navigate(Screen.ColorToValue.route) },
                        { navController.navigate(Screen.ValueToColor.route) }
                    )
                )
            }
        }
    }
}
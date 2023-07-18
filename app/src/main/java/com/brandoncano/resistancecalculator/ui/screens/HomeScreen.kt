package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.ui.ColorToValueActivity
import com.brandoncano.resistancecalculator.ui.ValueToColorActivity
import com.brandoncano.resistancecalculator.ui.components.HomeAppBar
import com.brandoncano.resistancecalculator.ui.components.HomeScreenAppIcon
import com.brandoncano.resistancecalculator.ui.components.TextButton
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme

@Composable
fun HomeScreen(context: Context, navController: NavController) {
    ResistanceCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column {
                HomeAppBar(navController)
                Spacer(modifier = Modifier.height(32.dp))
                HomeScreenAppIcon()
                val buttonModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 64.dp, end = 64.dp, top = 32.dp)
                    .fillMaxWidth()
                TextButton(
                    modifier = buttonModifier,
                    text = "Color to Value",
                    onClick = { navColorToValue(context) })
                TextButton(
                    modifier = buttonModifier,
                    text = "Value to Color",
                    onClick = { navValueToColor(context) })
            }
        }
    }
}

private fun navColorToValue(context: Context) {
    val intent = Intent(context, ColorToValueActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    startActivity(context, intent, null)
}

private fun navValueToColor(context: Context) {
    val intent = Intent(context, ValueToColorActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    startActivity(context, intent, null)
}
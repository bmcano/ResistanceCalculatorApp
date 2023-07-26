package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.brandoncano.resistancecalculator.ui.ColorToValueActivity
import com.brandoncano.resistancecalculator.ui.HomeAppBar
import com.brandoncano.resistancecalculator.ui.HomeScreenAppIcon
import com.brandoncano.resistancecalculator.ui.HomeScreenButton
import com.brandoncano.resistancecalculator.ui.ValueToColorActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme

@Composable
fun HomeScreen(context: Context) {
    ResistanceCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column {
                HomeAppBar()
                Spacer(modifier = Modifier.padding(top = 16.dp))
                HomeScreenAppIcon()
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 32.dp, top = 64.dp, end = 32.dp)
                ) {
                    HomeScreenButton("Color to Value", onClick = { navColorToValue(context) })
                    Spacer(modifier = Modifier.padding(end = 32.dp))
                    HomeScreenButton("Value to Color", onClick = { navValueToColor(context) })
                }
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
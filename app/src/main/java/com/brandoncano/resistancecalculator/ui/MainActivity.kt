package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.brandoncano.resistancecalculator.navigation.Navigation
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResistorCalculatorTheme {
                Navigation(this)
            }
        }
    }
}

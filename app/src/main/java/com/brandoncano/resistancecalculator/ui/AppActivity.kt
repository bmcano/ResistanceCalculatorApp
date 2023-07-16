package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brandoncano.resistancecalculator.ui.screens.HomeScreen

/**
 * Job: Main app activity
 */
class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(this)
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    val app = AppActivity()
    HomeScreen(app)
}

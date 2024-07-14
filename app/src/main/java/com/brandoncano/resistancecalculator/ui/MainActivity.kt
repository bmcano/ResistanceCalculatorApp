package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.brandoncano.resistancecalculator.navigation.Navigation

/**
 * Job: Activity for the RCV calculator app.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(this)
        }
    }
}

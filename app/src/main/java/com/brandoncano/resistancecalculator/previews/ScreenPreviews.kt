package com.brandoncano.resistancecalculator.previews

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.screens.AboutScreen
import com.brandoncano.resistancecalculator.ui.screens.HomeScreen

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomePreview() {
    val app = HomeActivity()
    HomeScreen(app, NavController(app))
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AboutPreview() {
    val app = HomeActivity()
    AboutScreen(app)
}
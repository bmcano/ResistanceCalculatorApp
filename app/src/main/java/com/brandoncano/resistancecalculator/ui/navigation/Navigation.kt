package com.brandoncano.resistancecalculator.ui.navigation

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brandoncano.resistancecalculator.ui.AppActivity
import com.brandoncano.resistancecalculator.ui.screens.AboutScreen
import com.brandoncano.resistancecalculator.ui.screens.ColorToValueScreen
import com.brandoncano.resistancecalculator.ui.screens.HomeScreen

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(context, navController)
        }
        composable(route = Screen.ColorToValue.route) {
            ColorToValueScreen(context, navController)
        }
        composable(route = Screen.ValueToColor.route) {

        }
        composable(route = Screen.About.route) {
            AboutScreen(context)
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomePreview() {
    val app = AppActivity()
    HomeScreen(app, NavController(app))
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ColorToValuePreview() {
    val app = AppActivity()
    ColorToValueScreen(app, NavController(app))
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AboutPreview() {
    val app = AppActivity()
    AboutScreen(app)
}
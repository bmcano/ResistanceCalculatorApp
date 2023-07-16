package com.brandoncano.resistancecalculator.ui

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object ColorToValueScreen : Screen("color_to_value_screen")
    object ValueToColorScreen : Screen("value_to_color_screen")
    object AboutScreen : Screen("about screen")
}

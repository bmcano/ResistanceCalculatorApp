package com.brandoncano.resistancecalculator.ui

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object ColorToValue : Screen("color_to_value_screen")
    object ValueToColor : Screen("value_to_color_screen")
    object About : Screen("about screen")
}

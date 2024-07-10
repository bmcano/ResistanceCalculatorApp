package com.brandoncano.resistancecalculator.navigation

/**
 * Job: Holds the route for each different screen
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object ColorToValue: Screen("color_to_value_screen")
    data object ValueToColor: Screen("value_to_color_screen")
    data object About : Screen("about_screen")
}

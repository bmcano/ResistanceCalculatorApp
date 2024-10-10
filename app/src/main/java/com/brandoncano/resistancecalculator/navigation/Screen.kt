package com.brandoncano.resistancecalculator.navigation

/**
 * Note: Keep screens in alphabetical order
 */
sealed class Screen(val route: String) {
    data object About : Screen("about")
    data object ColorToValue: Screen("color_to_value")
    data object Home : Screen("home")
    data object LearnColorCodes : Screen("color_to_value/learn")
    data object LearnPreferredValues : Screen("value_to_color/learn")
    data object LearnSmdCodes : Screen("smd/learn")
    data object Smd: Screen("smd")
    data object ValueToColor: Screen("value_to_color")
    data object ViewOurApps : Screen("view_our_apps")
}

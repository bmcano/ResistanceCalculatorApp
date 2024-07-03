package com.brandoncano.resistancecalculator.ui.composables

import android.content.Context
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleBody
import com.brandoncano.resistancecalculator.util.EmailFeedback

/**
 * Note: Menu items are in alphabetical order
 */

@Composable
fun AboutAppMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_about),
            style = textStyleBody(),
        ) },
        onClick = { navController.navigate(Screen.About.route) },
        interactionSource = interactionSource,
    )
}

@Composable
fun ClearSelectionsMenuItem(interactionSource: MutableInteractionSource, onClick: (() -> Unit)) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_clear_selections),
            style = textStyleBody(),
        ) },
        onClick = onClick,
        interactionSource = interactionSource,
    )
}

@Composable
fun ColorToValueMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_color_to_value),
            style = textStyleBody(),
        ) },
        onClick = { navController.navigate(Screen.ColorToValue.route) },
        interactionSource = interactionSource,
    )
}

@Composable
fun FeedbackMenuItem(context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_feedback),
            style = textStyleBody(),
        ) },
        onClick = { EmailFeedback.execute(context) },
        interactionSource = interactionSource,
    )
}

@Composable
fun ShareMenuItem(context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.menu_share),
            style = textStyleBody(),
        ) },
        onClick = { /* ShareResistance.execute(context, ResistorCtv()) */ },
        interactionSource = interactionSource,
    )
}

@Composable
fun ValueToColorMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { Text(
            text = stringResource(R.string.manu_value_to_color),
            style = textStyleBody(),
        ) },
        onClick = { navController.navigate(Screen.ValueToColor.route) },
        interactionSource = interactionSource,
    )
}

@AppScreenPreviews
@Composable
fun MenuItemsPreview() {
    val interactionSource = remember { MutableInteractionSource() }
    val app = HomeActivity()
    ResistorCalculatorTheme {
        Column {
            AboutAppMenuItem(NavController(app), interactionSource)
            ClearSelectionsMenuItem(interactionSource) { }
            ColorToValueMenuItem(NavController(app), interactionSource)
            FeedbackMenuItem(app, interactionSource)
            ShareMenuItem(app, interactionSource)
            ValueToColorMenuItem(NavController(app), interactionSource)
        }
    }
}

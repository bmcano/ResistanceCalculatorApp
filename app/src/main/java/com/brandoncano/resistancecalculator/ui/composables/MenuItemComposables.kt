package com.brandoncano.resistancecalculator.ui.composables

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleBody
import com.brandoncano.resistancecalculator.util.EmailFeedback
import com.brandoncano.resistancecalculator.util.ShareResistance

/**
 * Note: Menu items are in alphabetical order
 */

@Composable
fun AboutAppMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_about) },
        onClick = { navController.navigate(Screen.About.route) },
        leadingIcon = { MenuIcon(Icons.Outlined.Info) },
        interactionSource = interactionSource,
    )
}

@Composable
fun ClearSelectionsMenuItem(interactionSource: MutableInteractionSource, onClick: (() -> Unit)) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_clear_selections) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Cancel) },
        interactionSource = interactionSource,
    )
}

@Composable
fun ColorToValueMenuItem(
    navController: NavController,
    interactionSource: MutableInteractionSource
) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_color_to_value) },
        onClick = {
            navController.navigate(Screen.ColorToValue.route) {
                popUpTo(Screen.Home.route)
            }
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Colorize) },
        interactionSource = interactionSource,
    )
}

@Composable
fun FeedbackMenuItem(context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_feedback) },
        onClick = { EmailFeedback.execute(context) },
        leadingIcon = { MenuIcon(Icons.Outlined.Feedback) },
        interactionSource = interactionSource,
    )
}

@Composable
fun ShareMenuItem(context: Context, text: String, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share) },
        onClick = { ShareResistance.execute(context, text) },
        leadingIcon = { MenuIcon(Icons.Outlined.Share) },
        interactionSource = interactionSource,
    )
}

@Composable
fun ValueToColorMenuItem(
    navController: NavController,
    interactionSource: MutableInteractionSource
) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_value_to_color) },
        onClick = {
            navController.navigate(Screen.ValueToColor.route) {
                popUpTo(Screen.Home.route)
            }
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Search) },
        interactionSource = interactionSource,
    )
}

@Composable
private fun MenuText(@StringRes stringRes: Int) {
    Text(
        text = stringResource(id = stringRes),
        style = textStyleBody(),
    )
}

@Composable
private fun MenuIcon(imageVector: ImageVector) {
    Image(
        imageVector = imageVector,
        contentDescription = null,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
    )
}

@AppComponentPreviews
@Composable
private fun MenuItemsPreview() {
    val interactionSource = remember { MutableInteractionSource() }
    val app = MainActivity()
    ResistorCalculatorTheme {
        Column {
            AboutAppMenuItem(NavController(app), interactionSource)
            ClearSelectionsMenuItem(interactionSource) { }
            ColorToValueMenuItem(NavController(app), interactionSource)
            FeedbackMenuItem(app, interactionSource)
            ShareMenuItem(app, "text", interactionSource)
            ValueToColorMenuItem(NavController(app), interactionSource)
        }
    }
}

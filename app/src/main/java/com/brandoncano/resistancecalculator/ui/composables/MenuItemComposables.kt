package com.brandoncano.resistancecalculator.ui.composables

import android.content.Context
import android.graphics.Picture
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.menuText
import com.brandoncano.resistancecalculator.ui.theme.textStyleBody
import com.brandoncano.resistancecalculator.util.ComposableToBitmap
import com.brandoncano.resistancecalculator.util.EmailFeedback
import com.brandoncano.resistancecalculator.util.SaveBitmap
import com.brandoncano.resistancecalculator.util.ShareResistance
import com.brandoncano.resistancecalculator.util.ShareResistor

/**
 * Note: Menu items are in alphabetical order
 */

@Composable
fun AboutAppMenuItem(navController: NavController, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_about) },
        onClick = {
            showMenu.value = false
            navController.navigate(Screen.About.route)
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Info) },
    )
}

@Composable
fun ClearSelectionsMenuItem(onClick: (() -> Unit)) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_clear_selections) },
        onClick = onClick,
        leadingIcon = { MenuIcon(Icons.Outlined.Cancel) },
    )
}

@Composable
fun ColorToValueMenuItem(navController: NavController, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_color_to_value) },
        onClick = {
            showMenu.value = false
            navController.navigate(Screen.ColorToValue.route) {
                popUpTo(Screen.Home.route)
            }
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Colorize) },
    )
}

@Composable
fun FeedbackMenuItem(context: Context, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_feedback) },
        onClick = {
            showMenu.value = false
            EmailFeedback.execute(context)
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Feedback) },
    )
}

@Composable
fun ShareImageMenuItem(context: Context, showMenu: MutableState<Boolean>, picture: Picture) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share_image) },
        onClick = {
            showMenu.value = false
            val bitmap = ComposableToBitmap.execute(picture)
            val uri = SaveBitmap.execute(bitmap, context) ?: return@DropdownMenuItem
            ShareResistor.execute(uri, context)
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Image) },
    )
}

@Composable
fun ShareTextMenuItem(context: Context, text: String, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_share_text) },
        onClick = {
            showMenu.value = false
            ShareResistance.execute(context, text)
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Share) },
    )
}

@Composable
fun ValueToColorMenuItem(navController: NavController, showMenu: MutableState<Boolean>) {
    DropdownMenuItem(
        text = { MenuText(stringRes = R.string.menu_value_to_color) },
        onClick = {
            showMenu.value = false
            navController.navigate(Screen.ValueToColor.route) {
                popUpTo(Screen.Home.route)
            }
        },
        leadingIcon = { MenuIcon(Icons.Outlined.Search) },
    )
}

@Composable
private fun MenuText(@StringRes stringRes: Int) {
    Text(
        text = stringResource(id = stringRes),
        style = textStyleBody().menuText(),
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
    val showMenu = remember { mutableStateOf(false) }
    val app = MainActivity()
    ResistorCalculatorTheme {
        Column {
            AboutAppMenuItem(NavController(app), showMenu)
            ClearSelectionsMenuItem { }
            ColorToValueMenuItem(NavController(app), showMenu)
            FeedbackMenuItem(app, showMenu)
            ShareTextMenuItem(app, "text", showMenu)
            ValueToColorMenuItem(NavController(app), showMenu)
        }
    }
}

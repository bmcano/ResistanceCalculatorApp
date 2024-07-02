package com.brandoncano.resistancecalculator.ui.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.EmailFeedback

/**
 * Job: Hold all composables for the top app bars and menu items
 */

@Composable
fun MenuTopAppBar(
    titleText: String,
    interactionSource: MutableInteractionSource,
    content: @Composable (ColumnScope.() -> Unit)
) {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                expanded = false
            }
        }
    }
    DefaultAppBar(titleText) {
        IconButton(
            onClick = { expanded = !expanded },
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = stringResource(R.string.content_description_menu_more)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            content = content,
        )
    }
}

@Composable
fun TitleTopAppBar(
    titleText: String,
) {
    DefaultAppBar(titleText)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultAppBar(
    titleText: String,
    actions: @Composable (RowScope.() -> Unit) = { }
) {
    val colors = centerAlignedTopAppBarColors(
        containerColor = colorScheme.primary,
        navigationIconContentColor = colorScheme.onPrimary,
        titleContentColor = colorScheme.onPrimary,
        actionIconContentColor = colorScheme.onPrimary
    )
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titleText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = actions,
        colors = colors,
    )
    BottomShadow()
}

@Composable
fun BottomShadow(alpha: Float = 0.1f, height: Dp = 4.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Black.copy(alpha), Color.Transparent)
                )
            )
    )
}

// menu items are in alphabetical order
@Composable
fun AboutAppMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { TextBody(text = stringResource(R.string.menu_about)) },
        onClick = { navController.navigate(Screen.About.route) },
        interactionSource = interactionSource,
    )
}

@Composable
fun ClearSelectionsMenuItem(interactionSource: MutableInteractionSource, onClick: (() -> Unit)) {
    DropdownMenuItem(
        text = { TextBody(text = stringResource(R.string.menu_clear_selections)) },
        onClick = onClick,
        interactionSource = interactionSource,
    )
}

@Composable
fun ColorToValueMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { TextBody(text = stringResource(R.string.menu_color_to_value)) },
        onClick = { navController.navigate(Screen.ColorToValue.route) },
        interactionSource = interactionSource,
    )
}

@Composable
fun FeedbackMenuItem(context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { TextBody(text = stringResource(R.string.menu_feedback)) },
        onClick = { EmailFeedback.execute(context) },
        interactionSource = interactionSource,
    )
}

@Composable
fun ShareMenuItem(context: Context, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { TextBody(text = stringResource(R.string.menu_share)) },
        onClick = { /* ShareResistance.execute(context, ResistorCtv()) */ },
        interactionSource = interactionSource,
    )
}

@Composable
fun ValueToColorMenuItem(navController: NavController, interactionSource: MutableInteractionSource) {
    DropdownMenuItem(
        text = { TextBody(text = stringResource(R.string.manu_value_to_color)) },
        onClick = { navController.navigate(Screen.ValueToColor.route) },
        interactionSource = interactionSource,
    )
}

@AppScreenPreviews
@Composable
fun TitleTopAppBarPreview() {
    ResistorCalculatorTheme {
        TitleTopAppBar("TitleTopAppBar")
    }
}

@AppScreenPreviews
@Composable
fun MenuTopAppBarPreview() {
    val interactionSource = remember { MutableInteractionSource() }
    ResistorCalculatorTheme {
        MenuTopAppBar("TitleTopAppBar", interactionSource) {
            ClearSelectionsMenuItem(interactionSource) { }
        }
    }
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

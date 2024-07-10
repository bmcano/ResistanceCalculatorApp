package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
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
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleLargeTitle

@Composable
fun RcvMenuTopAppBar(
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
    RcvTopAppBar(titleText) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RcvTopAppBar(
    titleText: String,
    actions: @Composable (RowScope.() -> Unit) = { }
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titleText,
                style = textStyleLargeTitle(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        actions = actions,
        colors = centerAlignedTopAppBarColors(
            containerColor = colorScheme.primary,
            navigationIconContentColor = colorScheme.onPrimary,
            titleContentColor = colorScheme.onPrimary,
            actionIconContentColor = colorScheme.onPrimary
        ),
    )
    BottomShadow()
}

@Composable
private fun BottomShadow(alpha: Float = 0.1f, height: Dp = 4.dp) {
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

@AppScreenPreviews
@Composable
fun TitleTopAppBarPreview() {
    ResistorCalculatorTheme {
        RcvTopAppBar("TopAppBar")
    }
}

@AppScreenPreviews
@Composable
fun MenuTopAppBarPreview() {
    val interactionSource = remember { MutableInteractionSource() }
    ResistorCalculatorTheme {
        RcvMenuTopAppBar("MenuTopAppBar", interactionSource) {
            ClearSelectionsMenuItem(interactionSource) { }
        }
    }
}

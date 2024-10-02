package com.brandoncano.resistancecalculator.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.WidthFull
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Symbols
import com.brandoncano.resistancecalculator.ui.composables.AboutAppMenuItem
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppMenuTopAppBar
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.FeedbackMenuItem
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.textStyleHeadline

@Composable
fun HomeScreen(
    openMenu: MutableState<Boolean>,
    onAboutTapped: () -> Unit,
    onColorToValueTapped: () -> Unit,
    onValueToColorTapped: () -> Unit,
    onSmdTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        HomeScreenContent(
            openMenu = openMenu,
            onAboutTapped = onAboutTapped,
            onColorToValueTapped = onColorToValueTapped,
            onValueToColorTapped = onValueToColorTapped,
            onSmdTapped = onSmdTapped,
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
        )
    }
}

@Composable
private fun HomeScreenContent(
    openMenu: MutableState<Boolean>,
    onAboutTapped: () -> Unit,
    onColorToValueTapped: () -> Unit,
    onValueToColorTapped: () -> Unit,
    onSmdTapped: () -> Unit,
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppMenuTopAppBar(
            titleText = stringResource(R.string.app_name),
            interactionSource = remember { MutableInteractionSource() },
            showMenu = openMenu,
        ) {
            FeedbackMenuItem(context, Symbols.APP_NAME, openMenu)
            AboutAppMenuItem(onAboutTapped)
        }

        AppIcon()
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.home_calculators_header_text),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Colorize,
                text = stringResource(id = R.string.home_button_color_to_value),
                onClick = onColorToValueTapped
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Search,
                text = stringResource(id = R.string.home_button_value_to_color),
                onClick = onValueToColorTapped
            ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.WidthFull,
                text = stringResource(id = R.string.home_button_smd),
                onClick = onSmdTapped
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        OurAppsButtons(
            onRateThisAppTapped = onRateThisAppTapped,
            onViewOurAppsTapped = onViewOurAppsTapped,
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun AppIcon() {
    val backgroundColor = if (isSystemInDarkTheme()) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.primary
    }
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .size(128.dp),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.content_description_app_icon),
                modifier = Modifier.size(128.dp)
            )
        }
    }
}

@AppScreenPreviews
@Composable
private fun HomePreview() {
    ResistorCalculatorTheme {
        HomeScreen(
            openMenu = remember { mutableStateOf(false) },
            onAboutTapped = {},
            onColorToValueTapped = {},
            onValueToColorTapped = {},
            onSmdTapped = {},
            onRateThisAppTapped = {},
            onViewOurAppsTapped = {},
        )
    }
}

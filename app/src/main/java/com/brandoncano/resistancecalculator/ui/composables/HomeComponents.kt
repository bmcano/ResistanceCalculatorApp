package com.brandoncano.resistancecalculator.ui.composables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.AddToHomeScreen
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleHeadline
import com.brandoncano.resistancecalculator.util.OpenLink

/**
 * Job: Components for the home screen
 */

@Composable
fun RoundAppIcon() {
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        val backgroundColor = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            MaterialTheme.colorScheme.primary
        }
        Box(
            modifier = Modifier
                .size(196.dp)
                .clip(CircleShape)
                .background(color = backgroundColor),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.content_description_app_icon),
                modifier = Modifier.size(196.dp)
            )
        }
    }
}

@Composable
fun CalculatorButtons(navController: NavController) {
    Column {
        Text(
            text = stringResource(id = R.string.home_calculators_header_text),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        ArrowButtonCard(
            listOf(
                Icons.Outlined.Calculate,
                Icons.Outlined.Calculate
            ),
            listOf(
                stringResource(id = R.string.home_button_color_to_value),
                stringResource(id = R.string.home_button_value_to_color)
            ),
            listOf(
                { navController.navigate(Screen.ColorToValue.route) },
                { navController.navigate(Screen.ValueToColor.route) }
            ),
        )
    }
}

@Composable
fun OurAppsButtons(context: Context) {
    Column {
        val modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .align(Alignment.Start)
        Text(
            text = stringResource(id = R.string.home_our_apps_header_text),
            modifier = modifier,
            style = textStyleHeadline(),
        )
        ArrowButtonCard(
            listOf(
                Icons.Outlined.Star,
                Icons.AutoMirrored.Outlined.AddToHomeScreen
            ),
            listOf(
                stringResource(id = R.string.home_button_rate_us),
                stringResource(id = R.string.home_button_view_capacitor_app)
            ),
            listOf(
                { OpenLink.openResistorApp(context) },
                { OpenLink.openCapacitorApp(context) }
            ),
        )
    }
}

@AppScreenPreviews
@Composable
fun AppIconPreview() {
    ResistorCalculatorTheme {
        RoundAppIcon()
    }
}

package com.brandoncano.resistancecalculator.ui.screens.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.AddToHomeScreen
import androidx.compose.material.icons.outlined.Colorize
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.WidthFull
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.composables.AppComponentPreviews
import com.brandoncano.resistancecalculator.ui.composables.ArrowButtonCard
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleHeadline
import com.brandoncano.resistancecalculator.util.external.OpenLink

@Composable
fun AppIcon() {
    val backgroundColor = if (isSystemInDarkTheme()) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.primary
    }
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .size(128.dp),
        shape = RoundedCornerShape(16.dp),
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

@Composable
fun AppCalculatorButtons(navController: NavController) {
    Column {
        Text(
            text = stringResource(id = R.string.home_calculators_header_text),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        ArrowButtonCard(
            listOf(
                Icons.Outlined.Colorize,
                Icons.Outlined.Search
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
        ArrowButtonCard(
            Icons.Outlined.WidthFull,
            stringResource(id = R.string.home_button_smd),
        ) {
            navController.navigate(Screen.Smd.route)
        }
    }
}

@Composable
fun OurAppsButtons(context: Context) {
    Column {
        Text(
            text = stringResource(id = R.string.home_our_apps_header_text),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        ArrowButtonCard(
            listOf(
                Icons.Outlined.Grade,
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

@AppComponentPreviews
@Composable
private fun AppIconPreview() {
    ResistorCalculatorTheme {
        AppIcon()
    }
}

@AppComponentPreviews
@Composable
private fun StandardCalculatorButtonsPreview() {
    ResistorCalculatorTheme {
        val app = MainActivity()
        AppCalculatorButtons(NavController(app))
    }
}

@AppComponentPreviews
@Composable
private fun OurAppsButtonsPreview() {
    ResistorCalculatorTheme {
        val app = MainActivity()
        OurAppsButtons(app)
    }
}

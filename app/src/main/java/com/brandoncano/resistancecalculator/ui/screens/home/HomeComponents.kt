package com.brandoncano.resistancecalculator.ui.screens.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
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
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.navigation.Screen
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.utils.OpenLink

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

@Composable
fun AppCalculatorButtons(navController: NavController) {
    Column {
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
                onClick = { navController.navigate(Screen.ColorToValue.route) }
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Search,
                text = stringResource(id = R.string.home_button_value_to_color),
                onClick = { navController.navigate(Screen.ValueToColor.route) }
            ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.WidthFull,
                text = stringResource(id = R.string.home_button_smd),
                onClick = { navController.navigate(Screen.Smd.route) }
            )
        )
    }
}

@Composable
fun OurAppsButtons(context: Context, navController: NavController) {
    Column {
        Text(
            text = stringResource(id = R.string.home_our_apps_header_text),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Grade,
                text = stringResource(id = R.string.home_button_rate_us),
                onClick = { OpenLink.execute(context, Links.RESISTOR_PLAYSTORE) }
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Apps,
                text = stringResource(id = R.string.home_button_view_apps),
                onClick = { navController.navigate(Screen.ViewOurApps.route) }
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
        OurAppsButtons(app, NavController(app))
    }
}

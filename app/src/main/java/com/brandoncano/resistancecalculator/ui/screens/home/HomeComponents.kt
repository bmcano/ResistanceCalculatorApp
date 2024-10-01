package com.brandoncano.resistancecalculator.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.textStyleHeadline

@Composable
fun OurAppsButtons(
    onRateThisAppTapped: () -> Unit,
    onViewOurAppsTapped: () -> Unit,
) {
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
                onClick = onRateThisAppTapped,
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Apps,
                text = stringResource(id = R.string.home_button_view_apps),
                onClick = onViewOurAppsTapped,
            ),
        )
    }
}

@AppComponentPreviews
@Composable
private fun OurAppsButtonsPreview() {
    ResistorCalculatorTheme {
        Surface {
            OurAppsButtons(
                onRateThisAppTapped = {},
                onViewOurAppsTapped = {},
            )
        }
    }
}

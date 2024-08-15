package com.brandoncano.resistancecalculator.ui.screens.about

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Links
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppArrowCardButton
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.composables.AppStandardCard
import com.brandoncano.sharedcomponents.data.ArrowCardButtonContents
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.utils.OpenLink

@Composable
fun ViewPrivacyPolicy(context: Context) {
    AppArrowCardButton(
        ArrowCardButtonContents(
            imageVector = Icons.Outlined.FileOpen,
            text = stringResource(id = R.string.about_view_privacy_policy),
            onClick = { OpenLink.execute(context, Links.PRIVACY_POLICY) },
        )
    )
}

@AppComponentPreviews
@Composable
fun DescriptionCard() {
    Text(
        text = stringResource(id = R.string.about_description),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp),
        style = textStyleHeadline(),
    )
    AppStandardCard {
        Text(
            text = stringResource(id = R.string.about_description_01),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleBody(),
        )
        Text(
            text = stringResource(id = R.string.about_description_02),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            style = textStyleBody(),
        )
    }
}

@Composable
fun ViewIecStandard(context: Context) {
    Column {
        Text(
            text = stringResource(id = R.string.about_iec_header_text),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        AppArrowCardButton(
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Link,
                text = stringResource(id = R.string.about_standard_iec_button),
                onClick = { OpenLink.execute(context, Links.COLOR_IEC) },
            ),
            ArrowCardButtonContents(
                imageVector = Icons.Outlined.Link,
                text = stringResource(id = R.string.about_smd_iec_button),
                onClick = { OpenLink.execute(context, Links.SMD_IEC) },
            ),
        )
    }
}

@AppComponentPreviews
@Composable
private fun IecStandardPreview() {
    ResistorCalculatorTheme {
        Column {
            val context = MainActivity()
            ViewIecStandard(context)
        }
    }
}

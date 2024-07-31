package com.brandoncano.resistancecalculator.ui.screens.about

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.composables.AppComponentPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppDivider
import com.brandoncano.resistancecalculator.ui.composables.AppStandardCard
import com.brandoncano.resistancecalculator.ui.composables.ArrowButtonCard
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleBody
import com.brandoncano.resistancecalculator.ui.theme.textStyleHeadline
import com.brandoncano.resistancecalculator.util.OpenLink

@Composable
fun AuthorCard() {
    AppStandardCard {
        HeadlineBodyStack(
            label = R.string.about_created_by,
            body = R.string.about_author,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun AppInfoCard() {
    AppStandardCard {
        HeadlineBodyStack(
            label = R.string.about_app_version,
            body = R.string.version,
        )
        AppDivider(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp))
        HeadlineBodyStack(
            label = R.string.about_last_updated_on,
            body = R.string.last_updated,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ViewPrivacyPolicy(context: Context) {
    ArrowButtonCard(
        Icons.Outlined.FileOpen,
        stringResource(id = R.string.about_view_privacy_policy),
    ) {
        OpenLink.openPrivacyPolicy(context)
    }
}

@Composable
fun DescriptionCard() {
    Text(
        text = stringResource(id = R.string.about_description),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp),
        style = textStyleHeadline(),
    )
    AppStandardCard {
        Text(
            text = stringResource(id = R.string.about_description_one),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleBody(),
        )
        Text(
            text = stringResource(id = R.string.about_description_two),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleBody(),
        )
        Text(
            text = stringResource(id = R.string.about_description_three),
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
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .align(Alignment.Start),
            style = textStyleHeadline(),
        )
        ArrowButtonCard(
            listOf(
                Icons.Filled.Link,
                Icons.Filled.Link
            ),
            listOf(
                stringResource(id = R.string.about_standard_iec_button),
                stringResource(id = R.string.about_smd_iec_button)
            ),
            listOf(
                { OpenLink.openColorIECWebpage(context) },
                { OpenLink.openSmdIECWebpage(context) }
            ),
        )
    }
}

@Composable
fun HeadlineBodyStack(@StringRes label: Int, @StringRes body: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = label),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(id = body),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp),
            style = textStyleBody(),
        )
    }
}

@AppComponentPreviews
@Composable
private fun HeadlineBodyStackPreview() {
    ResistorCalculatorTheme {
        Column(
            modifier = Modifier.height(64.dp)
        ) {
            HeadlineBodyStack(
                label = R.string.about_created_by,
                body = R.string.about_author,
            )
        }
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

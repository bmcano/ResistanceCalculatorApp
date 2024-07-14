package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.composables.AppDivider
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppStandardCard
import com.brandoncano.resistancecalculator.ui.components.HeadlineBodyStack
import com.brandoncano.resistancecalculator.ui.components.OurAppsButtons
import com.brandoncano.resistancecalculator.ui.composables.AppTopAppBar
import com.brandoncano.resistancecalculator.ui.components.ViewIecStandard
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleBody
import com.brandoncano.resistancecalculator.ui.theme.textStyleHeadline

@Composable
fun AboutScreen(context: Context) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ContentView(context)
        }
    }
}

@Composable
private fun ContentView(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        AppTopAppBar(stringResource(R.string.about_title))
        AuthorCard()
        AppInfoCard()
        DescriptionCard()
        ViewIecStandard(context)
        OurAppsButtons(context)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun AuthorCard() {
    AppStandardCard {
        HeadlineBodyStack(
            label = R.string.about_created_by,
            body = R.string.about_author,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun AppInfoCard() {
    AppStandardCard {
        HeadlineBodyStack(
            label = R.string.about_app_version,
            body = R.string.version,
        )
        AppDivider(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )
        HeadlineBodyStack(
            label = R.string.about_last_updated_on,
            body = R.string.last_updated,
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun DescriptionCard() {
    val modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)
    AppStandardCard {
        Text(
            text = stringResource(id = R.string.about_description),
            modifier = modifier,
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(id = R.string.about_description_one),
            modifier = modifier,
            style = textStyleBody(),
        )
        Text(
            text = stringResource(id = R.string.about_description_two),
            modifier = modifier,
            style = textStyleBody(),
        )
        Text(
            text = stringResource(id = R.string.about_description_three),
            modifier = modifier.padding(bottom = 12.dp),
            style = textStyleBody(),
        )
    }
}

@AppScreenPreviews
@Composable
private fun AboutPreview() {
    val app = MainActivity()
    AboutScreen(app)
}

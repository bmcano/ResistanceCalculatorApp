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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.composables.AppDivider
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.DefaultCard
import com.brandoncano.resistancecalculator.ui.composables.LabelBodyText
import com.brandoncano.resistancecalculator.ui.composables.LabelBodyTextCard
import com.brandoncano.resistancecalculator.ui.composables.OurAppsButtons
import com.brandoncano.resistancecalculator.ui.composables.TextBody
import com.brandoncano.resistancecalculator.ui.composables.TextLabel
import com.brandoncano.resistancecalculator.ui.composables.TitleTopAppBar
import com.brandoncano.resistancecalculator.ui.composables.ViewIecStandard
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

/**
 * Job: Hold all the UI interaction and components for the about screen
 */

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
        TitleTopAppBar(stringResource(R.string.about_title))

        val modifier = Modifier
            .align(Alignment.Start)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)

        LabelBodyTextCard(
            label = R.string.about_created_by,
            body = R.string.about_author,
        )

        DefaultCard {
            LabelBodyText(
                label = R.string.about_app_version,
                body = R.string.version,
            )
            AppDivider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
            LabelBodyText(
                label = R.string.about_last_updated_on,
                body = R.string.last_updated,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        DefaultCard {
            TextLabel(
                modifier = modifier,
                text = stringResource(id = R.string.about_description)
            )
            TextBody(
                modifier = modifier,
                text = stringResource(id = R.string.description_one)
            )
            TextBody(
                modifier = modifier,
                text = stringResource(id = R.string.description_two)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        ViewIecStandard(context)
        OurAppsButtons(context)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@AppScreenPreviews
@Composable
private fun AboutPreview() {
    val app = HomeActivity()
    AboutScreen(app)
}

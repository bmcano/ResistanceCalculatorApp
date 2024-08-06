package com.brandoncano.resistancecalculator.ui.screens.about

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.screens.home.OurAppsButtons
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppTopAppBar
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

@Composable
fun AboutScreen(context: Context) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ContentView(context)
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
        ViewPrivacyPolicy(context)
        DescriptionCard()
        ViewIecStandard(context)
        OurAppsButtons(context)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun AboutPreview() {
    val app = MainActivity()
    ResistorCalculatorTheme {
        AboutScreen(app)
    }
}

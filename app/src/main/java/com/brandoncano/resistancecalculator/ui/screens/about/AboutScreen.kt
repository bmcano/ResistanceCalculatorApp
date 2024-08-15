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
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.ui.screens.home.OurAppsButtons
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.screen.AppInfoCard
import com.brandoncano.sharedcomponents.screen.AuthorCard

@Composable
fun AboutScreen(context: Context, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ContentView(context, navController)
    }
}

@Composable
private fun ContentView(context: Context, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        AppTopAppBar(stringResource(R.string.about_title))
        AuthorCard()
        AppInfoCard(R.string.version, R.string.last_updated)
        ViewPrivacyPolicy(context)
        DescriptionCard()
        ViewIecStandard(context)
        OurAppsButtons(context, navController)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun AboutPreview() {
    val app = MainActivity()
    ResistorCalculatorTheme {
        AboutScreen(app, NavController(app))
    }
}

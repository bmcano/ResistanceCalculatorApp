package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.data.ESeries
import com.brandoncano.sharedcomponents.composables.AppDivider
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleLargeTitle

@Composable
fun LearnPreferredValuesScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopAppBar(
                titleText = stringResource(R.string.info_title_3),
                navigationIcon =  Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
            )
        },
        contentWindowInsets = WindowInsets(0),
    ) { paddingValues ->
        LearnPreferredValuesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnPreferredValuesScreenContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = stringResource(R.string.info_intro_title),
            modifier = Modifier.padding(vertical = 12.dp),
            style = textStyleLargeTitle(),
        )
        Text(
            text = stringResource(R.string.info_values_intro_body),
            modifier = Modifier.padding(bottom = 32.dp),
            style = textStyleBody().onSurfaceVariant(),
        )

        Text(
            text = stringResource(R.string.info_values_preferred_values_title),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleLargeTitle(),
        )
        Text(
            text = stringResource(R.string.info_values_preferred_values_body),
            modifier = Modifier.padding(bottom = 24.dp),
            style = textStyleBody().onSurfaceVariant(),
        )

        Text(
            text = stringResource(R.string.info_values_preferred_values_low_precision),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        ESeriesTable(stringResource(R.string.info_values_e6_header), ESeries.E6)
        AppDivider(modifier = Modifier.padding(vertical = 16.dp))

        Text(
            text = stringResource(R.string.info_values_preferred_values_medium_precision),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        ESeriesTable(stringResource(R.string.info_values_e12_header), ESeries.E12)
        Spacer(modifier = Modifier.height(12.dp))
        ESeriesTable(stringResource(R.string.info_values_e24_header), ESeries.E24)
        AppDivider(modifier = Modifier.padding(vertical = 16.dp))

        Text(
            text = stringResource(R.string.info_values_preferred_values_high_precision),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        ESeriesTable(stringResource(R.string.info_values_e48_header), ESeries.E48)
        Spacer(modifier = Modifier.height(12.dp))
        ESeriesTable(stringResource(R.string.info_values_e96_header), ESeries.E96)
        Spacer(modifier = Modifier.height(12.dp))
        ESeriesTable(stringResource(R.string.info_values_e192_header), ESeries.E192)

        DisclaimerText()
        Spacer(modifier = Modifier.height(48.dp))
    }
}

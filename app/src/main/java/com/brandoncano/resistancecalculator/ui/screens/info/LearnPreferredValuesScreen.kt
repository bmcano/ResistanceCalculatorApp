package com.brandoncano.resistancecalculator.ui.screens.info

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.brandoncano.resistancecalculator.R
import com.brandoncano.sharedcomponents.composables.AppTopAppBar

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
    // TODO
}
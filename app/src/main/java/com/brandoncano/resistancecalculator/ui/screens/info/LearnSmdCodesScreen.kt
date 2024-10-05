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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.sharedcomponents.composables.AppScreenPreviews
import com.brandoncano.sharedcomponents.composables.AppTopAppBar
import com.brandoncano.sharedcomponents.text.onSurfaceVariant
import com.brandoncano.sharedcomponents.text.textStyleBody
import com.brandoncano.sharedcomponents.text.textStyleCallout
import com.brandoncano.sharedcomponents.text.textStyleHeadline
import com.brandoncano.sharedcomponents.text.textStyleLargeTitle
import com.brandoncano.sharedcomponents.text.textStyleTitle

/**
 * Note: Information originated from: https://eepower.com/resistor-guide/resistor-standards-and-codes/resistor-smd-code/
 */

@Composable
fun LearnSmdCodesScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppTopAppBar(
                titleText = stringResource(R.string.info_title),
                navigationIcon =  Icons.Filled.Close,
                onNavigateBack = onNavigateBack,
            )
        },
        contentWindowInsets = WindowInsets(0),
    ) { paddingValues ->
        LearnSmdCodesScreenContent(paddingValues)
    }
}

@Composable
private fun LearnSmdCodesScreenContent(
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(R.string.info_smd_overview_title),
            modifier = Modifier.padding(vertical = 12.dp),
            style = textStyleLargeTitle(),
        )
        Text(
            text = stringResource(R.string.info_smd_overview_body),
            modifier = Modifier.padding(bottom = 32.dp),
            style = textStyleBody().onSurfaceVariant(),
        )

        Text(
            text = stringResource(R.string.info_smd_resistor_codes_title),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleLargeTitle(),
        )
        Text(
            text = stringResource(R.string.info_smd_resistor_codes_body),
            modifier = Modifier.padding(bottom = 24.dp),
            style = textStyleBody().onSurfaceVariant(),
        )

        Text(
            text = stringResource(R.string.info_smd_three_code_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleTitle(),
        )
        Text(
            text = stringResource(R.string.info_smd_three_code_body),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeExampleCard(
            code = "Code: xyz",
            description = stringResource(R.string.info_smd_three_digit_formula),
        )
        Text(
            text = stringResource(R.string.info_smd_code_example),
            modifier = Modifier.padding(vertical = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeExampleCard(
            code = "Code: 472",
            description = stringResource(R.string.info_smd_three_digit_example),
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.info_smd_four_code_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleTitle(),
        )
        Text(
            text = stringResource(R.string.info_smd_four_code_body),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeExampleCard(
            code = "Code: wxyz",
            description = stringResource(R.string.info_smd_four_digit_formula),
        )
        Text(
            text = stringResource(R.string.info_smd_code_example),
            modifier = Modifier.padding(vertical = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeExampleCard(
            code = "Code: 1201",
            description = stringResource(R.string.info_smd_four_digit_example),
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        )
        Text(
            text = stringResource(R.string.info_smd_four_digit_body2),
            modifier = Modifier.padding(bottom = 24.dp),
            style = textStyleCallout().onSurfaceVariant(),
        )

        Text(
            text = stringResource(R.string.info_smd_eia_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleTitle(),
        )
        Text(
            text = stringResource(R.string.info_smd_eia_body),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeExampleCard(
            code = "Code: 43C",
            description = stringResource(R.string.info_smd_eia_example)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.info_smd_multipliers_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(R.string.info_smd_multipliers_body),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        MultiplierTable()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.info_smd_code_value_headline),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(R.string.info_smd_code_value_body),
            modifier = Modifier.padding(bottom = 12.dp),
            style = textStyleBody().onSurfaceVariant(),
        )
        CodeValueTable(codeValueList)
        DisclaimerText()
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@AppScreenPreviews
@Composable
private fun InfoPreview() {
    ResistorCalculatorTheme {
        LearnSmdCodesScreen {  }
    }
}

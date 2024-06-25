package com.brandoncano.resistancecalculator.ui.composables

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.OpenLink

/**
 * Job: Holds all the components for the about screen
 */

@Composable
fun LabelBodyText(@StringRes label: Int, @StringRes body: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        val modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        TextLabel(
            modifier = modifier.padding(top = 16.dp),
            text = stringResource(id = label)
        )
        TextBody(
            modifier = modifier.padding(top = 4.dp),
            text = stringResource(id = body)
        )
    }
}

@Composable
fun LabelBodyTextCard(@StringRes label: Int, @StringRes body: Int) {
    DefaultCard {
        LabelBodyText(label, body)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ViewIecStandard(context: Context) {
    ArrowButtonCard(
        Icons.Filled.Link,
        stringResource(id = R.string.iec_button_text)
    ) {
        OpenLink.openIECWebpage(context)
    }
}

@AppScreenPreviews
@Composable
private fun LabelBodyTextPreview() {
    ResistorCalculatorTheme {
        Column(
            modifier = Modifier.height(64.dp)
        ) {
            LabelBodyText(
                label = R.string.about_created_by,
                body = R.string.about_author,
            )
        }
    }
}

@AppScreenPreviews
@Composable
private fun LabelBodyTextCardPreview() {
    ResistorCalculatorTheme {
        Column(
            modifier = Modifier.height(82.dp)
        ) {
            LabelBodyTextCard(
                label = R.string.about_created_by,
                body = R.string.about_author,
            )
        }
    }
}

@AppScreenPreviews
@Composable
private fun IecStandardPreview() {
    ResistorCalculatorTheme {
        Column(
            modifier = Modifier.height(64.dp)
        ) {
            val context = HomeActivity()
            ViewIecStandard(context)
        }
    }
}

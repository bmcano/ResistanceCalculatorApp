package com.brandoncano.resistancecalculator.ui.components

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.RcvActivity
import com.brandoncano.resistancecalculator.ui.composables.AppComponentPreviews
import com.brandoncano.resistancecalculator.ui.composables.ArrowButtonCard
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleBody
import com.brandoncano.resistancecalculator.ui.theme.textStyleHeadline
import com.brandoncano.resistancecalculator.util.OpenLink

/**
 * Job: Holds all the components for the about screen
 */

@Composable
fun HeadlineBodyStack(@StringRes label: Int, @StringRes body: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        val modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        Text(
            text = stringResource(id = label),
            modifier = modifier.padding(top = 12.dp),
            style = textStyleHeadline(),
        )
        Text(
            text = stringResource(id = body),
            modifier = modifier.padding(top = 4.dp),
            style = textStyleBody(),
        )
    }
}

@Composable
fun ViewIecStandard(context: Context) {
    ArrowButtonCard(
        Icons.Filled.Link,
        stringResource(id = R.string.about_button_iec)
    ) {
        OpenLink.openIECWebpage(context)
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
        Column(
            modifier = Modifier.height(64.dp)
        ) {
            val context = RcvActivity()
            ViewIecStandard(context)
        }
    }
}

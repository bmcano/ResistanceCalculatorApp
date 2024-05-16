package com.brandoncano.resistancecalculator.ui.composables

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.util.OpenLink

/**
 * Job: Holds all the components for the about screen
 */

@Composable
fun LabelBodyTextStack(@StringRes label: Int, @StringRes body: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        TextLabel(
            modifier = modifier.padding(top = 16.dp),
            text = stringResource(id = label)
        )
        TextBody(
            modifier = modifier,
            text = stringResource(id = body)
        )
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

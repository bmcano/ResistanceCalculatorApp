package com.brandoncano.resistancecalculator.ui.composables

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Undo
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.MainActivity
import com.brandoncano.resistancecalculator.util.OpenLink

@Composable
fun LabelBodyTextStack(@StringRes label: Int, @StringRes body: Int) {
    val textModifierBody = Modifier
        .padding(start = 16.dp, end = 16.dp)
    val textModifier = textModifierBody
        .padding(top = 16.dp)
    TextLabel(
        modifier = textModifier,
        text = stringResource(id = label)
    )
    TextBody(
        modifier = textModifierBody,
        text = stringResource(id = body)
    )
}

@Composable
fun ViewIecStandard(context: Context) {
    val modifier = Modifier.padding(top = 16.dp)
    Column(modifier) {
        ArrowButtonCard(
            Icons.Filled.Link,
            stringResource(id = R.string.iec_button_text)
        ) {
            OpenLink.openIECWebpage(context)
        }
    }
}

package com.brandoncano.resistancecalculator.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R

@Composable
fun LabelBodyTextStack(@StringRes label: Int, @StringRes body: Int) {
    val textModifierBody = Modifier
        .padding(start = 16.dp, end = 16.dp)
    val textModifier = textModifierBody
        .padding(top = 16.dp)
    TextLabel(
        modifier = textModifier,
        text = stringResource(id = R.string.about_created_by)
    )
    TextBody(
        modifier = textModifierBody,
        text = stringResource(id = R.string.about_author)
    )
}
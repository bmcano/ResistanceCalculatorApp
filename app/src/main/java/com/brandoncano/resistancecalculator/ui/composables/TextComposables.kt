package com.brandoncano.resistancecalculator.ui.composeables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

/**
 * Job: Holds all the designs for text within the app
 */

@Composable
fun TextHeadline(
    modifier: Modifier = Modifier,
    text: String,
) {
    val textStyle = MaterialTheme.typography.headlineSmall
    AppText(modifier, text, textStyle)
}

@Composable
fun TextTitle(
    modifier: Modifier = Modifier,
    text: String,
) {
    val textStyle = MaterialTheme.typography.titleMedium
    AppText(modifier, text, textStyle)
}

@Composable
fun TextLabel(
    modifier: Modifier = Modifier,
    text: String,
) {
    val textStyle = MaterialTheme.typography.labelMedium
    AppText(modifier, text, textStyle)
}

@Composable
fun TextBody(
    modifier: Modifier = Modifier,
    text: String,
) {
    val textStyle = MaterialTheme.typography.bodyMedium
    AppText(modifier, text, textStyle)
}

@Composable
private fun AppText(modifier: Modifier, text: String, textStyle: TextStyle) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
    )
}

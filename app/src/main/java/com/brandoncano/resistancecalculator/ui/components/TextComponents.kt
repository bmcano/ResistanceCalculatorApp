package com.brandoncano.resistancecalculator.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme

/**
 * Job: Holds all the designs for text within the app
 */

@Composable
fun TextResistance(
    modifier: Modifier = Modifier,
    text: String,
) {
    val textStyle = MaterialTheme.typography.headlineMedium
    val textAlign = TextAlign.Center
    AppText(modifier, text, textStyle, textAlign)
}

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
private fun AppText(modifier: Modifier, text: String, textStyle: TextStyle, textAlign: TextAlign? = null) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
        textAlign = textAlign
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TextComponentsPreview() {
    ResistanceCalculatorTheme {
        Column {
            TextHeadline(text = "Headline")
            TextTitle(text = "Title")
            TextLabel(text = "Label")
            TextBody(text = "Body")
        }
    }
}

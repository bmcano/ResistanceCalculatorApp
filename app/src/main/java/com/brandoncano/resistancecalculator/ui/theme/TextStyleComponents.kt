package com.brandoncano.resistancecalculator.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandoncano.resistancecalculator.ui.composables.AppScreenPreviews

@Composable
private fun textStyleBase() = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
)

@Composable
fun textStyleLargeTitle() = textStyleBase().merge(
    fontWeight = FontWeight.Medium,
    fontSize = TextFontSize.largeTitle,
    lineHeight = TextLineHeight.largeTitle,
)

@Composable
fun textStyleTitle() = textStyleBase().merge(
    fontWeight = FontWeight.Medium,
    fontSize = TextFontSize.title,
    lineHeight = TextLineHeight.title,
)

@Composable
fun textStyleHeadline() = textStyleBase().merge(
    fontWeight = FontWeight.Medium,
    fontSize = TextFontSize.headline,
    lineHeight = TextLineHeight.headline,
    letterSpacing = 0.25.sp,
)

@Composable
fun textStyleCallout() = textStyleBase().merge(
    fontWeight = FontWeight.Medium,
    fontSize = TextFontSize.callout,
    lineHeight = TextLineHeight.callout,
)

@Composable
fun textStyleBody() = textStyleBase().merge(
    fontSize = TextFontSize.body,
    lineHeight = TextLineHeight.body,
)

@Composable
fun textStyleSubhead() = textStyleBase().merge(
    fontSize = TextFontSize.subhead,
    lineHeight = TextLineHeight.subhead,
)

@Composable
fun textStyleCaption() = textStyleBase().merge(
    fontSize = TextFontSize.caption,
    lineHeight = TextLineHeight.caption,
)

@AppScreenPreviews
@Composable
private fun TextStylePreview() {
    ResistorCalculatorTheme {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "LargeTitle",
                style = textStyleLargeTitle(),
            )
            Text(
                text = "Title",
                style = textStyleTitle(),
            )
            Text(
                text = "Headline",
                style = textStyleHeadline(),
            )
            Text(
                text = "Callout",
                style = textStyleCallout(),
            )
            Text(
                text = "Body",
                style = textStyleBody(),
            )
            Text(
                text = "Subhead",
                style = textStyleSubhead(),
            )
            Text(
                text = "Caption",
                style = textStyleCaption(),
            )
        }
    }
}

@Composable
fun TextStyle.white() = this.merge(
    color = white
)

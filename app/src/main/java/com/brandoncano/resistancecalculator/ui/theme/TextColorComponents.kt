package com.brandoncano.resistancecalculator.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.brandoncano.sharedcomponents.composables.AppComponentPreviews
import com.brandoncano.sharedcomponents.text.textStyleTitle

/**
 * Note: Text colors
 */

@Composable
fun TextStyle.white() = this.merge(
    color = white
)

@AppComponentPreviews
@Composable
private fun TextStyleColorsPreview() {
    ResistorCalculatorTheme {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "White",
                style = textStyleTitle().white(),
            )
        }
    }
}

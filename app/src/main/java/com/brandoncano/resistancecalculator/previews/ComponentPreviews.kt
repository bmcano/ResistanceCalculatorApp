package com.brandoncano.resistancecalculator.previews

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

/**
 * Job: show the previews of all different screen components
 */

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextComponentsPreview() {
    ResistorCalculatorTheme {
        Column {

        }
    }
}
package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleHeadline

/**
 * Job: Holds composables for buttons
 */
@Composable
fun AppButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            style = textStyleHeadline(),
        )
    }
}

@AppScreenPreviews
@Composable
fun ButtonPreview() {
    ResistorCalculatorTheme {
        AppButton(Modifier, text = "Button") { }
    }
}

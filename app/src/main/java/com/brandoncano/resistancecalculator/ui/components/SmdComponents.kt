package com.brandoncano.resistancecalculator.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.composables.AppComponentPreviews
import com.brandoncano.resistancecalculator.ui.composables.RcvCustomCard
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleTitle
import com.brandoncano.resistancecalculator.ui.theme.white

/**
 * Job: Hold custom components for the SMD screen
 */

@Composable
fun SmdResistorLayout() {
    Column(
        modifier = Modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // will worry about size later
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(
                // TODO - for the image itself - will want start/end edges thicker and rounded
                painter = painterResource(id = R.drawable.smd_draft),
                contentDescription = stringResource(id = R.string.content_description_app_icon),
            )
            Text(
                text = "Test",
                style = textStyleTitle().white()
            )
        }
        ResistanceText("resistance")
    }
}

@Composable
private fun ResistanceText(resistance: String) {
    RcvCustomCard(
        modifier = Modifier.padding(top = 12.dp)
    ) {
        Text(
            text = resistance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
    }
}

@AppComponentPreviews
@Composable
private fun SmdResistorLayoutPreview() {
    ResistorCalculatorTheme {
        SmdResistorLayout()
    }
}
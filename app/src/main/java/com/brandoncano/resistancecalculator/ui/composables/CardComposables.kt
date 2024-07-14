package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleCallout

/**
 * Job: Holds all the designs for cards and dividers within the app
 */

@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
    onCard: Boolean = true
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = 1.dp,
        color = if (onCard && isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.outline
        } else {
            MaterialTheme.colorScheme.outlineVariant
        }
    )
}

@Composable
fun AppStandardCard(
    content: @Composable (ColumnScope.() -> Unit)
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth(),
        content = content,
    )
}
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Card(
        modifier = modifier,
        content = content,
    )
}

@Composable
fun ArrowButtonCard(
    imageVector: ImageVector,
    cardText: String,
    onClick: (() -> Unit),
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
    ) {
        CardRowView(onClick, imageVector, cardText)
    }
}

@Composable
fun ArrowButtonCard(
    imageVectors: List<ImageVector>,
    cardTexts: List<String>,
    onClicks: List<(() -> Unit)>,
) {
    if (imageVectors.size != cardTexts.size || imageVectors.size != onClicks.size) return
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth()
    ) {
        onClicks.forEachIndexed { index, onClick ->
            CardRowView(onClick, imageVectors[index], cardTexts[index])
            if (onClicks.size - 1 != index) {
                AppDivider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            }
        }
    }
}

@Composable
private fun CardRowView(
    onClick: (() -> Unit),
    imageVector: ImageVector,
    cardText: String,
) {
    Row(
        modifier = Modifier.clickable(role = Role.Button, onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.padding(16.dp),
            imageVector = imageVector,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
        )
        Text(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = cardText,
            maxLines = 1,
            style = textStyleCallout(),
            overflow = TextOverflow.Ellipsis,
        )
        Image(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = stringResource(id = R.string.content_description_right_arrow),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
        )
    }
}

@AppComponentPreviews
@Composable
private fun AppStandardCardPreview() {
    ResistorCalculatorTheme {
        AppStandardCard {
            Text(text = "Text1", modifier = Modifier.padding(4.dp))
            AppDivider(onCard = true)
            Text(text = "Text2", modifier = Modifier.padding(4.dp))
            AppDivider(modifier = Modifier.padding(4.dp))
            Text(text = "Text3", modifier = Modifier.padding(4.dp))
        }
        AppDivider(modifier = Modifier.padding(4.dp), false)
    }
}

@AppComponentPreviews
@Composable
private fun AppCardPreview() {
    ResistorCalculatorTheme {
        AppCard {
            Text(text = "Text1extended", modifier = Modifier.padding(4.dp))
        }
    }
}

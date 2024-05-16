package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

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
        color = if (onCard) {
            MaterialTheme.colorScheme.onSurfaceVariant
        } else {
            MaterialTheme.colorScheme.onSurface
        }
    )
}

@Composable
fun DefaultCard(
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
                .weight(1f),
            text = cardText,
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium,
            overflow = TextOverflow.Ellipsis,
        )
        Image(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = "Right arrow",
//            contentDescription = stringResource(id = R.string.content_right_arrow),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
        )
    }
}

package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

/**
 * Job: Components for the bottom navigation bar
 */

@Composable
fun CalculatorNavigationBar(
    selection: Int = 1,
    onClick: (Int) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(selection) }
    val labels = listOf(
        R.string.navbar_three_band,
        R.string.navbar_four_band,
        R.string.navbar_five_band,
        R.string.navbar_six_band
    )
    val icons = listOf(
        R.drawable.img_icon_three_band,
        R.drawable.img_icon_four_band,
        R.drawable.img_icon_five_band,
        R.drawable.img_icon_six_band
    )
    NavigationBar {
        labels.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(icons[index]),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item),
                        style = MaterialTheme.typography.bodySmall,
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onClick(selectedItem)
                }
            )
        }
    }
}

@AppScreenPreviews
@Composable
fun MyBottomNavigationBarPreview() {
    ResistorCalculatorTheme {
        CalculatorNavigationBar { }
    }
}

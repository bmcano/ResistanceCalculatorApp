package com.brandoncano.resistancecalculator.ui.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownItem


@Composable
fun OutlinedDropDownMenu(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int = -1,
    items: List<DropdownItem>,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var selectedLeadingIcon by remember { mutableIntStateOf(leadingIcon) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                expanded = !expanded
            }
        }
    }
    Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                selectedText = it
                onValueChange(it)
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() }
                .clickable(interactionSource, null, enabled = true) { expanded = !expanded },
            label = { Text(stringResource(label)) },
            leadingIcon = {
                if (selectedLeadingIcon != -1)
                    Image(
                        painter = painterResource(selectedLeadingIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(32.dp)
                    )
            },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            interactionSource = interactionSource
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .clickable(interactionSource, null, enabled = true) { expanded = !expanded }
        ) {
            items.forEach {
                DropdownItemView(it) {
                    selectedText = it.name
                    selectedLeadingIcon = it.imageResId
                    expanded = false
                }
            }
        }
    }
}

@Composable
fun DropdownItemView(item: DropdownItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp)
            )
            Text(
                text = item.value,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}

@AppScreenPreviews
@Composable
fun CustomDropdownRowPreview() {
    val item1 = DropdownItem(imageResId = R.drawable.square_red, name = "Item 1", value = "Value 1")
    DropdownItemView(item1) { }
}

@AppScreenPreviews
@Composable
fun CustomDropdownPreview() {
    val item1 = DropdownItem(imageResId = R.drawable.square_red, name = "Item 1", value = "Value 1")
    val item2 = DropdownItem(imageResId = R.drawable.square_orange, name = "Item 2", value = "Value 2")
    val item3 = DropdownItem(imageResId = R.drawable.square_yellow, name = "Item 3", value = "Value 3")
    val item4 = DropdownItem(imageResId = R.drawable.square_green, name = "Item 4", value = "Value 4")
    val item5 = DropdownItem(imageResId = R.drawable.square_blue, name = "Item 5", value = "Value 5")
    val item6 = DropdownItem(imageResId = R.drawable.square_violet, name = "Item 6", value = "Value 6")
    val list = listOf(item1, item2, item3, item4, item5, item6)
    OutlinedDropDownMenu(R.string.number_band_hint1, -1, list) {
        // do nothing
    }
}

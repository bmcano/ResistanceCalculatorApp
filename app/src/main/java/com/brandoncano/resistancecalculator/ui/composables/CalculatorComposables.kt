package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownItem

// TODO - Find a good way to modify the colors of the different bands, this will be the last component we build.
@Composable
fun ResistorLayout() {
    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp)
    ) {
        Spacer(modifier = Modifier.height(1.dp)) // Spacer to replace the Group

        ResistorRow(
            R.drawable.img_resistor_p1, R.drawable.img_resistor_p2, R.drawable.img_resistor_p3,
            R.drawable.img_resistor_p4, R.drawable.img_resistor_p5, R.drawable.img_resistor_p6_7,
            R.drawable.img_resistor_p6_7, R.drawable.img_resistor_p8, R.drawable.img_resistor_p9,
            R.drawable.img_resistor_p10, R.drawable.img_resistor_p11, R.drawable.img_resistor_p12,
            R.drawable.img_resistor_p13
        )
    }
}

@Composable
fun ResistorRow(vararg resistorImages: Int) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        for (resistorImage in resistorImages) {
            Image(
                painter = painterResource(id = resistorImage),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun OutlinedDropDownMenu(
    label: String,
    items: List<DropdownItem>,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
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
            readOnly = true,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() }
                .clickable(interactionSource, null, enabled = true) { expanded = !expanded },
            label = { Text(label) },
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
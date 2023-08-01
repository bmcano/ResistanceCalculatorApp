package com.brandoncano.resistancecalculator.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropDownItem
import com.brandoncano.resistancecalculator.ui.theme.MangoPrimary

/**
 * Job: Hold different compose components to be reused
 */

@Preview
@Composable
fun HomeScreenAppIcon() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_round),
            contentDescription = stringResource(id = R.string.home_app_icon_content_description),
            alignment = Alignment.Center,
            modifier = Modifier.border(BorderStroke(2.dp, MangoPrimary), CircleShape)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedDropDownMenu(label: String, items: List<DropDownItem>) {
    val interactionSource = remember { MutableInteractionSource() }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var selectedIcon: Color? by remember { mutableStateOf(null) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                expanded = !expanded
            }
        }
    }
    Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
        OutlinedTextField(
            value = selectedText,
            readOnly = true,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() }
                .clickable(interactionSource, null, enabled = true) { expanded = !expanded },
            label = { Text(label) },
            leadingIcon = {
                if (selectedIcon != null) {
                    Icon(
                        painter = painterResource(id = R.drawable.square_black),
                        contentDescription = "Color",
                        tint = selectedIcon as Color
                    )
                }
            },
            trailingIcon = {
                Icon(icon, "", Modifier.clickable { expanded = !expanded })
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
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.square_black),
                            contentDescription = "Color",
                            tint = it.color
                        )
                    },
                    text = { Text(it.name) },
                    onClick = {
                        selectedText = it.name
                        selectedIcon = it.color
                        expanded = false
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun Resistor() {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.img_resistor_p1), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p2), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p3), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p4), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p5), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p6_7), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p6_7), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p8), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p9), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p10), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p11), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p12), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.img_resistor_p13), contentDescription = null)
    }
}
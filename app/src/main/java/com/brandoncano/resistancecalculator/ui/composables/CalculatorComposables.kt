package com.brandoncano.resistancecalculator.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R

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

//@Composable
//fun OutlinedDropDownMenu(
//    label: String,
//    items: List<Any>,
//    viewModel: CapacitorViewModel,
//    startingValue: CapacitorTolerance?
//) {
//    val interactionSource = remember { MutableInteractionSource() }
//    var expanded by remember { mutableStateOf(false) }
//    var selectedText by remember { mutableStateOf("${startingValue ?: ""}") }
//    var textFieldSize by remember { mutableStateOf(Size.Zero) }
//    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
//    LaunchedEffect(interactionSource) {
//        interactionSource.interactions.collect { interaction ->
//            if (interaction is PressInteraction.Release) {
//                expanded = !expanded
//            }
//        }
//    }
//    Column(Modifier.padding(start = 16.dp, end = 16.dp)) {
//        OutlinedTextField(
//            value = selectedText,
//            readOnly = true,
//            onValueChange = { selectedText = it },
//            modifier = Modifier
//                .fillMaxSize()
//                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() }
//                .clickable(interactionSource, null, enabled = true) { expanded = !expanded },
//            label = { Text(label) },
//            trailingIcon = {
//                Icon(
//                    imageVector = icon,
//                    contentDescription = null,
//                    modifier = Modifier.clickable { expanded = !expanded }
//                )
//            },
//            interactionSource = interactionSource
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier
//                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
//                .clickable(interactionSource, null, enabled = true) { expanded = !expanded }
//        ) {
//            DropdownMenuItem(
//                text = {
//                    Text(
//                        text = stringResource(id = R.string.home_no_tolerance),
//                        style = TextStyle(fontStyle = FontStyle.Italic)
//                    )
//                },
//                onClick = {
//                    selectedText = ""
//                    expanded = false
//                },
//            )
//            items.forEach {
//                val toleranceText: String = it.toString()
//                DropdownMenuItem(
//                    text = { Text(toleranceText) },
//                    onClick = {
//                        viewModel.capacitor.tolerance = it
//                        selectedText = toleranceText
//                        expanded = false
//                    },
//                )
//            }
//        }
//    }
//}
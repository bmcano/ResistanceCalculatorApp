package com.brandoncano.resistancecalculator.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.Screen
import com.brandoncano.resistancecalculator.ui.theme.MangoPrimary

/**
 * Job: Hold different compose components to be reused
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(navController: NavController) {
//    val colors = centerAlignedTopAppBarColors(
//        containerColor = OrangePrimary,
//        navigationIconContentColor = WhiteTheme,
//        titleContentColor = WhiteTheme,
//        actionIconContentColor = WhiteTheme
//    )
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            var expanded by remember { mutableStateOf(false) }
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Feedback") },
                    onClick = { }
                )
                DropdownMenuItem(
                    text = { Text("About this app") },
                    onClick = { navController.navigate(Screen.About.route) }
                )
            }
        },
//        colors = colors
    )
}

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

// https://stackoverflow.com/questions/67111020/exposed-drop-down-menu-for-jetpack-compose
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun DropDownColorMenu() {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Item1","Item2","Item3")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = selectedText,
//            readOnly = true,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text("Label") },
            trailingIcon = {
                Icon(icon,"contentDescription", Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            suggestions.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        selectedText = it
                        expanded = false
                    }
                )
            }
        }
    }
}
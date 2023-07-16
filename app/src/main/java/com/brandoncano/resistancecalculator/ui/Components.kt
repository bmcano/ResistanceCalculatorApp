package com.brandoncano.resistancecalculator.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.MangoPrimary
import com.brandoncano.resistancecalculator.ui.theme.OrangePrimary
import com.brandoncano.resistancecalculator.ui.theme.WhiteTheme

/**
 * Job: Hold different compose components to be reused
 */

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeAppBar() {
    val colors = centerAlignedTopAppBarColors(
        containerColor = OrangePrimary,
//        scrolledContainerColor = ,
        navigationIconContentColor = WhiteTheme,
        titleContentColor = WhiteTheme,
        actionIconContentColor = WhiteTheme
    )
    CenterAlignedTopAppBar(
//        modifier = Modifier,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
//        navigationIcon = {
//            IconButton(onClick = { /* doSomething() */ }) {
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "Localized description"
//                )
//            }
//        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More"
                )
            }
        },
        colors = colors
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

@Composable
fun HomeScreenButton(buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(MangoPrimary),
        shape = RoundedCornerShape(4.dp),
    ) {
        Text(text = buttonText, color = WhiteTheme)
    }
}

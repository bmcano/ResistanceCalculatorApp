package com.brandoncano.resistancecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.MangoPrimary
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme

@Composable
fun TextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(MangoPrimary),
        shape = RoundedCornerShape(8.dp),
    ) {
        TextHeadline(text = text)
    }
}

@Composable
fun ImageTextButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(MangoPrimary),
        shape = RoundedCornerShape(8.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = "TODO",
        )
        TextBody(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = text
        )
    }
}

@Preview
@Composable
private fun ButtonPreviews() {
    ResistanceCalculatorTheme {
        Column {
            TextButton(text = "This is a button", onClick = { })
            ImageTextButton(text = "This has an icon", onClick = { })
        }
    }
}
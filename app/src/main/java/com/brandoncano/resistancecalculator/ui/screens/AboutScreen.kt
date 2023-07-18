package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.components.ImageTextButton
import com.brandoncano.resistancecalculator.ui.components.TextBody
import com.brandoncano.resistancecalculator.ui.components.TextHeadline
import com.brandoncano.resistancecalculator.ui.components.TextTitle
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme

@Composable
fun AboutScreen(context: Context) {
    ResistanceCalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column {
                val textModifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 16.dp, end = 16.dp)
                TextTitle(
                    modifier = textModifier,
                    text = stringResource(id = R.string.app_name)
                )
                TextHeadline(
                    modifier = textModifier,
                    text = stringResource(id = R.string.app_version)
                )
                TextBody(
                    modifier = textModifier,
                    text = stringResource(id = R.string.version)
                )
                TextHeadline(
                    modifier = textModifier,
                    text = stringResource(id = R.string.created_by)
                )
                TextBody(
                    modifier = textModifier,
                    text = stringResource(id = R.string.author)
                )
                // resistor
                TextBody(
                    modifier = textModifier.padding(top = 16.dp),
                    text = stringResource(id = R.string.description_one)
                )
                TextBody(
                    modifier = textModifier.padding(top = 16.dp),
                    text = stringResource(id = R.string.description_two)
                )
                ImageTextButton(
                    modifier = textModifier.padding(top = 16.dp).fillMaxWidth(),
                    text = stringResource(id = R.string.rate_us),
                ) {
                    val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator&pli=1")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(context, intent, null)
                }
            }
        }
    }
}
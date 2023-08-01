package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddToHomeScreen
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.components.ArrowButtonCard
import com.brandoncano.resistancecalculator.ui.components.DefaultCard
import com.brandoncano.resistancecalculator.ui.components.TextBody
import com.brandoncano.resistancecalculator.ui.components.TextHeadline
import com.brandoncano.resistancecalculator.ui.components.TextLabel
import com.brandoncano.resistancecalculator.ui.components.TitleAppBar
import com.brandoncano.resistancecalculator.ui.theme.ResistanceCalculatorTheme
import com.brandoncano.resistancecalculator.util.PlayStore

@Composable
fun AboutScreen(context: Context) {
    ResistanceCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) { Content(context) }
    }
}

@Composable
private fun Content(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleAppBar(stringResource(R.string.menu_about))
        val textModifierBody = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(start = 16.dp, end = 16.dp)
        val textModifier = textModifierBody.padding(top = 16.dp)

        DefaultCard {
            TextHeadline(
                modifier = textModifier,
                text = stringResource(id = R.string.app_name)
            )
            TextLabel(
                modifier = textModifier,
                text = stringResource(id = R.string.created_by)
            )
            TextBody(
                modifier = textModifierBody,
                text = stringResource(id = R.string.author)
            )
            TextLabel(
                modifier = textModifier,
                text = stringResource(id = R.string.app_version)
            )
            TextBody(
                modifier = textModifierBody,
                text = stringResource(id = R.string.version)
            )
            TextLabel(
                modifier = textModifier,
                text = stringResource(id = R.string.last_updated_on)
            )
            TextBody(
                modifier = textModifierBody,
                text = stringResource(id = R.string.last_updated)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        DefaultCard {
            TextLabel(
                modifier = textModifier,
                text = stringResource(id = R.string.description)
            )
            TextBody(
                modifier = textModifier,
                text = stringResource(id = R.string.description_one)
            )
            TextBody(
                modifier = textModifier,
                text = stringResource(id = R.string.description_two)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        ArrowButtonCard(
            listOf(Icons.Outlined.Star, Icons.Outlined.AddToHomeScreen),
            listOf(
                stringResource(id = R.string.rate_us),
                stringResource(id = R.string.view_capacitor_app)
            ),
            listOf(
                { PlayStore.openResistorApp(context) },
                { PlayStore.openCapacitorApp(context) }
            ),
        )
    }
}
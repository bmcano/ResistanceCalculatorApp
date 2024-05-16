package com.brandoncano.resistancecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.composables.DefaultCard
import com.brandoncano.resistancecalculator.ui.composables.LabelBodyTextStack
import com.brandoncano.resistancecalculator.ui.composables.TextBody
import com.brandoncano.resistancecalculator.ui.composables.TextHeadline
import com.brandoncano.resistancecalculator.ui.composables.TextLabel
import com.brandoncano.resistancecalculator.ui.composables.TitleAppBar
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

@Composable
fun AboutScreen(context: Context) {
    ResistorCalculatorTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Content(context)
        }
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
        TitleAppBar(stringResource(R.string.about_title))
        val textModifierBody = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(start = 16.dp, end = 16.dp)
        val textModifier = textModifierBody.padding(top = 16.dp)

        DefaultCard {
            TextHeadline(
                modifier = textModifier,
                text = stringResource(id = R.string.app_name)
            )
            LabelBodyTextStack(label = R.string.about_app_version, body = R.string.version)
            LabelBodyTextStack(label = R.string.about_created_by, body = R.string.about_author)
            LabelBodyTextStack(label = R.string.about_last_updated_on, body = R.string.last_updated)
            Spacer(modifier = Modifier.height(16.dp))
        }

//        DefaultCard {
//            TextLabel(
//                modifier = textModifier,
//                text = stringResource(id = R.string.about_description)
//            )
//            TextBody(
//                modifier = textModifier,
//                text = stringResource(id = R.string.about_description_part_01)
//            )
//            TextBody(
//                modifier = textModifier,
//                text = stringResource(id = R.string.about_description_part_02)
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//
//        ArrowButtonCard(
//            listOf(Icons.Outlined.Star, Icons.AutoMirrored.Outlined.AddToHomeScreen),
//            listOf(
//                stringResource(id = R.string.about_rate_this_app),
//                stringResource(id = R.string.about_view_resistor_app)
//            ),
//            listOf(
//                { PlayStore.openCapacitorApp(context) },
//                { PlayStore.openResistorApp(context) }
//            ),
//        )
    }
}
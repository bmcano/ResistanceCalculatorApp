package com.brandoncano.resistancecalculator.ui.screens.smd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.model.smd.SmdResistor
import com.brandoncano.resistancecalculator.ui.composables.AppComponentPreviews
import com.brandoncano.resistancecalculator.ui.composables.AppCard
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleLargeTitle
import com.brandoncano.resistancecalculator.ui.theme.textStyleTitle
import com.brandoncano.resistancecalculator.ui.theme.white
import com.brandoncano.resistancecalculator.util.formatResistance

@Composable
fun SmdResistorLayout(
    resistor: SmdResistor,
    isError: Boolean = false,
) {
    Column(
        modifier = Modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_smd_resistor),
                contentDescription = stringResource(id = R.string.content_description_app_icon),
            )
            val text = if (isError) {
                stringResource(id = R.string.error_na)
            }  else {
                resistor.code
            }
            Text(
                text = text,
                style = textStyleLargeTitle().white()
            )
        }
        val text = when {
            resistor.isEmpty() -> stringResource(id = R.string.default_smd_value)
            isError -> stringResource(id = R.string.error_na)
            else -> resistor.formatResistance()
        }
        ResistanceText(text)
    }
}

@Composable
private fun ResistanceText(resistance: String) {
    AppCard(modifier = Modifier.padding(top = 12.dp)) {
        Text(
            text = resistance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
    }
}

@AppComponentPreviews
@Composable
private fun SmdResistorLayoutPreview() {
    ResistorCalculatorTheme {
        val resistor = SmdResistor(code = "1R4")
        SmdResistorLayout(resistor)
    }
}

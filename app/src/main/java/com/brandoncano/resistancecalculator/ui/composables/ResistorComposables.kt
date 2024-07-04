package com.brandoncano.resistancecalculator.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtv
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.ui.theme.textStyleTitle
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.bandFiveForDisplay
import com.brandoncano.resistancecalculator.util.bandSixForDisplay
import com.brandoncano.resistancecalculator.util.bandThreeForDisplay
import com.brandoncano.resistancecalculator.util.formatResistance

/**
 * Job: Holds all the parts for the resistor layout.
 */

private data class ResistorImagePair(@DrawableRes val drawableRes: Int, val color: String? = null)

@Composable
fun ResistorLayout(resistor: ResistorCtv) {
    Column(
        modifier = Modifier.padding(top = 12.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ResistorRow(
            ResistorImagePair(R.drawable.img_resistor_p1),
            ResistorImagePair(R.drawable.img_resistor_p2, resistor.band1),
            ResistorImagePair(R.drawable.img_resistor_p3),
            ResistorImagePair(R.drawable.img_resistor_p4, resistor.band2),
            ResistorImagePair(R.drawable.img_resistor_p5),
            ResistorImagePair(R.drawable.img_resistor_p6_7, resistor.bandThreeForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_p6_7),
            ResistorImagePair(R.drawable.img_resistor_p8, resistor.band4),
            ResistorImagePair(R.drawable.img_resistor_p9),
            ResistorImagePair(R.drawable.img_resistor_p10, resistor.bandFiveForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_p11),
            ResistorImagePair(R.drawable.img_resistor_p12, resistor.bandSixForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_p13),
        )
        ResistanceText(resistor.formatResistance())
    }
}

@Composable
fun ResistorLayout(resistor: ResistorVtc, resistance: String) {
    Column(
        modifier = Modifier.padding(top = 12.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ResistorRow(
            ResistorImagePair(R.drawable.img_resistor_p1),
            ResistorImagePair(R.drawable.img_resistor_p2, resistor.band1),
            ResistorImagePair(R.drawable.img_resistor_p3),
            ResistorImagePair(R.drawable.img_resistor_p4, resistor.band2),
            ResistorImagePair(R.drawable.img_resistor_p5),
            ResistorImagePair(R.drawable.img_resistor_p6_7, resistor.bandThreeForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_p6_7),
            ResistorImagePair(R.drawable.img_resistor_p8, resistor.band4),
            ResistorImagePair(R.drawable.img_resistor_p9),
            ResistorImagePair(R.drawable.img_resistor_p10, resistor.bandFiveForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_p11),
            ResistorImagePair(R.drawable.img_resistor_p12, resistor.bandSixForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_p13),
        )
        ResistanceText(resistance)
    }
}

@Composable
private fun ResistorRow(vararg resistorImages: ResistorImagePair) {
    Row(
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        resistorImages.forEach { resistorImage ->
            if (resistorImage.color.isNullOrEmpty()) {
                ResistorImage(resistorImage.drawableRes)
            } else {
                val color = ColorFinder.textToColor(resistorImage.color)
                ResistorImage(resistorImage.drawableRes, color)
            }
        }
    }
}

@Composable
private fun ResistorImage(@DrawableRes drawableRes: Int) {
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
    )
}

@Composable
private fun ResistorImage(@DrawableRes drawableRes: Int, color: Color) {
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color),
    )
}

@Composable
private fun ResistanceText(resistance: String) {
    ContentCard(
        modifier = Modifier.padding(top = 12.dp)
    ) {
        Text(
            text = resistance,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            style = textStyleTitle(),
        )
    }
}

@AppScreenPreviews
@Composable
private fun ResistorLayoutsPreview() {
    ResistorCalculatorTheme {
        Column {
            ResistorLayout(ResistorCtv())
            ResistorLayout(ResistorCtv("Red", "Orange", "", "Yellow", "", "", 3))
            ResistorLayout(ResistorCtv("Red", "Orange", "", "Yellow", "Green", "", 4))
            ResistorLayout(ResistorCtv("Red", "Orange", "Black", "Yellow", "Green", "", 5))
            ResistorLayout(ResistorCtv("Red", "Orange", "Black", "Yellow", "Green", "Blue", 6))
        }
    }
}

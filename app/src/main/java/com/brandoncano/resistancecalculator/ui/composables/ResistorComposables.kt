package com.brandoncano.resistancecalculator.ui.composables

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.model.ctv.Resistor
import com.brandoncano.resistancecalculator.resistor.ResistorCtV
import com.brandoncano.resistancecalculator.ui.HomeActivity
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.ResistanceFormatter

/**
 * Job: Holds all the parts for the resistor layout.
 */

@Composable
fun ResistorLayout(
    resistor: Resistor
) {
    Column(
        modifier = Modifier
            .padding(top = 12.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // might make these as extension functions instead
        val band3 = if (resistor.isFiveSixBand()) resistor.band3 else ""
        val band5 = if (!resistor.isThreeBand()) resistor.band5 else ""
        val band6 = if (resistor.isSixBand()) resistor.band6 else ""
        Spacer(modifier = Modifier.height(1.dp)) // Spacer to replace the Group
        ResistorRow(
            ResistorImagePair(R.drawable.img_resistor_p1),
            ResistorImagePair(R.drawable.img_resistor_p2, resistor.band1),
            ResistorImagePair(R.drawable.img_resistor_p3),
            ResistorImagePair(R.drawable.img_resistor_p4, resistor.band2),
            ResistorImagePair(R.drawable.img_resistor_p5),
            ResistorImagePair(R.drawable.img_resistor_p6_7, band3),
            ResistorImagePair(R.drawable.img_resistor_p6_7),
            ResistorImagePair(R.drawable.img_resistor_p8, resistor.band4),
            ResistorImagePair(R.drawable.img_resistor_p9),
            ResistorImagePair(R.drawable.img_resistor_p10, band5),
            ResistorImagePair(R.drawable.img_resistor_p11),
            ResistorImagePair(R.drawable.img_resistor_p12, band6),
            ResistorImagePair(R.drawable.img_resistor_p13),
        )

        // NOTE: this is temporary translation until logic is adjusted to handle new models
        val resistorCtV = ResistorCtV(HomeActivity())
        resistorCtV.sigFigBandOne = resistor.band1
        resistorCtV.sigFigBandTwo = resistor.band2
        resistorCtV.sigFigBandThree = resistor.band3
        resistorCtV.multiplierBand = resistor.band4
        resistorCtV.toleranceBand = resistor.band5
        resistorCtV.ppmBand = resistor.band6
        resistorCtV.setBands(resistor.numberOfBands)
        val resistance = ResistanceFormatter.calculate(resistorCtV)
        ResistanceText(resistance)
    }
}

private data class ResistorImagePair(@DrawableRes val drawableRes: Int, val color: String? = null)

@Composable
private fun ResistorRow(vararg resistorImages: ResistorImagePair) {
    Row(
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        resistorImages.forEach { resistorImage ->
            if (resistorImage.color.isNullOrEmpty()) {
                ResistorImage(resistorImage.drawableRes)
            } else {
                val colorRes = ColorFinder.textToColor(resistorImage.color)
                ResistorImage(resistorImage.drawableRes, colorRes)
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
private fun ResistorImage(@DrawableRes drawableRes: Int, @ColorRes colorRes: Int) {
    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        colorFilter = ColorFilter.tint(colorResource(colorRes)),
    )
}

// TODO - WIP
@Composable
private fun ResistanceText(resistance: String) {
    ContentCard(
        modifier = Modifier.padding(top = 12.dp)
    ) {
        TextHeadline(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp),
            text = resistance
        )
    }
}

@AppScreenPreviews
@Composable
private fun ResistorLayoutsPreview() {
    ResistorCalculatorTheme {
        Column {
//            ResistorLayout("", "", "", "", "", "")
//            ResistorLayout("Red", "Orange", "", "Yellow", "", "")
//            ResistorLayout("Red", "Orange", "", "Yellow", "Green", "")
//            ResistorLayout("Red", "Orange", "Black", "Yellow", "Green", "")
//            ResistorLayout("Red", "Orange", "Black", "Yellow", "Green", "Blue")
        }
    }
}

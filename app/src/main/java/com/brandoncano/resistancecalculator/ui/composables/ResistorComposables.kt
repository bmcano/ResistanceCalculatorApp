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
import com.brandoncano.resistancecalculator.model.ctv.ResistorCtv
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.bandFiveForDisplay
import com.brandoncano.resistancecalculator.util.bandSixForDisplay
import com.brandoncano.resistancecalculator.util.bandThreeForDisplay
import com.brandoncano.resistancecalculator.util.formatResistance

/**
 * Job: Holds all the parts for the resistor layout.
 */

@Composable
fun ResistorLayout(
    resistor: ResistorCtv
) {
    Column(
        modifier = Modifier
            .padding(top = 12.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // might make these as extension functions instead
        Spacer(modifier = Modifier.height(1.dp)) // Spacer to replace the Group
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
fun ResistorLayout(
    resistor: ResistorVtc
) {
    Column(
        modifier = Modifier
            .padding(top = 12.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // might make these as extension functions instead
        Spacer(modifier = Modifier.height(1.dp)) // Spacer to replace the Group
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

        ResistanceText("resistor.formatResistance()")
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

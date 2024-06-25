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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import com.brandoncano.resistancecalculator.util.ColorFinder

/**
 * Job: Holds all the parts for the resistor layout.
 */

@Composable
fun ResistorLayout(
    band1: String,
    band2: String,
    band3: String,
    band4: String,
    band5: String,
    band6: String
) {
    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp)
    ) {
        Spacer(modifier = Modifier.height(1.dp)) // Spacer to replace the Group
        ResistorRow(
            ResistorImagePair(R.drawable.img_resistor_p1),
            ResistorImagePair(R.drawable.img_resistor_p2, band1),
            ResistorImagePair(R.drawable.img_resistor_p3),
            ResistorImagePair(R.drawable.img_resistor_p4, band2),
            ResistorImagePair(R.drawable.img_resistor_p5),
            ResistorImagePair(R.drawable.img_resistor_p6_7, band3),
            ResistorImagePair(R.drawable.img_resistor_p6_7),
            ResistorImagePair(R.drawable.img_resistor_p8, band4),
            ResistorImagePair(R.drawable.img_resistor_p9),
            ResistorImagePair(R.drawable.img_resistor_p10, band5),
            ResistorImagePair(R.drawable.img_resistor_p11),
            ResistorImagePair(R.drawable.img_resistor_p12, band6),
            ResistorImagePair(R.drawable.img_resistor_p13),
        )
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

@AppScreenPreviews
@Composable
private fun ResistorLayoutsPreview() {
    ResistorCalculatorTheme {
        Column {
            ResistorLayout("", "", "", "", "", "")
            ResistorLayout("Red", "Orange", "", "Yellow", "", "")
            ResistorLayout("Red", "Orange", "", "Yellow", "Green", "")
            ResistorLayout("Red", "Orange", "Black", "Yellow", "Green", "")
            ResistorLayout("Red", "Orange", "Black", "Yellow", "Green", "Blue")
        }
    }
}

package com.brandoncano.resistancecalculator.ui.screens.vtc

import android.graphics.Picture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.model.vtc.ResistorVtc
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistanceText
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorImagePair
import com.brandoncano.resistancecalculator.ui.screens.ctv.ResistorRow
import com.brandoncano.resistancecalculator.util.bandFiveForDisplay
import com.brandoncano.resistancecalculator.util.bandFourForDisplay
import com.brandoncano.resistancecalculator.util.bandOneForDisplay
import com.brandoncano.resistancecalculator.util.bandSixForDisplay
import com.brandoncano.resistancecalculator.util.bandThreeForDisplay
import com.brandoncano.resistancecalculator.util.bandTwoForDisplay
import com.brandoncano.resistancecalculator.util.deriveResistorColor
import com.brandoncano.sharedcomponents.composables.DrawContent

@Composable
fun resistorPicture(resistor: ResistorVtc, isError: Boolean): Picture {
    val picture = remember { Picture() }
    DrawContent(picture) {
        ResistorLayout(resistor, isError)
    }
    return picture
}

@Composable
fun ResistorLayout(
    resistor: ResistorVtc,
    isError: Boolean,
) {
    Column(
        modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val resistorColor = resistor.deriveResistorColor()
        ResistorRow(
            ResistorImagePair(R.drawable.img_resistor_wire, Colors.RESISTOR_WIRE),
            ResistorImagePair(R.drawable.img_resistor_end_left, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_96, resistor.bandOneForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_curve_left, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistor.bandTwoForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistor.bandThreeForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_64, resistor.bandFourForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_band_64_wide, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_64_wide, resistor.bandFiveForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_curve_right, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_band_96, resistor.bandSixForDisplay()),
            ResistorImagePair(R.drawable.img_resistor_end_right, resistorColor),
            ResistorImagePair(R.drawable.img_resistor_wire, Colors.RESISTOR_WIRE),
        )
        val text = when {
            resistor.isEmpty() -> stringResource(id = R.string.default_vtc_value)
            isError -> stringResource(id = R.string.error_na)
            else -> resistor.getResistorValue()
        }
        ResistanceText(text)
    }
}

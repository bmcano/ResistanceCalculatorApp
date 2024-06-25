package com.brandoncano.resistancecalculator.previews

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.DropdownItem
import com.brandoncano.resistancecalculator.ui.composables.DropdownItemView
import com.brandoncano.resistancecalculator.ui.composables.OutlinedDropDownMenu
import com.brandoncano.resistancecalculator.ui.composables.ResistorLayout
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme

/**
 * Job: show the previews of all different screen components
 */


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CustomDropdownRowPreview() {
    val item1 = DropdownItem(imageResId = R.drawable.square_red, name = "Item 1", value = "Value 1")
    DropdownItemView(item1) { }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CustomDropdownPreview() {
    val item1 = DropdownItem(imageResId = R.drawable.square_red, name = "Item 1", value = "Value 1")
    val item2 = DropdownItem(imageResId = R.drawable.square_orange, name = "Item 2", value = "Value 2")
    val item3 = DropdownItem(imageResId = R.drawable.square_yellow, name = "Item 3", value = "Value 3")
    val item4 = DropdownItem(imageResId = R.drawable.square_green, name = "Item 4", value = "Value 4")
    val item5 = DropdownItem(imageResId = R.drawable.square_blue, name = "Item 5", value = "Value 5")
    val item6 = DropdownItem(imageResId = R.drawable.square_violet, name = "Item 6", value = "Value 6")
    val list = listOf(item1, item2, item3, item4, item5, item6)
    OutlinedDropDownMenu("test", list)
}
package com.brandoncano.resistancecalculator.components

import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.constants.Colors
import com.brandoncano.resistancecalculator.constants.Symbols

/**
 * Job: Holds the list of items for each dropdown
 */
object DropdownLists {

    val NUMBER_LIST = listOf(
        DropdownItem(R.drawable.square_black, Colors.BLACK, "0"),
        DropdownItem(R.drawable.square_brown, Colors.BROWN, "1"),
        DropdownItem(R.drawable.square_red, Colors.RED, "2"),
        DropdownItem(R.drawable.square_orange, Colors.ORANGE, "3"),
        DropdownItem(R.drawable.square_yellow, Colors.YELLOW, "4"),
        DropdownItem(R.drawable.square_green, Colors.GREEN, "5"),
        DropdownItem(R.drawable.square_blue, Colors.BLUE, "6"),
        DropdownItem(R.drawable.square_violet, Colors.VIOLET, "7"),
        DropdownItem(R.drawable.square_gray, Colors.GRAY, "8"),
        DropdownItem(R.drawable.square_white, Colors.WHITE, "9"),
    )

    val NUMBER_LIST_NO_BLACK = NUMBER_LIST.drop(1)

    val MULTIPLIER_LIST = listOf(
        DropdownItem(R.drawable.square_black, Colors.BLACK, "x 1"),
        DropdownItem(R.drawable.square_brown, Colors.BROWN, "x 10"),
        DropdownItem(R.drawable.square_red, Colors.RED, "x 100"),
        DropdownItem(R.drawable.square_orange, Colors.ORANGE, "x 1k"),
        DropdownItem(R.drawable.square_yellow, Colors.YELLOW, "x 10k"),
        DropdownItem(R.drawable.square_green, Colors.GREEN, "x 100k"),
        DropdownItem(R.drawable.square_blue, Colors.BLUE, "x 1M"),
        DropdownItem(R.drawable.square_violet, Colors.VIOLET, "x 10M"),
        DropdownItem(R.drawable.square_gray, Colors.GRAY, "x 100M"),
        DropdownItem(R.drawable.square_white, Colors.WHITE, "x 1G"),
        DropdownItem(R.drawable.square_gold, Colors.GOLD, "x 0.1"),
        DropdownItem(R.drawable.square_silver, Colors.SILVER, "x 0.01"),
    )

    fun getToleranceArray(isVtC: Boolean = false): Array<SpinnerItem> {
        return arrayOf(
            SpinnerItem(Colors.BROWN, R.drawable.square_brown, "${Symbols.PM}1%", isVtC),
            SpinnerItem(Colors.RED, R.drawable.square_red, "${Symbols.PM}2%", isVtC),
            SpinnerItem(Colors.GREEN, R.drawable.square_green, "${Symbols.PM}0.5%", isVtC),
            SpinnerItem(Colors.BLUE, R.drawable.square_blue, "${Symbols.PM}0.25%", isVtC),
            SpinnerItem(Colors.VIOLET, R.drawable.square_violet, "${Symbols.PM}0.1%", isVtC),
            SpinnerItem(Colors.GRAY, R.drawable.square_gray, "${Symbols.PM}0.05%", isVtC),
            SpinnerItem(Colors.GOLD, R.drawable.square_gold, "${Symbols.PM}5%", isVtC),
            SpinnerItem(Colors.SILVER, R.drawable.square_silver, "${Symbols.PM}10%", isVtC),
        )
    }

    fun getPpmArray(isVtC: Boolean = false): Array<SpinnerItem> {
        return arrayOf(
            SpinnerItem(Colors.BLACK, R.drawable.square_black, "250 ${Symbols.PPM}", isVtC),
            SpinnerItem(Colors.BROWN, R.drawable.square_brown, "100 ${Symbols.PPM}", isVtC),
            SpinnerItem(Colors.RED, R.drawable.square_red, "50 ${Symbols.PPM}", isVtC),
            SpinnerItem(Colors.ORANGE, R.drawable.square_orange, "15 ${Symbols.PPM}", isVtC),
            SpinnerItem(Colors.YELLOW, R.drawable.square_yellow, "25 ${Symbols.PPM}", isVtC),
            SpinnerItem(Colors.GREEN, R.drawable.square_green, "20 ${Symbols.PPM}", isVtC),
            SpinnerItem(Colors.BLUE, R.drawable.square_blue, "10 ${Symbols.PPM}", isVtC),
            SpinnerItem(Colors.VIOLET, R.drawable.square_violet, "5 ${Symbols.PPM}", isVtC),
            SpinnerItem(Colors.GRAY, R.drawable.square_gray, "1 ${Symbols.PPM}", isVtC),
        )
    }

    val UNITS_LIST = listOf(Symbols.Ohms, Symbols.kOhms, Symbols.MOhms, Symbols.GOhms)
}
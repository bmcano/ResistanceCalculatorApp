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

    val TOLERANCE_LIST = listOf(
        DropdownItem(R.drawable.square_brown, Colors.BROWN, "${Symbols.PM}1%"),
        DropdownItem(R.drawable.square_red, Colors.RED, "${Symbols.PM}2%"),
        DropdownItem(R.drawable.square_green, Colors.GREEN, "${Symbols.PM}0.5%"),
        DropdownItem(R.drawable.square_blue, Colors.BLUE, "${Symbols.PM}0.25%"),
        DropdownItem(R.drawable.square_violet, Colors.VIOLET, "${Symbols.PM}0.1%"),
        DropdownItem(R.drawable.square_gray, Colors.GRAY, "${Symbols.PM}0.05%"),
        DropdownItem(R.drawable.square_gold, Colors.GOLD, "${Symbols.PM}5%"),
        DropdownItem(R.drawable.square_silver, Colors.SILVER, "${Symbols.PM}10%"),
    )

    val PPM_LIST = listOf(
        DropdownItem(R.drawable.square_black, Colors.BLACK, "250 ${Symbols.PPM}"),
        DropdownItem(R.drawable.square_brown, Colors.BROWN, "100 ${Symbols.PPM}"),
        DropdownItem(R.drawable.square_red, Colors.RED, "50 ${Symbols.PPM}"),
        DropdownItem(R.drawable.square_orange, Colors.ORANGE, "15 ${Symbols.PPM}"),
        DropdownItem(R.drawable.square_yellow, Colors.YELLOW, "25 ${Symbols.PPM}"),
        DropdownItem(R.drawable.square_green, Colors.GREEN, "20 ${Symbols.PPM}"),
        DropdownItem(R.drawable.square_blue, Colors.BLUE, "10 ${Symbols.PPM}"),
        DropdownItem(R.drawable.square_violet, Colors.VIOLET, "5 ${Symbols.PPM}"),
        DropdownItem(R.drawable.square_gray, Colors.GRAY, "1 ${Symbols.PPM}"),
    )

    val UNITS_LIST = listOf(Symbols.OHMS, Symbols.KOHMS, Symbols.MOHMS, Symbols.GOHMS)
}
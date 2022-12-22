package com.brandoncano.resistancecalculator

/**
 * @author Brandon
 *
 * Job: Holds all the string values to save and access save state data
 */
enum class StateData(val name_: String, val key_: String) {

    // color to value
    RESISTANCE_CTV("screenText1","screen text1"),
    BUTTON_SELECTION_CTV("buttonSelection1", "button selection1"),
    SIGFIG_BAND_ONE_CTV("numBand1","num band1"),
    SIGFIG_BAND_TWO_CTV("numBand2","num band2"),
    SIGFIG_BAND_THREE_CTV("numBand3","num band3"),
    MULTIPLIER_BAND_CTV("multiplierBand1","multiplier band1"),
    TOLERANCE_BAND_CTV("toleranceBand1","tolerance band1"),
    PPM_BAND_CTV("ppmBand1","ppm band1"),

    // value to color
    RESISTANCE_VTC("screenText2", "screen text2"),
    BUTTON_SELECTION_VTC("buttonSelection2", "button selection2"),
    USER_INPUT_VTC("UserInput", "user input"),
    UNITS_DROPDOWN_VTC("unitsDropDown", "units dropDown"),
    TOLERANCE_DROPDOWN_VTC("toleranceDropDown", "tolerance dropDown"),
    PPM_DROPDOWN_VTC("ppmDropDown", "ppm dropDown")
}
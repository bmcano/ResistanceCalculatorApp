package com.brandoncano.resistancecalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Job: Holds the names and keys for all shared_prefs data.
 *
 * Notes:
 *   Data is saved as xml files with mapping, where name_ -> file name; key_ -> key in map.
 *   Device File Explorer -> data -> data -> com.brandoncano.resistancecalculator -> shared_prefs
 */
enum class StateData(private val name_: String, private val key_: String) {

    // color to value
    RESISTANCE_CTV("screenText1", "screen text1"),
    BUTTON_SELECTION_CTV("buttonSelection1", "button selection1"),
    SIGFIG_BAND_ONE_CTV("numBand1", "num band1"),
    SIGFIG_BAND_TWO_CTV("numBand2", "num band2"),
    SIGFIG_BAND_THREE_CTV("numBand3", "num band3"),
    MULTIPLIER_BAND_CTV("multiplierBand1", "multiplier band1"),
    TOLERANCE_BAND_CTV("toleranceBand1", "tolerance band1"),
    PPM_BAND_CTV("ppmBand1", "ppm band1"),

    // value to color
    RESISTANCE_VTC("screenText2", "screen text2"),
    BUTTON_SELECTION_VTC("buttonSelection2", "button selection2"),
    USER_INPUT_VTC("UserInput", "user input"),
    UNITS_DROPDOWN_VTC("unitsDropDown", "units dropDown"),
    TOLERANCE_DROPDOWN_VTC("toleranceDropDown", "tolerance dropDown"),
    PPM_DROPDOWN_VTC("ppmDropDown", "ppm dropDown"),

    ; // methods to save and load the data as strings

    fun saveData(context: Context, input: String) {
        val sharedPreferences = context.getSharedPreferences(name_, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(input)
        editor.putString(key_, json)
        editor.apply()
    }

    fun loadData(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(name_, AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(key_, null)
        val type: Type = object : TypeToken<String?>() {}.type
        return gson.fromJson<String?>(json, type) ?: return ""
    }
}
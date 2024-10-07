package com.brandoncano.resistancecalculator.data

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

enum class ThemePreferences(private val _name: String, private val _key: String) {

    THEME_PREFERENCE("app_settings", "theme_preference");

    fun saveIntData(context: Context, input: Int) {
        val sharedPreferences = context.getSharedPreferences(_name, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(_key, input)
        editor.apply()
    }

    fun loadIntData(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences(_name, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt(_key, 0) // Default to 0 (SYSTEM_DEFAULT)
    }
}

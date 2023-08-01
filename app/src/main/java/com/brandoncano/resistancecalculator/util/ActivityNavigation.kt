package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.brandoncano.resistancecalculator.ui.AboutActivity
import com.brandoncano.resistancecalculator.ui.ColorToValueActivity
import com.brandoncano.resistancecalculator.ui.ValueToColorActivity

/**
 * Job: will navigate to a different activity
 */
object ActivityNavigation {

    fun toColorToValue(context: Context) {
        val intent = Intent(context, ColorToValueActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(context, intent, null)
    }

    fun toValueToColor(context: Context) {
        val intent = Intent(context, ValueToColorActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(context, intent, null)
    }

    fun toAbout(context: Context) {
        val intent = Intent(context, AboutActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(context, intent, null)
    }
}
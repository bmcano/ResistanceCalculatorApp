package com.brandoncano.resistancecalculator.util

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.resistor.ResistorImage
import com.brandoncano.resistancecalculator.ui.AboutActivity
import com.brandoncano.resistancecalculator.ui.ColorToValueActivity
import com.brandoncano.resistancecalculator.ui.ValueToColorActivity

/**
 * Job: Extension functions for the activities.
 */
fun AppCompatActivity.setupActionBar(title: Int) {
    val actionBar: ActionBar? = supportActionBar
    if (actionBar != null) {
        val colorDrawable = ColorDrawable(getColor(R.color.orange_primary))
        actionBar.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(title)
        actionBar.elevation = 4F
    }
}

fun AppCompatActivity.createResistorImage(): ResistorImage {
    return ResistorImage(
        findViewById(R.id.r_p2_band1),
        findViewById(R.id.r_p4_band2),
        findViewById(R.id.r_p6_band3),
        findViewById(R.id.r_p8_band4),
        findViewById(R.id.r_p10_band5),
        findViewById(R.id.r_p12_band6)
    )
}

fun AppCompatActivity.toAboutActivity() {
    val intent = Intent(this, AboutActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    startActivity(intent)
}

fun AppCompatActivity.toColorToValueActivity() {
    val intent = Intent(this, ColorToValueActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
   startActivity(intent)
}

fun AppCompatActivity.toValueToColorActivity() {
    val intent = Intent(this, ValueToColorActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    startActivity(intent)
}

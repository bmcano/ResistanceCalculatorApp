package com.brandoncano.resistancecalculator.util

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.R

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

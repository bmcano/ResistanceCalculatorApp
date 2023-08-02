package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.util.PlayStore
import com.brandoncano.resistancecalculator.util.setupActionBar

/**
 * Job: Activity for about page of the app.
 */
@Suppress("UNUSED_PARAMETER")
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupActionBar(R.string.about)
    }

    fun rateApp(view: View) {
        PlayStore.openResistorApp(this)
    }

    fun viewCapacitorApp(view: View) {
        PlayStore.openCapacitorApp(this)
    }
}

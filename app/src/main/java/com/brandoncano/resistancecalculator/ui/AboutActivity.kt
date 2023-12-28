package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.util.OpenLink
import com.brandoncano.resistancecalculator.util.setupActionBar

/**
 * Job: Activity for about page of the app.
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupActionBar(R.string.about)

        val rateUsButton: Button = findViewById(R.id.rate_us_button)
        rateUsButton.setOnClickListener { OpenLink.openResistorApp(this) }
        val viewCapacitorAppButton: Button = findViewById(R.id.view_capacitor_app_button)
        viewCapacitorAppButton.setOnClickListener { OpenLink.openCapacitorApp(this) }
        val iecButton: Button = findViewById(R.id.iec_button)
        iecButton.setOnClickListener { OpenLink.openIECWebpage(this) }
    }
}

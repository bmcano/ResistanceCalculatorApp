package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.util.ActivityNavigation
import com.brandoncano.resistancecalculator.util.EmailFeedback
import com.brandoncano.resistancecalculator.util.setupActionBar

/**
 * Job: Activity for starting screen of the app.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar(R.string.app_name)
    }

    override fun onResume() {
        super.onResume()
        // if using android 7.1 or lower, then hide the app icon to prevent crashing
        val appIconImageView: ImageView = findViewById(R.id.circular_app_icon_main_activity)
        if (android.os.Build.VERSION.SDK_INT < 26) {
            appIconImageView.visibility = View.GONE
        }

        val colorToValueButton: Button = findViewById(R.id.color_to_value_button)
        val valueToColorButton: Button = findViewById(R.id.value_to_color_button)
        colorToValueButton.setOnClickListener { ActivityNavigation.toColorToValue(this) }
        valueToColorButton.setOnClickListener { ActivityNavigation.toValueToColor(this) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.feedback -> EmailFeedback.execute(this)
            R.id.about_item -> ActivityNavigation.toAbout(this)
        }
        return super.onOptionsItemSelected(item)
    }
}

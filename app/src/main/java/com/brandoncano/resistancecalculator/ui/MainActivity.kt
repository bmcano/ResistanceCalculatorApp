package com.brandoncano.resistancecalculator.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.util.ActivityNavigation
import com.brandoncano.resistancecalculator.util.EmailFeedback

/**
 * Job: Activity for starting screen of the app.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val colorDrawable = ColorDrawable(getColor(R.color.orange_primary))
            actionBar.setBackgroundDrawable(colorDrawable)
            actionBar.title = getString(R.string.app_name)
            actionBar.elevation = 4F
        }
    }

    override fun onResume() {
        super.onResume()
        layoutSetup()
        buttonSetup()
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

    private fun layoutSetup() {
        val appIconImageView: ImageView = findViewById(R.id.circular_app_icon_main_activity)
        // if user is using android 7.1 or lower, then we hide the app icon to prevent crashing
        if (android.os.Build.VERSION.SDK_INT < 26) {
            appIconImageView.visibility = View.GONE
        }
    }

    private fun buttonSetup() {
        val colorToValueButton: Button = findViewById(R.id.color_to_value_button)
        val valueToColorButton: Button = findViewById(R.id.value_to_color_button)
        colorToValueButton.setOnClickListener { ActivityNavigation.toColorToValue(this) }
        valueToColorButton.setOnClickListener { ActivityNavigation.toValueToColor(this) }
    }
}

package com.brandoncano.resistancecalculator

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

/**
 * Job: activity for the home screen page
 *
 * @author: Brandon
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // sets up the action bar
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(R.string.app_name)

        buttonSetup()
    }

    override fun onResume() {
        super.onResume()
        buttonSetup()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.feedback -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = MenuFunctions.feedback()
                startActivity(intent)
                true
            }

            R.id.about_item -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun buttonSetup() {
        val colorToValueButton: Button = findViewById(R.id.color_to_value_button)
        val valueToColorButton: Button = findViewById(R.id.value_to_color_button)

        colorToValueButton.setOnClickListener {
            val intent = Intent(this, ColorToValueActivity::class.java)
            startActivity(intent)
        }

        valueToColorButton.setOnClickListener {
            val intent = Intent(this, ValueToColorActivity::class.java)
            startActivity(intent)
        }
    }
}

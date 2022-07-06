package com.brandoncano.resistancecalculator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.brandoncano.resistancecalculator.util.ColorFinder

/**
 * Job: activity for the about page
 *
 * @author: Brandon
 */

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // sets up the action bar correctly
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(R.string.about)

        makeResistorImage()
    }

    // fun little easter egg
    @Suppress("UNUSED_PARAMETER")
    fun easterEgg(view: View) {
        makeResistorImage()
    }

    private fun makeResistorImage() {
        val numBand1: ImageView = findViewById(R.id.r_band_1)
        val numBand2: ImageView = findViewById(R.id.r_band_2)
        val numBand3: ImageView = findViewById(R.id.r_band_3)
        val multiplierBand: ImageView = findViewById(R.id.r_band_4)
        val toleranceBand: ImageView = findViewById(R.id.r_band_5)
        val ppmBand: ImageView = findViewById(R.id.r_band_6)

        numBand1.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
        numBand2.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
        multiplierBand.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
        toleranceBand.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))

        when((4..6).random()) {
            4 -> {
                numBand3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
                ppmBand.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
            }
            5 -> {
                numBand3.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
                ppmBand.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
            }
            6 -> {
                numBand3.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
                ppmBand.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
            }
        }
    }
}

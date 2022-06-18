package com.example.resistancecalculator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat

class AboutActivity : AppCompatActivity() {

    private lateinit var numBand1: ImageView
    private lateinit var numBand2: ImageView
    private lateinit var numBand3: ImageView
    private lateinit var multiplierBand: ImageView
    private lateinit var toleranceBand: ImageView
    private lateinit var ppmBand: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // sets up the action bar correctly
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(R.string.about)

        // changeable images
        numBand1 = findViewById(R.id.r_band_1)
        numBand2 = findViewById(R.id.r_band_2)
        numBand3 = findViewById(R.id.r_band_3)
        multiplierBand = findViewById(R.id.r_band_4)
        toleranceBand = findViewById(R.id.r_band_5)
        ppmBand = findViewById(R.id.r_band_6)

        makeResistorImage()
    }

    // fun little easter egg
    @Suppress("UNUSED_PARAMETER")
    fun easterEgg(view: View) {
        makeResistorImage()
    }

    private fun makeResistorImage() {
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

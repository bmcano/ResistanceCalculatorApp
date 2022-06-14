package com.example.resistancecalculator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBar

@Suppress("DEPRECATION")
class AboutActivity : AppCompatActivity() {

    private lateinit var band1: ImageView
    private lateinit var band2: ImageView
    private lateinit var band3: ImageView
    private lateinit var band4: ImageView
    private lateinit var band5: ImageView
    private lateinit var band6: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // sets up the action bar correctly
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(R.string.about)

        // changeable images
        band1 = findViewById(R.id.r_band_1)
        band2 = findViewById(R.id.r_band_2)
        band3 = findViewById(R.id.r_band_3)
        band4 = findViewById(R.id.r_band_4)
        band5 = findViewById(R.id.r_band_5)
        band6 = findViewById(R.id.r_band_6)

        makeResistorImage()
    }

    // fun little easter egg
    @Suppress("UNUSED_PARAMETER")
    fun easterEgg(view: View) {
        makeResistorImage()
    }

    // TODO - make the numbers truly random
    private fun makeResistorImage() {
        band1.setColorFilter(resources.getColor(ColorFinder.randomColor()))
        band2.setColorFilter(resources.getColor(ColorFinder.randomColor()))
        band4.setColorFilter(resources.getColor(ColorFinder.randomColor()))
        band5.setColorFilter(resources.getColor(ColorFinder.randomColor()))

        when((4..6).random()) {
            4 -> {
                band3.setColorFilter(resources.getColor(ColorFinder.bandColor()))
                band6.setColorFilter(resources.getColor(ColorFinder.bandColor()))
            }
            5 -> {
                band3.setColorFilter(resources.getColor(ColorFinder.randomColor()))
                band6.setColorFilter(resources.getColor(ColorFinder.bandColor()))
            }
            6 -> {
                band3.setColorFilter(resources.getColor(ColorFinder.randomColor()))
                band6.setColorFilter(resources.getColor(ColorFinder.randomColor()))
            }
        }
    }
}

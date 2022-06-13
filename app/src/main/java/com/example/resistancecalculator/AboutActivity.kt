package com.example.resistancecalculator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    // TODO - will make color accurate resistors later
    private fun makeResistorImage() {
        band1.setColorFilter(resources.getColor(randomColor()))
        band2.setColorFilter(resources.getColor(randomColor()))
        band3.setColorFilter(resources.getColor(randomColor()))
        band4.setColorFilter(resources.getColor(randomColor()))

        when((4..6).random()) {
            4 -> { /* do nothing */ }
            5 -> {
                band5.setColorFilter(resources.getColor(randomColor()))
            }
            6 -> {
                band5.setColorFilter(resources.getColor(randomColor()))
                band6.setColorFilter(resources.getColor(randomColor()))
            }
        }
    }

    private fun randomColor() : Int {
        return when((1..12).random()) {
            1 -> R.color.red32
            2 -> R.color.orange32
            3 -> R.color.yellow32
            4 -> R.color.gold32
            5 -> R.color.green32
            6 -> R.color.blue32
            7 -> R.color.violet32
            8 -> R.color.white32
            9 -> R.color.silver32
            10 -> R.color.gray
            11 -> R.color.black32
            12 -> R.color.brown32
            else -> { R.color.black32 }
        }
    }
}

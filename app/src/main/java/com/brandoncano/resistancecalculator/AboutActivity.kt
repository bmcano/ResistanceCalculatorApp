package com.brandoncano.resistancecalculator

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.brandoncano.resistancecalculator.util.ColorFinder

/**
 * Job: Activity for about page of the app.
 */
@Suppress("UNUSED_PARAMETER")
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val colorDrawable = ColorDrawable(getColor(R.color.orange_primary))
            actionBar.setBackgroundDrawable(colorDrawable)
            actionBar.title = getString(R.string.about)
            actionBar.elevation = 4F
        }

        makeRandomResistorImage()
    }

    fun easterEgg(view: View) = makeRandomResistorImage()

    fun rateApp(view: View) {
        val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator&pli=1")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun makeRandomResistorImage() {
        val numBand1: ImageView = findViewById(R.id.r_p2_band1)
        val numBand2: ImageView = findViewById(R.id.r_p4_band2)
        val numBand3: ImageView = findViewById(R.id.r_p6_band3)
        val multiplierBand: ImageView = findViewById(R.id.r_p8_band4)
        val toleranceBand: ImageView = findViewById(R.id.r_p10_band5)
        val ppmBand: ImageView = findViewById(R.id.r_p12_band6)

        setRandomColorFilter(numBand1)
        setRandomColorFilter(numBand2)
        setRandomColorFilter(multiplierBand)
        setRandomColorFilter(toleranceBand)

        when ((4..6).random()) {
            4 -> {
                setRandomColorFilter(numBand3, R.color.resistor_blank)
                setRandomColorFilter(ppmBand, R.color.resistor_blank)
            } 5 -> {
                setRandomColorFilter(numBand3)
                setRandomColorFilter(ppmBand, R.color.resistor_blank)
            } 6 -> {
                setRandomColorFilter(numBand3)
                setRandomColorFilter(ppmBand)
            }
        }
    }

    private fun setRandomColorFilter(band: ImageView, color: Int = ColorFinder.randomColor()) {
        band.setColorFilter(ContextCompat.getColor(this, color))
    }
}

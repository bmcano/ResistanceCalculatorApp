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
 * @author Brandon
 *
 * Job: This hold the structure for the about page of the app.
 */
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

    @Suppress("UNUSED_PARAMETER")
    fun easterEgg(view: View) = makeRandomResistorImage()

    private fun makeRandomResistorImage() {
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

        when ((4..6).random()) {
            4 -> {
                numBand3.setColorFilter(ContextCompat.getColor(this, ColorFinder.textToColor()))
                ppmBand.setColorFilter(ContextCompat.getColor(this, ColorFinder.textToColor()))
            } 5 -> {
                numBand3.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
                ppmBand.setColorFilter(ContextCompat.getColor(this, ColorFinder.textToColor()))
            } 6 -> {
                numBand3.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
                ppmBand.setColorFilter(ContextCompat.getColor(this, ColorFinder.randomColor()))
            }
        }

        //Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
        //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //startActivity(intent)
    }

    fun rateApp(view: View) {
        val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.brandoncano.resistancecalculator&pli=1")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}

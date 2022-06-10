package com.example.resistancecalculator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var colorImage_1: ImageView
    private lateinit var colorImage_2: ImageView
    private lateinit var colorImage_3: ImageView
    private lateinit var colorImage_4: ImageView

    // initialize empty strings
    private var colorBand1: String = ""
    private var colorBand2: String = ""
    private var multiplierBand: String = ""
    private var toleranceBand: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()

        // sets the action bar to @color/purple_500
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#FF6200EE"))
        actionBar!!.setBackgroundDrawable(colorDrawable)


        R.layout.activity_main
    }

    override fun onResume() {
        super.onResume()
        setUp()
    }

    // initial setup method
    private fun setUp() {
        // shows the calculated resistance
        textView = findViewById(R.id.resistance_display)

        colorImage_1 = findViewById(R.id.color_image_1)
        colorImage_2 = findViewById(R.id.color_image_2)
        colorImage_3 = findViewById(R.id.color_image_3)
        colorImage_4 = findViewById(R.id.color_image_4)

        // color band 1
        val spinner1: Spinner = findViewById(R.id.color_band_1)
        setSpinner(spinner1, R.array.color_bands)

        // color band 2
        val spinner2: Spinner = findViewById(R.id.color_band_2)
        setSpinner(spinner2, R.array.color_bands)

        // multiplier band
        val spinner3: Spinner = findViewById(R.id.multiplier_band)
        setSpinner(spinner3, R.array.multiplier_band)

        // tolerance band
        val spinner4: Spinner = findViewById(R.id.tolerance_band)
        setSpinner(spinner4, R.array.tolerance_band)
    }

    // private method to create and set the adapters for each spinner,
    // also includes the onItemSelectedListener
    private fun setSpinner(spinner: Spinner, array: Int) {
        ArrayAdapter.createFromResource(
            this,
            array,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                when(parent!!.id) {
                    R.id.color_band_1 -> {
                        colorBand1 = parent.getItemAtPosition(position).toString()
                        colorImage_1.setImageResource(imageColor(colorBand1))
                    }
                    R.id.color_band_2 -> {
                        colorBand2 = parent.getItemAtPosition(position).toString()
                        colorImage_2.setImageResource(imageColor(colorBand2))
                    }
                    R.id.multiplier_band -> {
                        multiplierBand = parent.getItemAtPosition(position).toString()
                        colorImage_3.setImageResource(imageColor(multiplierBand))
                    }
                    R.id.tolerance_band -> {
                        toleranceBand = parent.getItemAtPosition(position).toString()
                        colorImage_4.setImageResource(imageColor(toleranceBand))
                    }
                }
                textView.text = calcResistance(colorBand1, colorBand2, multiplierBand, toleranceBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        }
    }

    // take the color name from the string arrays and finds the correct id
    private fun imageColor(color: String): Int{
        return when(color) {
            "Black" -> R.drawable.black
            "Blue" -> R.drawable.blue
            "Brown" -> R.drawable.brown
            "Gold" -> R.drawable.gold
            "Gray" -> R.drawable.gray
            "Green" -> R.drawable.green
            "Orange" -> R.drawable.orange
            "Red" -> R.drawable.red
            "Silver" -> R.drawable.silver
            "Violet" -> R.drawable.violet
            "White" -> R.drawable.white
            "Yellow" -> R.drawable.yellow
            else -> { R.drawable.black }
        }
    }
}
package com.example.resistancecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    private var colorBand1: String = ""
    private var colorBand2: String = ""
    private var multiplierBand: String = ""
    private var toleranceBand: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    override fun onResume() {
        super.onResume()
        setUp()
    }

    // initial setup method
    private fun setUp() {
        // shows the calculated resistance
        textView = findViewById(R.id.resistance_display)

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
                    R.id.color_band_1 -> colorBand1 = parent.getItemAtPosition(position).toString()
                    R.id.color_band_2 -> colorBand2 = parent.getItemAtPosition(position).toString()
                    R.id.multiplier_band-> multiplierBand = parent.getItemAtPosition(position).toString()
                    R.id.tolerance_band -> toleranceBand = parent.getItemAtPosition(position).toString()
                    else -> { }
                }
                // colorBand1 = parent!!.getItemAtPosition(position).toString()
                textView.text = calcResistance(colorBand1, colorBand2, multiplierBand, toleranceBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        }
    }
}
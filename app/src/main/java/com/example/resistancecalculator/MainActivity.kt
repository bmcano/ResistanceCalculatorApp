package com.example.resistancecalculator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Toast.makeText(this@MainActivity, "${parent!!.id}", Toast.LENGTH_SHORT).show()

    private lateinit var textView: TextView
    private lateinit var colorImage1: ImageView
    private lateinit var colorImage2: ImageView
    private lateinit var colorImage3: ImageView
    private lateinit var colorImage4: ImageView

    // initialize empty strings
    private var numberBand1: String = ""
    private var numberBand2: String = ""
    private var multiplierBand: String = ""
    private var toleranceBand: String = ""

    private var newLayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(newLayout) {
            setContentView(R.layout.activity_main_new)
            setUpNew()
        } else {
            setContentView(R.layout.activity_main)
            setUp()
        }

        // sets the action bar to @color/purple_500
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#FF6200EE"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
    }

    override fun onResume() {
        super.onResume()
        if(newLayout) {
            setUpNew()
        } else {
            setUp()
        }
    }


    // new stuff for working on the new UI, will replace the old bits once it is functioning
    private fun setUpNew() {
        // will display the resistance
        textView = findViewById(R.id.resistance_display_new)


        // number band 1
        val dropDown1 : AutoCompleteTextView = findViewById(R.id.spinner1)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Bands.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDown1.setAdapter(adapter)
        }

        dropDown1.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand1 = dropDown1.adapter.getItem(position).toString()
                dropDown1.setCompoundDrawablesRelativeWithIntrinsicBounds(imageColor(numberBand1),0,0,0)
                textView.text = calcResistance(numberBand1, numberBand2, multiplierBand, toleranceBand)
            }


        // number band 2
        val dropDown2 : AutoCompleteTextView = findViewById(R.id.spinner2)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Bands.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDown2.setAdapter(adapter)
        }

        dropDown2.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand2 = dropDown2.adapter.getItem(position).toString()
                dropDown2.setCompoundDrawablesRelativeWithIntrinsicBounds(imageColor(numberBand2),0,0,0)
                textView.text = calcResistance(numberBand1, numberBand2, multiplierBand, toleranceBand)
            }


        // multiplier band
        val dropDown3 : AutoCompleteTextView = findViewById(R.id.spinner3)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Bands.MULTIPLIER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDown3.setAdapter(adapter)
        }

        dropDown3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                multiplierBand = dropDown3.adapter.getItem(position).toString()
                dropDown3.setCompoundDrawablesRelativeWithIntrinsicBounds(imageColor(multiplierBand),0,0,0)
                textView.text = calcResistance(numberBand1, numberBand2, multiplierBand, toleranceBand)
            }


        // tolerance band
        val dropDown4 : AutoCompleteTextView = findViewById(R.id.spinner4)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Bands.TOLERANCE.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDown4.setAdapter(adapter)
        }

        dropDown4.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                toleranceBand = dropDown4.adapter.getItem(position).toString()
                dropDown4.setCompoundDrawablesRelativeWithIntrinsicBounds(imageColor(toleranceBand),0,0,0)
                textView.text = calcResistance(numberBand1, numberBand2, multiplierBand, toleranceBand)
            }
    }


    /**
     *  DO NOT CHANGE BELOW: this is the original working UI, new stuff is still a work in progress
     */

    // initial setup method
    private fun setUp() {
        // shows the calculated resistance
        textView = findViewById(R.id.resistance_display)

        colorImage1 = findViewById(R.id.color_image_1)
        colorImage2 = findViewById(R.id.color_image_2)
        colorImage3 = findViewById(R.id.color_image_3)
        colorImage4 = findViewById(R.id.color_image_4)

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
                        numberBand1 = parent.getItemAtPosition(position).toString()
                        colorImage1.setImageResource(imageColor(numberBand1))
                    }
                    R.id.color_band_2 -> {
                        numberBand2 = parent.getItemAtPosition(position).toString()
                        colorImage2.setImageResource(imageColor(numberBand2))
                    }
                    R.id.multiplier_band -> {
                        multiplierBand = parent.getItemAtPosition(position).toString()
                        colorImage3.setImageResource(imageColor(multiplierBand))
                    }
                    R.id.tolerance_band -> {
                        toleranceBand = parent.getItemAtPosition(position).toString()
                        colorImage4.setImageResource(imageColor(toleranceBand))
                    }
                }
                textView.text = calcResistance(numberBand1, numberBand2, multiplierBand, toleranceBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        }
    }

    // take the color name from the string arrays and finds the correct id
    private fun imageColor(color: String): Int {
        return when(color) {
            "Black" -> R.drawable.black32
            "Blue" -> R.drawable.blue32
            "Brown" -> R.drawable.brown32
            "Gold" -> R.drawable.gold32
            "Gray" -> R.drawable.gray32
            "Green" -> R.drawable.green32
            "Orange" -> R.drawable.orange32
            "Red" -> R.drawable.red32
            "Silver" -> R.drawable.silver32
            "Violet" -> R.drawable.violet32
            "White" -> R.drawable.white32
            "Yellow" -> R.drawable.yellow32
            else -> { R.drawable.black32 }
        }
    }
}
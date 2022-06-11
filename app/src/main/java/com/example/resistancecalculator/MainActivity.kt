package com.example.resistancecalculator

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    // initialize as empty strings
    private var numberBand1: String = ""
    private var numberBand2: String = ""
    private var multiplierBand: String = ""
    private var toleranceBand: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()

        // sets the action bar to @color/purple_500
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#FF6200EE"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
    }

    override fun onResume() {
        super.onResume()
        setup()
    }


    // sets up all drop down menus, and the rest of the screen
    private fun setup() {
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
            else -> { R.drawable.blank32 }
        }
    }
}

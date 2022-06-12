package com.example.resistancecalculator

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private lateinit var dialog: Dialog

    // initialize as empty strings
    private var numberBand1: String = ""
    private var numberBand2: String = ""
    private var numberBand3: String = ""
    private var multiplierBand: String = ""
    private var toleranceBand: String = ""
    private var ppmBand: String = ""

    private lateinit var toggleDropDown3: TextInputLayout
    private lateinit var toggleDropDown6: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        buttonSetup()

        // sets the action bar color
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
    }

    override fun onResume() {
        super.onResume()
        setup()
        buttonSetup()
    }

    // options menu dropdown in top right corner
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_item -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> { return super.onOptionsItemSelected(item) }
        }
    }

    // sets up all button related events
    private fun buttonSetup() {
        toggleDropDown3 = findViewById(R.id.dropDownSelector3)
        toggleDropDown6 = findViewById(R.id.dropDownSelector6)

        // find button IDs
        button1 = findViewById(R.id.four_band)
        button2 = findViewById(R.id.five_band)
        button3 = findViewById(R.id.six_band)
        button4 = findViewById(R.id.show_charts)

        dialog = Dialog(this)
        var imageSelection = 4

        // button listeners
        // toggle four band resistor
        button1.setOnClickListener {
            button1.setBackgroundColor(getColor(R.color.green_700))

            button2.setBackgroundColor(getColor(R.color.green_500))
            button3.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown3.visibility = View.GONE
            toggleDropDown6.visibility = View.GONE

            calcResistanceHelper()
            imageSelection = 4
        }

        // toggle five band resistor
        button2.setOnClickListener {
            button2.setBackgroundColor(getColor(R.color.green_700))

            button1.setBackgroundColor(getColor(R.color.green_500))
            button3.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown3.visibility = View.VISIBLE
            toggleDropDown6.visibility = View.GONE

            calcResistanceHelper()
            imageSelection = 5
        }

        // toggle six band resistor
        button3.setOnClickListener {
            button3.setBackgroundColor(getColor(R.color.green_700))

            button1.setBackgroundColor(getColor(R.color.green_500))
            button2.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown3.visibility = View.VISIBLE
            toggleDropDown6.visibility = View.VISIBLE

            calcResistanceHelper()
            imageSelection = 6
        }

        // show pop up window
        button4.setOnClickListener {
            dialog.setContentView(
                when(imageSelection) {
                    4 -> R.layout.popup_chart_4
                    5 -> R.layout.popup_chart_5
                    6 -> R.layout.popup_chart_6
                    else -> { R.layout.popup_chart_6 }
                }
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
    }

    // sets up all drop down menus and related events
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
                calcResistanceHelper()
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
                calcResistanceHelper()
            }


        // number band 3
        val dropDown3 : AutoCompleteTextView = findViewById(R.id.spinner3)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Bands.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDown3.setAdapter(adapter)
        }

        dropDown3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand3 = dropDown3.adapter.getItem(position).toString()
                dropDown3.setCompoundDrawablesRelativeWithIntrinsicBounds(imageColor(numberBand3),0,0,0)
                calcResistanceHelper()
            }


        // multiplier band
        val dropDown4 : AutoCompleteTextView = findViewById(R.id.spinner4)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Bands.MULTIPLIER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDown4.setAdapter(adapter)
        }

        dropDown4.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                multiplierBand = dropDown4.adapter.getItem(position).toString()
                dropDown4.setCompoundDrawablesRelativeWithIntrinsicBounds(imageColor(multiplierBand),0,0,0)
                calcResistanceHelper()
            }


        // tolerance band
        val dropDown5 : AutoCompleteTextView = findViewById(R.id.spinner5)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Bands.TOLERANCE.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDown5.setAdapter(adapter)
        }

        dropDown5.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                toleranceBand = dropDown5.adapter.getItem(position).toString()
                dropDown5.setCompoundDrawablesRelativeWithIntrinsicBounds(imageColor(toleranceBand),0,0,0)
                calcResistanceHelper()
            }


        // temperature coefficient band
        val dropDown6 : AutoCompleteTextView = findViewById(R.id.spinner6)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Bands.PPM.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDown6.setAdapter(adapter)
        }

        dropDown6.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                ppmBand = dropDown6.adapter.getItem(position).toString()
                dropDown6.setCompoundDrawablesRelativeWithIntrinsicBounds(imageColor(ppmBand),0,0,0)
                calcResistanceHelper()
            }
    }

    // will determine which calculations to do
    private fun calcResistanceHelper() {
        if(toggleDropDown3.visibility == View.GONE && toggleDropDown6.visibility == View.GONE) {
            textView.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, multiplierBand, toleranceBand)
        } else if(toggleDropDown6.visibility == View.GONE){
            textView.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, numberBand3, multiplierBand, toleranceBand)
        } else {
            textView.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, numberBand3, multiplierBand, toleranceBand, ppmBand)
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

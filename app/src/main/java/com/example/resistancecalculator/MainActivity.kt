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

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    private lateinit var dialog: Dialog
    private var imageSelection = 4

    // initialize as empty strings
    private var numberBand1: String = ""
    private var numberBand2: String = ""
    private var numberBand3: String = ""
    private var multiplierBand: String = ""
    private var toleranceBand: String = ""
    private var ppmBand: String = ""

    private lateinit var toggleDropDown3: TextInputLayout
    private lateinit var toggleDropDown6: TextInputLayout

    private lateinit var band1: ImageView
    private lateinit var band2: ImageView
    private lateinit var band3: ImageView
    private lateinit var band4: ImageView
    private lateinit var band5: ImageView
    private lateinit var band6: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        imageSetup()
        buttonSetup()

        // sets the action bar color
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
    }

    override fun onResume() {
        super.onResume()
        setup()
        imageSetup()
        buttonSetup()
    }

    // options menu dropdown in top right corner
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.show_resistor_charts -> {
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
                return true
            }
            R.id.about_item -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> { super.onOptionsItemSelected(item) }
        }
    }

    private fun imageSetup() {
        band1 = findViewById(R.id.r_band_1)
        band2 = findViewById(R.id.r_band_2)
        band3 = findViewById(R.id.r_band_3)
        band4 = findViewById(R.id.r_band_4)
        band5 = findViewById(R.id.r_band_5)
        band6 = findViewById(R.id.r_band_6)
    }


    // sets up all button related events
    private fun buttonSetup() {
        toggleDropDown3 = findViewById(R.id.dropDownSelector3)
        toggleDropDown6 = findViewById(R.id.dropDownSelector6)

        // find button IDs
        button1 = findViewById(R.id.four_band)
        button2 = findViewById(R.id.five_band)
        button3 = findViewById(R.id.six_band)

        dialog = Dialog(this)

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

            band3.setColorFilter(resources.getColor(ColorFinder.bandColor()))
            band6.setColorFilter(resources.getColor(ColorFinder.bandColor()))
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

            band3.setColorFilter(resources.getColor(ColorFinder.bandColor(numberBand3)))
            band6.setColorFilter(resources.getColor(ColorFinder.bandColor()))
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

            band3.setColorFilter(resources.getColor(ColorFinder.bandColor(numberBand3)))
            band6.setColorFilter(resources.getColor(ColorFinder.bandColor(ppmBand)))
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
                dropDown1.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand1),0,0,0)
                calcResistanceHelper()
                band1.setColorFilter(resources.getColor(ColorFinder.bandColor(numberBand1)))
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
                dropDown2.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand2),0,0,0)
                calcResistanceHelper()
                band2.setColorFilter(resources.getColor(ColorFinder.bandColor(numberBand2)))
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
                dropDown3.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand3),0,0,0)
                calcResistanceHelper()
                band3.setColorFilter(resources.getColor(ColorFinder.bandColor(numberBand3)))
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
                dropDown4.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(multiplierBand),0,0,0)
                calcResistanceHelper()
                band4.setColorFilter(resources.getColor(ColorFinder.bandColor(multiplierBand)))
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
                dropDown5.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(toleranceBand),0,0,0)
                calcResistanceHelper()
                band5.setColorFilter(resources.getColor(ColorFinder.bandColor(toleranceBand)))
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
                dropDown6.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(ppmBand),0,0,0)
                calcResistanceHelper()
                band6.setColorFilter(resources.getColor(ColorFinder.bandColor(ppmBand)))
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
}

package com.brandoncano.resistancecalculator

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout

/**
 * Job: activity for the color to value page
 *
 * @author: Brandon
 */

class ColorToValueActivity : AppCompatActivity() {
    private lateinit var screenText: TextView
    private var imageSelection = 4

    private var numberBand1: String = ""
    private var numberBand2: String = ""
    private var numberBand3: String = ""
    private var multiplierBand: String = ""
    private var toleranceBand: String = ""
    private var ppmBand: String = ""

    private lateinit var toggleDropDownNumberBand3: TextInputLayout
    private lateinit var toggleDropDownPPM: TextInputLayout

    private lateinit var bandImgae1: ImageView
    private lateinit var bandImage2: ImageView
    private lateinit var bandImage3: ImageView
    private lateinit var bandImage4: ImageView
    private lateinit var bandImage5: ImageView
    private lateinit var bandImage6: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_to_value)

        // sets up the action bar
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(R.string.color_to_value)

        dropDownSetup()
        idSetup()
        buttonSetup()
    }

    override fun onResume() {
        super.onResume()
        dropDownSetup()
        idSetup()
        buttonSetup()
    }

    // create menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown_ctv, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // menu options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.value_to_color -> {
                super.finish()
                val intent = Intent(this, ValueToColorActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.show_resistor_charts -> {
                MenuFunctions.showResistorCharts(this, imageSelection)
                true
            }

            R.id.share_item -> {
                val intent = MenuFunctions.shareItemCTV(imageSelection, screenText, numberBand1, numberBand2, numberBand3, multiplierBand, toleranceBand, ppmBand)
                startActivity(Intent.createChooser(intent, ""))
                true
            }

            R.id.feedback -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = MenuFunctions.feedback()
                startActivity(intent)
                true
            }

            R.id.about_item -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> { super.onOptionsItemSelected(item) }
        }
    }

    private fun idSetup() {
        screenText = findViewById(R.id.resistance_display_new)
        bandImgae1 = findViewById(R.id.r_band_1)
        bandImage2 = findViewById(R.id.r_band_2)
        bandImage3 = findViewById(R.id.r_band_3)
        bandImage4 = findViewById(R.id.r_band_4)
        bandImage5 = findViewById(R.id.r_band_5)
        bandImage6 = findViewById(R.id.r_band_6)
        toggleDropDownNumberBand3 = findViewById(R.id.dropDownSelector3)
        toggleDropDownPPM = findViewById(R.id.dropDownSelector6)
    }

    private fun buttonSetup() {
        val fourBandButton: Button = findViewById(R.id.four_band)
        val fiveBandButton: Button = findViewById(R.id.five_band)
        val sixBandButton: Button = findViewById(R.id.six_band)

        // toggle four band resistor
        fourBandButton.setOnClickListener {
            buttonListener(fourBandButton, fiveBandButton, sixBandButton, 4, View.GONE, View.GONE)

            bandImage3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
            bandImage6.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
        }

        // toggle five band resistor
        fiveBandButton.setOnClickListener {
            buttonListener(fiveBandButton, fourBandButton, sixBandButton, 5, View.VISIBLE, View.GONE)

            bandImage3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand3)))
            bandImage6.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
        }

        // toggle six band resistor
        sixBandButton.setOnClickListener {
            buttonListener(sixBandButton, fourBandButton, fiveBandButton, 6, View.VISIBLE, View.VISIBLE)

            bandImage3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand3)))
            bandImage6.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(ppmBand)))
        }
    }

    private fun buttonListener(selectedBtn: Button, btn1: Button, btn2: Button, btnNumber: Int, view1: Int, view2: Int ) {
        selectedBtn.setBackgroundColor(getColor(R.color.green_700))
        btn1.setBackgroundColor(getColor(R.color.green_500))
        btn2.setBackgroundColor(getColor(R.color.green_500))

        toggleDropDownNumberBand3.visibility = view1
        toggleDropDownPPM.visibility = view2

        calcResistanceHelper()
        imageSelection = btnNumber
    }

    private fun dropDownSetup() {
        // number band 1
        val dropDownBand1 : AutoCompleteTextView = findViewById(R.id.spinner1)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropDownBand1.setAdapter(adapter)
        }

        dropDownBand1.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand1 = dropDownBand1.adapter.getItem(position).toString()
                dropDownBand1.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand1),0,0,0)
                calcResistanceHelper()
                bandImgae1.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand1)))
            }

        // number band 2
        val dropDownBand2 : AutoCompleteTextView = findViewById(R.id.spinner2)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownBand2.setAdapter(adapter)
        }

        dropDownBand2.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand2 = dropDownBand2.adapter.getItem(position).toString()
                dropDownBand2.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand2),0,0,0)
                calcResistanceHelper()
                bandImage2.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand2)))
            }

        // number band 3
        val dropDownBand3 : AutoCompleteTextView = findViewById(R.id.spinner3)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownBand3.setAdapter(adapter)
        }

        dropDownBand3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand3 = dropDownBand3.adapter.getItem(position).toString()
                dropDownBand3.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand3),0,0,0)
                calcResistanceHelper()
                bandImage3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand3)))
            }

        // multiplier band
        val dropDownMultiplier : AutoCompleteTextView = findViewById(R.id.spinner4)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.MULTIPLIER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownMultiplier.setAdapter(adapter)
        }

        dropDownMultiplier.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                multiplierBand = dropDownMultiplier.adapter.getItem(position).toString()
                dropDownMultiplier.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(multiplierBand),0,0,0)
                calcResistanceHelper()
                bandImage4.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(multiplierBand)))
            }

        // tolerance band
        val dropDownTolerance : AutoCompleteTextView = findViewById(R.id.spinner5)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.TOLERANCE.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownTolerance.setAdapter(adapter)
        }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                toleranceBand = dropDownTolerance.adapter.getItem(position).toString()
                dropDownTolerance.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(toleranceBand),0,0,0)
                calcResistanceHelper()
                bandImage5.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(toleranceBand)))
            }

        // temperature coefficient band
        val dropDownPPM : AutoCompleteTextView = findViewById(R.id.spinner6)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.PPM.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownPPM.setAdapter(adapter)
        }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                ppmBand = dropDownPPM.adapter.getItem(position).toString()
                dropDownPPM.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(ppmBand),0,0,0)
                calcResistanceHelper()
                bandImage6.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(ppmBand)))
            }
    }

    // will determine which calculations to do
    private fun calcResistanceHelper() {
        if(toggleDropDownNumberBand3.visibility == View.GONE && toggleDropDownPPM.visibility == View.GONE) {
            screenText.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, multiplierBand, toleranceBand)
        } else if(toggleDropDownPPM.visibility == View.GONE){
            screenText.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, numberBand3, multiplierBand, toleranceBand)
        } else {
            screenText.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, numberBand3, multiplierBand, toleranceBand, ppmBand)
        }
    }
}

package com.brandoncano.resistancecalculator

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.brandoncano.resistancecalculator.spinner.ImageTextArrayAdapter
import com.brandoncano.resistancecalculator.spinner.SpinnerContents
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.MenuFunctions
import com.brandoncano.resistancecalculator.util.ResistorFormatter
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Job: activity for the value to color page
 *
 * @author: Brandon
 */

class ValueToColorActivity : AppCompatActivity() {
    companion object {
        private const val EMPTY_STRING = ""
    }

    private lateinit var screenText: TextView
    private lateinit var numberBand1: ImageView
    private lateinit var numberBand2: ImageView
    private lateinit var numberBand3: ImageView
    private lateinit var multiplierBand: ImageView
    private lateinit var toleranceColor: ImageView
    private lateinit var ppmColor: ImageView
    private lateinit var toggleDropDown: TextInputLayout

    private var imageSelection = 4
    private var buttonCheck = EMPTY_STRING
    private var resistance = EMPTY_STRING
    private var toleranceBand: String = EMPTY_STRING
    private var ppmBand: String = EMPTY_STRING
    private var units: String = EMPTY_STRING
    private var shareColors: Array<Int> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_to_color)

        // sets up the action bar correctly
        val actionBar: ActionBar? = supportActionBar
        if(actionBar != null) {
            val colorDrawable = ColorDrawable(getColor(R.color.orange_primary))
            actionBar.setBackgroundDrawable(colorDrawable)
            actionBar.title = getString(R.string.value_to_color)
        }

        idSetup()
        dropDownSetup()
        buttonSetup()
        calculateButtonSetup()
        screenText.text = loadData("screenText2", "screen text2")
        if(screenText.text == EMPTY_STRING) screenText.text = getString(R.string.enter_value)
    }

    override fun onResume() {
        super.onResume()
        idSetup()
        dropDownSetup()
        buttonSetup()
        calculateButtonSetup()
        screenText.text = loadData("screenText2", "screen text2")
        if(screenText.text == EMPTY_STRING) screenText.text = getString(R.string.enter_value)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown_vtc, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.color_to_value -> {
                super.finish()
                val intent = Intent(this, ColorToValueActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.show_resistor_charts -> {
                MenuFunctions.showResistorCharts(this, imageSelection)
            }

            R.id.share_item -> {
                val intent = MenuFunctions.shareItemVTC(
                    imageSelection, shareColors, screenText, toleranceBand, ppmBand
                )
                startActivity(Intent.createChooser(intent, EMPTY_STRING))
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

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun idSetup() {
        screenText = findViewById(R.id.display_resistance)
        numberBand1 = findViewById(R.id.r_band_1)
        numberBand2 = findViewById(R.id.r_band_2)
        numberBand3 = findViewById(R.id.r_band_3)
        multiplierBand = findViewById(R.id.r_band_4)
        toleranceColor = findViewById(R.id.r_band_5)
        ppmColor = findViewById(R.id.r_band_6)
        toggleDropDown = findViewById(R.id.dropDownSelectorPPM)
    }

    private fun buttonSetup() {
        val fourBandButton: Button = findViewById(R.id.four_band)
        val fiveBandButton: Button = findViewById(R.id.five_band)
        val sixBandButton: Button = findViewById(R.id.six_band)

        fun loadImage(button: String) {
            resistance = loadData("UserInput", "user input")
            // fix for those with crashing screens
            if(resistance == "NotValid") {
                resistance = "51"
            }
            shareColors = ResistorFormatter.generateResistor(imageSelection, resistance, units)
            numberBand1.setColorFilter(ContextCompat.getColor(this, shareColors[0]))
            numberBand2.setColorFilter(ContextCompat.getColor(this, shareColors[1]))
            if (button == "5" || button == "6")
                numberBand3.setColorFilter(ContextCompat.getColor(this, shareColors[2]))
            multiplierBand.setColorFilter(ContextCompat.getColor(this, shareColors[3]))
            toleranceColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.toleranceColor(toleranceBand)))
            if (button == "6")
                ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor(ppmBand)))
        }

        when(loadData("buttonSelection2", "button selection2")) {
            "4" -> {
                buttonListener(fourBandButton, fiveBandButton, sixBandButton, 4, View.INVISIBLE)
                loadImage("4")
            }
            "5" -> {
                buttonListener(fiveBandButton, fourBandButton, sixBandButton, 5, View.INVISIBLE)
                loadImage("5")
            }
            "6" -> {
                buttonListener(sixBandButton, fourBandButton, fiveBandButton, 6, View.VISIBLE)
                loadImage("6")
            }
        }

        // toggle four band resistor
        fourBandButton.setOnClickListener {
            buttonListener(fourBandButton, fiveBandButton, sixBandButton, 4, View.INVISIBLE)
        }

        // toggle five band resistor
        fiveBandButton.setOnClickListener {
            buttonListener(fiveBandButton, fourBandButton, sixBandButton, 5, View.INVISIBLE)
        }

        // toggle six band resistor
        sixBandButton.setOnClickListener {
            buttonListener(sixBandButton, fourBandButton, fiveBandButton, 6, View.VISIBLE)
        }
    }

    private fun buttonListener(selectedBtn: Button, btn1: Button, btn2: Button, btnNumber: Int, view: Int ) {
        selectedBtn.setBackgroundColor(getColor(R.color.mango_dark))
        btn1.setBackgroundColor(getColor(R.color.mango_primary))
        btn2.setBackgroundColor(getColor(R.color.mango_primary))

        toggleDropDown.visibility = view
        imageSelection = btnNumber
        if (resistance != EMPTY_STRING) resistance = errorFinder(buttonCheck)
        saveData("buttonSelection2", "button selection2", "$imageSelection")
    }

    private fun calculateButtonSetup() {
        val inputResistance: EditText = findViewById(R.id.enter_resistance)
        inputResistance.setText(loadData("UserInput", "user input"))
        inputResistance.doOnTextChanged { text, _, _, _ ->
            resistance = errorFinder(text.toString())
        }

        // make the resistor
        val calculateButton: Button = findViewById(R.id.calculate)
        calculateButton.setOnClickListener {
            val colors: Array<Int> = ResistorFormatter.generateResistor(imageSelection, resistance, units)
            shareColors = colors // for sharing in menu
            if (colors.isNotEmpty()) {
                numberBand1.setColorFilter(ContextCompat.getColor(this, colors[0]))
                numberBand2.setColorFilter(ContextCompat.getColor(this, colors[1]))
                numberBand3.setColorFilter(ContextCompat.getColor(this, colors[2]))
                multiplierBand.setColorFilter(ContextCompat.getColor(this, colors[3]))
                toleranceColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.toleranceColor(toleranceBand)))

                screenText.text = updateText(colors)
                saveData("screenText2", "screen text2", screenText.text.toString())
            }
            // prevents an invalid input from being saved
            if(resistance != "NotValid") {
                saveData("UserInput", "user input", resistance)
            }
            closeKeyboard()
        }
    }

    // finds any errors in the user input
    private fun errorFinder(text: String) : String {
        val textInputLayout: TextInputLayout = findViewById(R.id.edit_text_outline)
        return if (text == EMPTY_STRING || text == ".") {
            textInputLayout.error = null
            buttonCheck = EMPTY_STRING
            EMPTY_STRING
        } else if (!ResistorFormatter.isValidInput(imageSelection, text, units)) {
            textInputLayout.error = "Invalid Input"
            buttonCheck = text
            "NotValid"
        } else {
            textInputLayout.error = null
            buttonCheck = text
            text
        }
    }

    private fun updateText(colors: Array<Int>) : String {
        return when (imageSelection) {
            4 -> {
                numberBand3.setColorFilter(ContextCompat.getColor(this, R.color.resistor_blank))
                ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor()))
                "$resistance $units $toleranceBand"
            }
            5 -> {
                numberBand3.setColorFilter(ContextCompat.getColor(this, colors[2]))
                ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor()))
                "$resistance $units $toleranceBand"
            }
            6 -> {
                numberBand3.setColorFilter(ContextCompat.getColor(this, colors[2]))
                ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor(ppmBand)))
                if (ppmBand == EMPTY_STRING) "$resistance $units $toleranceBand" else "$resistance $units $toleranceBand\n$ppmBand"
            }
            else -> EMPTY_STRING
        }
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun dropDownSetup() {
        val dropDownUnits : AutoCompleteTextView = findViewById(R.id.spinnerUnits)
        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinnerTolerance)
        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinnerPPM)

        // load and set saved data
        dropDownUnits.setText(loadData("unitsDropDown", "units dropDown"))
        units = loadData("unitsDropDown", "units dropDown")

        dropDownTolerance.setText(loadData("toleranceDropDown", "tolerance dropDown"))
        toleranceBand = loadData("toleranceDropDown", "tolerance dropDown")
        dropDownTolerance.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.toleranceImage(toleranceBand),0,0,0)

        dropDownPPM.setText(loadData("ppmDropDown", "ppm dropDown"))
        ppmBand = loadData("ppmDropDown", "ppm dropDown")
        dropDownPPM.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.ppmImage(ppmBand),0,0,0)

        // create and set adapters
        ArrayAdapter(
            this,
            R.layout.spinner_units_layout,
            SpinnerContents.SimpleArray.UNITS.array
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_units_layout)
            dropDownUnits.setAdapter(adapter)
        }

        val toleranceAdapter = ImageTextArrayAdapter(this, SpinnerContents.TOLERANCE_TEXT.array)
        dropDownTolerance.setAdapter(toleranceAdapter)
        val ppmAdapter = ImageTextArrayAdapter(this, SpinnerContents.PPM_TEXT.array)
        dropDownPPM.setAdapter(ppmAdapter)

        // listeners
        dropDownUnits.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                units = dropDownUnits.adapter.getItem(position).toString()
                if (resistance != EMPTY_STRING) resistance = errorFinder(buttonCheck)
                saveData("unitsDropDown", "units dropDown", dropDownUnits.text.toString())
            }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                toleranceBand = dropDownTolerance.adapter.getItem(position).toString()
                dropDownTolerance.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.toleranceImage(toleranceBand),0,0,0)
                saveData("toleranceDropDown", "tolerance dropDown", dropDownTolerance.text.toString())
            }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                ppmBand = dropDownPPM.adapter.getItem(position).toString()
                dropDownPPM.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.ppmImage(ppmBand),0,0,0)
                saveData("ppmDropDown", "ppm dropDown", dropDownPPM.text.toString())
            }
    }

    // saves the user input
    private fun saveData(name: String, key: String, input: String) {
        val sharedPreferences = getSharedPreferences(name, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(input)
        editor.putString(key, json)
        editor.apply()
    }

    // loads the user input
    private fun loadData(name: String, key: String) : String {
        val sharedPreferences = getSharedPreferences(name, MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences!!.getString(key, null)
        val type: Type = object : TypeToken<String?>() {}.type
        return gson.fromJson<String?>(json, type) ?: return EMPTY_STRING
    }
}

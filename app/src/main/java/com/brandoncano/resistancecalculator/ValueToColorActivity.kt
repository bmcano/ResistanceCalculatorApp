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
import com.brandoncano.resistancecalculator.util.*
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * @author: Brandon
 *
 * Job: activity for the value to color page.
 */
class ValueToColorActivity : AppCompatActivity() {

    companion object {
        private const val EMPTY_STRING = ""
    }

    private lateinit var resistanceText: TextView
    private lateinit var toggleDropDown: TextInputLayout
    private lateinit var inputResistance: EditText

    private lateinit var bandImage1: ImageView
    private lateinit var bandImage2: ImageView
    private lateinit var bandImage3: ImageView
    private lateinit var bandImage4: ImageView
    private lateinit var bandImage5: ImageView
    private lateinit var bandImage6: ImageView

    private val resistor: Resistor = Resistor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_to_color)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val colorDrawable = ColorDrawable(getColor(R.color.orange_primary))
            actionBar.setBackgroundDrawable(colorDrawable)
            actionBar.title = getString(R.string.value_to_color)
            actionBar.elevation = 4F
        }

        dropDownSetup()
        generalSetup()
        buttonSetup()
        calculateButtonSetup()
    }

    override fun onResume() {
        super.onResume()
        dropDownSetup()
        generalSetup()
        buttonSetup()
        calculateButtonSetup()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown_vtc, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.color_to_value -> {
                super.finish()
                startActivity(Intent(this, ColorToValueActivity::class.java))
                return true
            }
            R.id.show_resistor_charts -> {
                return ResistorChart.show(this, resistor.getNumberOfBands())
            }
            R.id.share_item -> {
                val intent = ShareResistance.shareItemVTC(resistor, resistanceText)
                startActivity(Intent.createChooser(intent, EMPTY_STRING))
                return true
            }
            R.id.feedback -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = EmailFeedback.execute()
                startActivity(intent)
                return true
            }
            R.id.about_item -> {
                startActivity(Intent(this, AboutActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun generalSetup() {
        resistanceText = findViewById(R.id.display_resistance)
        bandImage1 = findViewById(R.id.r_band_1)
        bandImage2 = findViewById(R.id.r_band_2)
        bandImage3 = findViewById(R.id.r_band_3)
        bandImage4 = findViewById(R.id.r_band_4)
        bandImage5 = findViewById(R.id.r_band_5)
        bandImage6 = findViewById(R.id.r_band_6)
        toggleDropDown = findViewById(R.id.dropDownSelectorPPM)

        resistanceText.text = loadStateData(StateData.RESISTANCE_VTC)
        if (resistanceText.text.isEmpty()) resistanceText.text = getString(R.string.enter_value)
    }

    private fun buttonSetup() {
        val fourBandButton: Button = findViewById(R.id.four_band)
        val fiveBandButton: Button = findViewById(R.id.five_band)
        val sixBandButton: Button = findViewById(R.id.six_band)

        when (loadStateData(StateData.BUTTON_SELECTION_VTC)) {
            "4" -> {
                updateButtonSelection(fourBandButton, fiveBandButton, sixBandButton, 4, View.INVISIBLE)
            }
            "5" -> {
                updateButtonSelection(fiveBandButton, fourBandButton, sixBandButton, 5, View.INVISIBLE)
            }
            "6" -> {
                updateButtonSelection(sixBandButton, fourBandButton, fiveBandButton, 6, View.VISIBLE)
            }
        }

        resistor.resistance = loadStateData(StateData.USER_INPUT_VTC)
        // crash fix - leaving this activity with invalid input would cause a crash
        if (!(resistor.resistance == "NotValid" || resistor.resistance.isEmpty())) {
            updateResistorAndText()
        }

        // toggle four band resistor
        fourBandButton.setOnClickListener {
            updateButtonSelection(fourBandButton, fiveBandButton, sixBandButton, 4, View.INVISIBLE)
        }

        // toggle five band resistor
        fiveBandButton.setOnClickListener {
            updateButtonSelection(fiveBandButton, fourBandButton, sixBandButton, 5, View.INVISIBLE)
        }

        // toggle six band resistor
        sixBandButton.setOnClickListener {
            updateButtonSelection(sixBandButton, fourBandButton, fiveBandButton, 6, View.VISIBLE)
        }
    }

    private fun updateButtonSelection(
        selectedBtn: Button, btn1: Button, btn2: Button, btnNumber: Int, view: Int
    ) {
        selectedBtn.setBackgroundColor(getColor(R.color.mango_dark))
        btn1.setBackgroundColor(getColor(R.color.mango_primary))
        btn2.setBackgroundColor(getColor(R.color.mango_primary))

        toggleDropDown.visibility = view
        resistor.setNumberOfBands(btnNumber)
        if (resistor.resistance.isNotEmpty()) errorFinder(inputResistance.text.toString())
        saveStateData(StateData.BUTTON_SELECTION_VTC, "${resistor.getNumberOfBands()}")
    }

    private fun dropDownSetup() {
        val dropDownUnits: AutoCompleteTextView = findViewById(R.id.spinnerUnits)
        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinnerTolerance)
        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinnerPPM)

        // load and set saved data
        resistor.units = loadStateData(StateData.UNITS_DROPDOWN_VTC)
        dropDownUnits.setText(resistor.units)

        resistor.toleranceValue = loadStateData(StateData.TOLERANCE_DROPDOWN_VTC)
        dropDownTolerance.setText(resistor.toleranceValue)
        setDropDownDrawable(dropDownTolerance, resistor.toleranceValue)

        resistor.ppmValue = loadStateData(StateData.PPM_DROPDOWN_VTC)
        dropDownPPM.setText(resistor.ppmValue)
        setDropDownDrawable(dropDownPPM, resistor.ppmValue)

        // create and set dropdown adapters
        ArrayAdapter(
            this, R.layout.spinner_units_layout, SpinnerContents.unitsArray
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_units_layout)
            dropDownUnits.setAdapter(adapter)
        }

        val toleranceAdapter = ImageTextArrayAdapter(this, SpinnerContents.toleranceTextArray)
        dropDownTolerance.setAdapter(toleranceAdapter)

        val ppmAdapter = ImageTextArrayAdapter(this, SpinnerContents.ppmTextArray)
        dropDownPPM.setAdapter(ppmAdapter)

        // dropdown listeners
        dropDownUnits.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.units = dropDownUnits.adapter.getItem(position).toString()
                if (resistor.resistance.isNotEmpty()) errorFinder(inputResistance.text.toString())
                saveStateData(StateData.UNITS_DROPDOWN_VTC, dropDownUnits.text.toString())
            }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.toleranceValue = dropDownTolerance.adapter.getItem(position).toString()
                setDropDownDrawable(dropDownTolerance, resistor.toleranceValue)
                saveStateData(StateData.TOLERANCE_DROPDOWN_VTC, dropDownTolerance.text.toString())
            }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.ppmValue = dropDownPPM.adapter.getItem(position).toString()
                setDropDownDrawable(dropDownPPM, resistor.ppmValue)
                saveStateData(StateData.PPM_DROPDOWN_VTC, dropDownPPM.text.toString())
            }
    }

    private fun calculateButtonSetup() {
        // text input setup and listener
        inputResistance = findViewById(R.id.enter_resistance)
        inputResistance.setText(loadStateData(StateData.USER_INPUT_VTC))
        inputResistance.doOnTextChanged { text, _, _, _ ->
            errorFinder(text.toString())
        }

        // button setup and listener
        val calculateButton: Button = findViewById(R.id.calculate)
        calculateButton.setOnClickListener {
            updateResistorAndText()

            // prevents an invalid input from being saved
            if (resistor.resistance != "NotValid") {
                saveStateData(StateData.USER_INPUT_VTC, resistor.resistance)
            } else {
                resistanceText.text = getString(R.string.invalid_input)
            }

            closeKeyboard()
        }
    }

    // finds any errors in the user input
    private fun errorFinder(text: String) {
        val textInputLayout: TextInputLayout = findViewById(R.id.edit_text_outline)
        resistor.resistance = if (text.isEmpty() || text == ".") {
            textInputLayout.error = null
            EMPTY_STRING
        } else if (!ResistanceInput.isValid(resistor, text)) {
            textInputLayout.error = "Invalid Input"
            "NotValid"
        } else {
            textInputLayout.error = null
            text
        }
    }

    // updates the resistor on screen and the text
    private fun updateResistorAndText() {
        ResistorFormatter.generateResistor(resistor)
        setBandColor(bandImage1, resistor.sigFigBandOne)
        setBandColor(bandImage2, resistor.sigFigBandTwo)
        setBandColor(bandImage4, resistor.multiplierBand)
        setBandColor(bandImage5, resistor.toleranceValue)

        when (resistor.getNumberOfBands()) {
            4 -> {
                setBandColor(bandImage3)
                setBandColor(bandImage6)
            }
            5 -> {
                setBandColor(bandImage3, resistor.sigFigBandThree)
                setBandColor(bandImage6)
            }
            6 -> {
                setBandColor(bandImage3, resistor.sigFigBandThree)
                setBandColor(bandImage6, resistor.ppmValue)
            }
        }
        resistanceText.text = resistor.getResistanceText()
        saveStateData(StateData.RESISTANCE_VTC, resistanceText.text.toString())
    }

    // closes the keyboard
    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // helper method to set the color of the band on screen
    private fun setBandColor(band: ImageView, colorText: String = "") {
        val color = ColorFinder.textToColor(colorText)
        band.setColorFilter(ContextCompat.getColor(this, color))
    }

    // helper method to set the drawable that appears after a selection
    private fun setDropDownDrawable(dropDown: AutoCompleteTextView, color: String) {
        dropDown.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ColorFinder.textToColoredDrawable(color), 0, 0, 0
        )
    }

    // saves the user input
    private fun saveStateData(data: StateData, input: String) {
        val sharedPreferences = getSharedPreferences(data.name_, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(input)
        editor.putString(data.key_, json)
        editor.apply()
    }

    // loads the user input
    private fun loadStateData(data: StateData): String {
        val sharedPreferences = getSharedPreferences(data.name_, MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(data.key_, null)
        val type: Type = object : TypeToken<String?>() {}.type
        return gson.fromJson<String?>(json, type) ?: return EMPTY_STRING
    }
}

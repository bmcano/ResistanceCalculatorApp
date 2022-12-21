package com.brandoncano.resistancecalculator

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
 * Job: activity for the color to value page
 */
class ColorToValueActivity : AppCompatActivity() {

    companion object {
        private const val EMPTY_STRING = ""
    }

    private var imageSelection = 4
    private lateinit var resistanceText: TextView
    private lateinit var toggleDropDownNumberBand3: TextInputLayout
    private lateinit var toggleDropDownPPM: TextInputLayout

    private var numberBand1 = EMPTY_STRING
    private var numberBand2 = EMPTY_STRING
    private var numberBand3 = EMPTY_STRING
    private var multiplierBand = EMPTY_STRING
    private var toleranceBand = EMPTY_STRING
    private var ppmBand = EMPTY_STRING

    private lateinit var bandImage1: ImageView
    private lateinit var bandImage2: ImageView
    private lateinit var bandImage3: ImageView
    private lateinit var bandImage4: ImageView
    private lateinit var bandImage5: ImageView
    private lateinit var bandImage6: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_to_value)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val colorDrawable = ColorDrawable(getColor(R.color.orange_primary))
            actionBar.setBackgroundDrawable(colorDrawable)
            actionBar.title = getString(R.string.color_to_value)
            actionBar.elevation = 4F
        }

        dropDownSetup()
        generalSetup()
        buttonSetup()
    }

    override fun onResume() {
        super.onResume()
        dropDownSetup()
        generalSetup()
        buttonSetup()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown_ctv, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // TODO - will make single purpose util objects for these, maybe a resistor data class
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.value_to_color -> {
                super.finish()
                startActivity(Intent(this, ValueToColorActivity::class.java))
                return true
            }
            R.id.show_resistor_charts -> {
                return ResistorChart.show(this, imageSelection)
            }
            R.id.share_item -> {
                val intent = MenuFunctions.shareItemCTV(
                    imageSelection, resistanceText, numberBand1, numberBand2,
                    numberBand3, multiplierBand, toleranceBand, ppmBand
                )
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
        resistanceText = findViewById(R.id.resistance_display_new)
        bandImage1 = findViewById(R.id.r_band_1)
        bandImage2 = findViewById(R.id.r_band_2)
        bandImage3 = findViewById(R.id.r_band_3)
        bandImage4 = findViewById(R.id.r_band_4)
        bandImage5 = findViewById(R.id.r_band_5)
        bandImage6 = findViewById(R.id.r_band_6)
        toggleDropDownNumberBand3 = findViewById(R.id.dropDownSelector3)
        toggleDropDownPPM = findViewById(R.id.dropDownSelector6)

        resistanceText.text = loadStateData(StateData.RESISTANCE_CTV)
        if (resistanceText.text.isEmpty()) resistanceText.text = getString(R.string.default_text)
    }

    private fun buttonSetup() {
        val fourBandButton: Button = findViewById(R.id.four_band)
        val fiveBandButton: Button = findViewById(R.id.five_band)
        val sixBandButton: Button = findViewById(R.id.six_band)

        fun loadImage(button: String) {
            setBandColor(bandImage1, ColorFinder.textToColor(numberBand1))
            setBandColor(bandImage2, ColorFinder.textToColor(numberBand2))
            setBandColor(bandImage4, ColorFinder.textToColor(multiplierBand))
            setBandColor(bandImage5, ColorFinder.textToColor(toleranceBand))

            if (button == "5" || button == "6")
                setBandColor(bandImage3, ColorFinder.textToColor(numberBand3))

            if (button == "6")
                setBandColor(bandImage6, ColorFinder.textToColor(ppmBand))
        }

        when (loadStateData(StateData.BUTTON_SELECTION_CTV)) {
            "4" -> {
                updateButtonSelection(
                    fourBandButton, fiveBandButton, sixBandButton, 4, View.GONE, View.GONE
                )
                loadImage("4")
            }
            "5" -> {
                updateButtonSelection(
                    fiveBandButton, fourBandButton, sixBandButton, 5, View.VISIBLE, View.GONE
                )
                loadImage("5")
            }
            "6" -> {
                updateButtonSelection(
                    sixBandButton, fourBandButton, fiveBandButton, 6, View.VISIBLE, View.VISIBLE
                )
                loadImage("6")
            }
        }

        // toggle four band resistor
        fourBandButton.setOnClickListener {
            updateButtonSelection(
                fourBandButton, fiveBandButton, sixBandButton, 4, View.GONE, View.GONE
            )

            setBandColor(bandImage3, ColorFinder.textToColor())
            setBandColor(bandImage6, ColorFinder.textToColor())
        }

        // toggle five band resistor
        fiveBandButton.setOnClickListener {
            updateButtonSelection(
                fiveBandButton, fourBandButton, sixBandButton, 5, View.VISIBLE, View.GONE
            )

            setBandColor(bandImage3, ColorFinder.textToColor(numberBand3))
            setBandColor(bandImage6, ColorFinder.textToColor())
        }

        // toggle six band resistor
        sixBandButton.setOnClickListener {
            updateButtonSelection(
                sixBandButton, fourBandButton, fiveBandButton, 6, View.VISIBLE, View.VISIBLE
            )

            setBandColor(bandImage3, ColorFinder.textToColor(numberBand3))
            setBandColor(bandImage6, ColorFinder.textToColor(ppmBand))
        }
    }

    private fun updateButtonSelection(
        selectedBtn: Button, btn1: Button, btn2: Button, btnNumber: Int, view1: Int, view2: Int
    ) {
        selectedBtn.setBackgroundColor(getColor(R.color.mango_dark))
        btn1.setBackgroundColor(getColor(R.color.mango_primary))
        btn2.setBackgroundColor(getColor(R.color.mango_primary))

        toggleDropDownNumberBand3.visibility = view1
        toggleDropDownPPM.visibility = view2

        imageSelection = btnNumber
        updateResistance()
        saveStateData(StateData.BUTTON_SELECTION_CTV, "$imageSelection")
    }

    private fun dropDownSetup() {
        val dropDownBand1: AutoCompleteTextView = findViewById(R.id.spinner1)
        val dropDownBand2: AutoCompleteTextView = findViewById(R.id.spinner2)
        val dropDownBand3: AutoCompleteTextView = findViewById(R.id.spinner3)
        val dropDownMultiplier: AutoCompleteTextView = findViewById(R.id.spinner4)
        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinner5)
        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinner6)

        // load and set saved data
        numberBand1 = loadStateData(StateData.SIGFIG_BAND_ONE_CTV)
        dropDownBand1.setText(numberBand1)
        setDropDownDrawable(dropDownBand1, numberBand1)

        numberBand2 = loadStateData(StateData.SIGFIG_BAND_TWO_CTV)
        dropDownBand2.setText(numberBand2)
        setDropDownDrawable(dropDownBand2, numberBand2)

        numberBand3 = loadStateData(StateData.SIGFIG_BAND_THREE_CTV)
        dropDownBand3.setText(numberBand3)
        setDropDownDrawable(dropDownBand3, numberBand3)

        multiplierBand = loadStateData(StateData.MULTIPLIER_BAND_CTV)
        dropDownMultiplier.setText(multiplierBand)
        setDropDownDrawable(dropDownMultiplier, multiplierBand)

        toleranceBand = loadStateData(StateData.TOLERANCE_BAND_CTV)
        dropDownTolerance.setText(toleranceBand)
        setDropDownDrawable(dropDownTolerance, toleranceBand)

        ppmBand = loadStateData(StateData.PPM_BAND_CTV)
        dropDownPPM.setText(ppmBand)
        setDropDownDrawable(dropDownPPM, ppmBand)

        // create and set adapters
        val numberAdapter = ImageTextArrayAdapter(this, SpinnerContents.numberArray)
        dropDownBand1.setAdapter(numberAdapter)
        dropDownBand2.setAdapter(numberAdapter)
        dropDownBand3.setAdapter(numberAdapter)

        val multiplierAdapter = ImageTextArrayAdapter(this, SpinnerContents.multiplierArray)
        dropDownMultiplier.setAdapter(multiplierAdapter)

        val toleranceAdapter = ImageTextArrayAdapter(this, SpinnerContents.toleranceArray)
        dropDownTolerance.setAdapter(toleranceAdapter)

        val ppmAdapter = ImageTextArrayAdapter(this, SpinnerContents.ppmArray)
        dropDownPPM.setAdapter(ppmAdapter)

        // listeners
        dropDownBand1.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand1 = dropDownBand1.adapter.getItem(position).toString()
                setDropDownDrawable(dropDownBand1, numberBand1)
                updateResistance()
                setBandColor(bandImage1, ColorFinder.textToColor(numberBand1))
                saveStateData(StateData.SIGFIG_BAND_ONE_CTV, dropDownBand1.text.toString())
            }

        dropDownBand2.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand2 = dropDownBand2.adapter.getItem(position).toString()
                setDropDownDrawable(dropDownBand2, numberBand2)
                updateResistance()
                setBandColor(bandImage2, ColorFinder.textToColor(numberBand2))
                saveStateData(StateData.SIGFIG_BAND_TWO_CTV, dropDownBand2.text.toString())
            }

        dropDownBand3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand3 = dropDownBand3.adapter.getItem(position).toString()
                setDropDownDrawable(dropDownBand3, numberBand3)
                updateResistance()
                setBandColor(bandImage3, ColorFinder.textToColor(numberBand3))
                saveStateData(StateData.SIGFIG_BAND_THREE_CTV, dropDownBand3.text.toString())
            }

        dropDownMultiplier.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                multiplierBand = dropDownMultiplier.adapter.getItem(position).toString()
                setDropDownDrawable(dropDownMultiplier, multiplierBand)
                updateResistance()
                setBandColor(bandImage4, ColorFinder.textToColor(multiplierBand))
                saveStateData(StateData.MULTIPLIER_BAND_CTV, dropDownMultiplier.text.toString())
            }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                toleranceBand = dropDownTolerance.adapter.getItem(position).toString()
                setDropDownDrawable(dropDownTolerance, toleranceBand)
                updateResistance()
                setBandColor(bandImage5, ColorFinder.textToColor(toleranceBand))
                saveStateData(StateData.TOLERANCE_BAND_CTV, dropDownTolerance.text.toString())
            }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                ppmBand = dropDownPPM.adapter.getItem(position).toString()
                setDropDownDrawable(dropDownPPM, ppmBand)
                updateResistance()
                setBandColor(bandImage6, ColorFinder.textToColor(ppmBand))
                saveStateData(StateData.PPM_BAND_CTV, dropDownPPM.text.toString())
            }
    }

    private fun updateResistance() {
        val resistor = Resistor(numberBand1, numberBand2, numberBand3, multiplierBand, toleranceBand, ppmBand)
        resistanceText.text = ResistanceFormatter.calculate(resistor, imageSelection)
        saveStateData(StateData.RESISTANCE_CTV, resistanceText.text.toString())
    }

    // helper method to set the color of the band on screen
    private fun setBandColor(band: ImageView, color: Int) {
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

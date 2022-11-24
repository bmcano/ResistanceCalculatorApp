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
import com.brandoncano.resistancecalculator.util.ColorFinder
import com.brandoncano.resistancecalculator.util.MenuFunctions
import com.brandoncano.resistancecalculator.util.ResistanceFormatter
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
    private lateinit var screenText: TextView
    private lateinit var toggleDropDownNumberBand3: TextInputLayout
    private lateinit var toggleDropDownPPM: TextInputLayout

    private var numberBand1: String = EMPTY_STRING
    private var numberBand2: String = EMPTY_STRING
    private var numberBand3: String = EMPTY_STRING
    private var multiplierBand: String = EMPTY_STRING
    private var toleranceBand: String = EMPTY_STRING
    private var ppmBand: String = EMPTY_STRING

    private lateinit var bandImage1: ImageView
    private lateinit var bandImage2: ImageView
    private lateinit var bandImage3: ImageView
    private lateinit var bandImage4: ImageView
    private lateinit var bandImage5: ImageView
    private lateinit var bandImage6: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_to_value)

        // sets up the action bar correctly
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val colorDrawable = ColorDrawable(getColor(R.color.orange_primary))
            actionBar.setBackgroundDrawable(colorDrawable)
            actionBar.title = getString(R.string.color_to_value)
            actionBar.elevation = 4F
        }

        dropDownSetup()
        idSetup()
        buttonSetup()
        screenText.text = loadData("screenText1", "screen text1")
        if (screenText.text == EMPTY_STRING) screenText.text = getString(R.string.default_text)
    }

    override fun onResume() {
        super.onResume()
        dropDownSetup()
        idSetup()
        buttonSetup()
        screenText.text = loadData("screenText1", "screen text1")
        if (screenText.text == EMPTY_STRING) screenText.text = getString(R.string.default_text)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown_ctv, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.value_to_color -> {
                super.finish()
                val intent = Intent(this, ValueToColorActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.show_resistor_charts -> {
                MenuFunctions.showResistorCharts(this, imageSelection)
            }

            R.id.share_item -> {
                val intent = MenuFunctions.shareItemCTV(
                    imageSelection, screenText, numberBand1, numberBand2,
                    numberBand3, multiplierBand, toleranceBand, ppmBand
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
        screenText = findViewById(R.id.resistance_display_new)
        bandImage1 = findViewById(R.id.r_band_1)
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

        fun loadImage(button: String) {
            bandImage1.setColorFilter(
                ContextCompat.getColor(
                    this,
                    ColorFinder.textToColor(numberBand1)
                )
            )
            bandImage2.setColorFilter(
                ContextCompat.getColor(
                    this,
                    ColorFinder.textToColor(numberBand2)
                )
            )
            if (button == "5" || button == "6")
                bandImage3.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        ColorFinder.textToColor(numberBand3)
                    )
                )
            bandImage4.setColorFilter(
                ContextCompat.getColor(
                    this,
                    ColorFinder.textToColor(multiplierBand)
                )
            )
            bandImage5.setColorFilter(
                ContextCompat.getColor(
                    this,
                    ColorFinder.textToColor(toleranceBand)
                )
            )
            if (button == "6")
                bandImage6.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        ColorFinder.textToColor(ppmBand)
                    )
                )
        }

        when (loadData("buttonSelection1", "button selection1")) {
            "4" -> {
                buttonListener(
                    fourBandButton,
                    fiveBandButton,
                    sixBandButton,
                    4,
                    View.GONE,
                    View.GONE
                )
                loadImage("4")
            }
            "5" -> {
                buttonListener(
                    fiveBandButton,
                    fourBandButton,
                    sixBandButton,
                    5,
                    View.VISIBLE,
                    View.GONE
                )
                loadImage("5")
            }
            "6" -> {
                buttonListener(
                    sixBandButton,
                    fourBandButton,
                    fiveBandButton,
                    6,
                    View.VISIBLE,
                    View.VISIBLE
                )
                loadImage("6")
            }
        }

        // toggle four band resistor
        fourBandButton.setOnClickListener {
            buttonListener(fourBandButton, fiveBandButton, sixBandButton, 4, View.GONE, View.GONE)

            bandImage3.setColorFilter(ContextCompat.getColor(this, ColorFinder.textToColor()))
            bandImage6.setColorFilter(ContextCompat.getColor(this, ColorFinder.textToColor()))
        }

        // toggle five band resistor
        fiveBandButton.setOnClickListener {
            buttonListener(
                fiveBandButton,
                fourBandButton,
                sixBandButton,
                5,
                View.VISIBLE,
                View.GONE
            )

            bandImage3.setColorFilter(
                ContextCompat.getColor(
                    this,
                    ColorFinder.textToColor(numberBand3)
                )
            )
            bandImage6.setColorFilter(ContextCompat.getColor(this, ColorFinder.textToColor()))
        }

        // toggle six band resistor
        sixBandButton.setOnClickListener {
            buttonListener(
                sixBandButton,
                fourBandButton,
                fiveBandButton,
                6,
                View.VISIBLE,
                View.VISIBLE
            )

            bandImage3.setColorFilter(
                ContextCompat.getColor(
                    this,
                    ColorFinder.textToColor(numberBand3)
                )
            )
            bandImage6.setColorFilter(ContextCompat.getColor(this, ColorFinder.textToColor(ppmBand)))
        }
    }

    private fun buttonListener(
        selectedBtn: Button,
        btn1: Button,
        btn2: Button,
        btnNumber: Int,
        view1: Int,
        view2: Int
    ) {
        selectedBtn.setBackgroundColor(getColor(R.color.mango_dark))
        btn1.setBackgroundColor(getColor(R.color.mango_primary))
        btn2.setBackgroundColor(getColor(R.color.mango_primary))

        toggleDropDownNumberBand3.visibility = view1
        toggleDropDownPPM.visibility = view2

        calculateResistanceHelper()
        imageSelection = btnNumber
        saveData("buttonSelection1", "button selection1", "$imageSelection")
    }

    private fun dropDownSetup() {
        val dropDownBand1: AutoCompleteTextView = findViewById(R.id.spinner1)
        val dropDownBand2: AutoCompleteTextView = findViewById(R.id.spinner2)
        val dropDownBand3: AutoCompleteTextView = findViewById(R.id.spinner3)
        val dropDownMultiplier: AutoCompleteTextView = findViewById(R.id.spinner4)
        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinner5)
        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinner6)

        // load and set saved data
        dropDownBand1.setText(loadData("numBand1", "num band1"))
        numberBand1 = loadData("numBand1", "num band1")
        dropDownBand1.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ColorFinder.textToColoredDrawable(
                numberBand1
            ), 0, 0, 0
        )

        dropDownBand2.setText(loadData("numBand2", "num band2"))
        numberBand2 = loadData("numBand2", "num band2")
        dropDownBand2.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ColorFinder.textToColoredDrawable(
                numberBand2
            ), 0, 0, 0
        )

        dropDownBand3.setText(loadData("numBand3", "num band3"))
        numberBand3 = loadData("numBand3", "num band3")
        dropDownBand3.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ColorFinder.textToColoredDrawable(
                numberBand3
            ), 0, 0, 0
        )

        dropDownMultiplier.setText(loadData("multiplierBand1", "multiplier band1"))
        multiplierBand = loadData("multiplierBand1", "multiplier band1")
        dropDownMultiplier.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ColorFinder.textToColoredDrawable(
                multiplierBand
            ), 0, 0, 0
        )

        dropDownTolerance.setText(loadData("toleranceBand1", "tolerance band1"))
        toleranceBand = loadData("toleranceBand1", "tolerance band1")
        dropDownTolerance.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ColorFinder.textToColoredDrawable(
                toleranceBand
            ), 0, 0, 0
        )

        dropDownPPM.setText(loadData("ppmBand1", "ppm band1"))
        ppmBand = loadData("ppmBand1", "ppm band1")
        dropDownPPM.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ColorFinder.textToColoredDrawable(ppmBand),
            0,
            0,
            0
        )

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
                dropDownBand1.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ColorFinder.textToColoredDrawable(
                        numberBand1
                    ), 0, 0, 0
                )
                calculateResistanceHelper()
                bandImage1.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        ColorFinder.textToColor(numberBand1)
                    )
                )
                saveData("numBand1", "num band1", dropDownBand1.text.toString())
            }

        dropDownBand2.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand2 = dropDownBand2.adapter.getItem(position).toString()
                dropDownBand2.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ColorFinder.textToColoredDrawable(
                        numberBand2
                    ), 0, 0, 0
                )
                calculateResistanceHelper()
                bandImage2.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        ColorFinder.textToColor(numberBand2)
                    )
                )
                saveData("numBand2", "num band2", dropDownBand2.text.toString())
            }

        dropDownBand3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand3 = dropDownBand3.adapter.getItem(position).toString()
                dropDownBand3.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ColorFinder.textToColoredDrawable(
                        numberBand3
                    ), 0, 0, 0
                )
                calculateResistanceHelper()
                bandImage3.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        ColorFinder.textToColor(numberBand3)
                    )
                )
                saveData("numBand3", "num band3", dropDownBand3.text.toString())
            }

        dropDownMultiplier.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                multiplierBand = dropDownMultiplier.adapter.getItem(position).toString()
                dropDownMultiplier.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ColorFinder.textToColoredDrawable(
                        multiplierBand
                    ), 0, 0, 0
                )
                calculateResistanceHelper()
                bandImage4.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        ColorFinder.textToColor(multiplierBand)
                    )
                )
                saveData("multiplierBand1", "multiplier band1", dropDownMultiplier.text.toString())
            }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                toleranceBand = dropDownTolerance.adapter.getItem(position).toString()
                dropDownTolerance.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ColorFinder.textToColoredDrawable(
                        toleranceBand
                    ), 0, 0, 0
                )
                calculateResistanceHelper()
                bandImage5.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        ColorFinder.textToColor(toleranceBand)
                    )
                )
                saveData("toleranceBand1", "tolerance band1", dropDownTolerance.text.toString())
            }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                ppmBand = dropDownPPM.adapter.getItem(position).toString()
                dropDownPPM.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ColorFinder.textToColoredDrawable(
                        ppmBand
                    ), 0, 0, 0
                )
                calculateResistanceHelper()
                bandImage6.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        ColorFinder.textToColor(ppmBand)
                    )
                )
                saveData("ppmBand1", "ppm band1", dropDownPPM.text.toString())
            }
    }

    private fun calculateResistanceHelper() {
        if (toggleDropDownNumberBand3.visibility == View.GONE && toggleDropDownPPM.visibility == View.GONE) {
            screenText.text = ResistanceFormatter.calcResistance(
                numberBand1,
                numberBand2,
                multiplierBand,
                toleranceBand
            )
        } else if (toggleDropDownPPM.visibility == View.GONE) {
            screenText.text = ResistanceFormatter.calcResistance(
                numberBand1,
                numberBand2,
                numberBand3,
                multiplierBand,
                toleranceBand
            )
        } else {
            screenText.text = ResistanceFormatter.calcResistance(
                numberBand1,
                numberBand2,
                numberBand3,
                multiplierBand,
                toleranceBand,
                ppmBand
            )
        }
        saveData("screenText1", "screen text1", screenText.text.toString())
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
    private fun loadData(name: String, key: String): String {
        val sharedPreferences = getSharedPreferences(name, MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences!!.getString(key, null)
        val type: Type = object : TypeToken<String?>() {}.type
        return gson.fromJson<String?>(json, type) ?: return EMPTY_STRING
    }
}

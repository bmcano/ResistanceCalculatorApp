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
import com.brandoncano.resistancecalculator.util.EmailFeedback
import com.brandoncano.resistancecalculator.util.ResistanceFormatter
import com.brandoncano.resistancecalculator.util.ResistorChart
import com.brandoncano.resistancecalculator.util.ShareResistance
import com.google.android.material.textfield.TextInputLayout

/**
 * Job: Activity for the color to value page.
 */
class ColorToValueActivity : AppCompatActivity() {

    companion object {
        private const val EMPTY_STRING = ""
    }

    private lateinit var resistanceText: TextView
    private lateinit var toggleDropDownNumberBand3: TextInputLayout
    private lateinit var toggleDropDownPPM: TextInputLayout

    private val resistor: Resistor = Resistor() // all bands, by default, are an empty string

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.value_to_color -> {
                super.finish()
                startActivity(Intent(this, ValueToColorActivity::class.java))
                return true
            }
            R.id.show_resistor_charts -> {
                return ResistorChart.show(this, resistor.getNumberOfBands())
            }
            R.id.share_item -> {
                val intent = ShareResistance.shareCTV(resistor, resistanceText)
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

        resistanceText.text = StateData.RESISTANCE_CTV.loadData(this)
        if (resistanceText.text.isEmpty()) resistanceText.text = getString(R.string.default_text)
    }

    private fun buttonSetup() {
        val fourBandButton: Button = findViewById(R.id.four_band)
        val fiveBandButton: Button = findViewById(R.id.five_band)
        val sixBandButton: Button = findViewById(R.id.six_band)

        fun loadImage(button: String) {
            setBandColor(bandImage1, ColorFinder.textToColor(resistor.sigFigBandOne))
            setBandColor(bandImage2, ColorFinder.textToColor(resistor.sigFigBandTwo))
            setBandColor(bandImage4, ColorFinder.textToColor(resistor.multiplierBand))
            setBandColor(bandImage5, ColorFinder.textToColor(resistor.toleranceBand))

            if (button == "5" || button == "6")
                setBandColor(bandImage3, ColorFinder.textToColor(resistor.sigFigBandThree))

            if (button == "6")
                setBandColor(bandImage6, ColorFinder.textToColor(resistor.ppmBand))
        }

        when (StateData.BUTTON_SELECTION_CTV.loadData(this)) {
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

        // toggle number of bands listener
        fourBandButton.setOnClickListener {
            updateButtonSelection(
                fourBandButton, fiveBandButton, sixBandButton, 4, View.GONE, View.GONE
            )

            setBandColor(bandImage3, ColorFinder.textToColor())
            setBandColor(bandImage6, ColorFinder.textToColor())
        }

        fiveBandButton.setOnClickListener {
            updateButtonSelection(
                fiveBandButton, fourBandButton, sixBandButton, 5, View.VISIBLE, View.GONE
            )

            setBandColor(bandImage3, ColorFinder.textToColor(resistor.sigFigBandThree))
            setBandColor(bandImage6, ColorFinder.textToColor())
        }

        sixBandButton.setOnClickListener {
            updateButtonSelection(
                sixBandButton, fourBandButton, fiveBandButton, 6, View.VISIBLE, View.VISIBLE
            )

            setBandColor(bandImage3, ColorFinder.textToColor(resistor.sigFigBandThree))
            setBandColor(bandImage6, ColorFinder.textToColor(resistor.ppmBand))
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

        resistor.setNumberOfBands(btnNumber)
        updateResistance()
        StateData.BUTTON_SELECTION_CTV.saveData(this, "${resistor.getNumberOfBands()}")
    }

    private fun dropDownSetup() {
        val dropDownBand1: AutoCompleteTextView = findViewById(R.id.spinner1)
        val dropDownBand2: AutoCompleteTextView = findViewById(R.id.spinner2)
        val dropDownBand3: AutoCompleteTextView = findViewById(R.id.spinner3)
        val dropDownMultiplier: AutoCompleteTextView = findViewById(R.id.spinner4)
        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinner5)
        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinner6)

        // load and set saved data
        resistor.sigFigBandOne = StateData.SIGFIG_BAND_ONE_CTV.loadData(this)
        dropDownBand1.setText(resistor.sigFigBandOne)
        setDropDownDrawable(dropDownBand1, resistor.sigFigBandOne)

        resistor.sigFigBandTwo = StateData.SIGFIG_BAND_TWO_CTV.loadData(this)
        dropDownBand2.setText(resistor.sigFigBandTwo)
        setDropDownDrawable(dropDownBand2, resistor.sigFigBandTwo)

        resistor.sigFigBandThree = StateData.SIGFIG_BAND_THREE_CTV.loadData(this)
        dropDownBand3.setText(resistor.sigFigBandThree)
        setDropDownDrawable(dropDownBand3, resistor.sigFigBandThree)

        resistor.multiplierBand = StateData.MULTIPLIER_BAND_CTV.loadData(this)
        dropDownMultiplier.setText(resistor.multiplierBand)
        setDropDownDrawable(dropDownMultiplier, resistor.multiplierBand)

        resistor.toleranceBand = StateData.TOLERANCE_BAND_CTV.loadData(this)
        dropDownTolerance.setText(resistor.toleranceBand)
        setDropDownDrawable(dropDownTolerance, resistor.toleranceBand)

        resistor.ppmBand = StateData.PPM_BAND_CTV.loadData(this)
        dropDownPPM.setText(resistor.ppmBand)
        setDropDownDrawable(dropDownPPM, resistor.ppmBand)

        // create and set dropdown adapters
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

        // dropdown listeners
        dropDownBand1.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.sigFigBandOne = dropDownBand1.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownBand1, resistor.sigFigBandOne, bandImage1)
                StateData.SIGFIG_BAND_ONE_CTV.saveData(this, dropDownBand1.text.toString())
            }

        dropDownBand2.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.sigFigBandTwo = dropDownBand2.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownBand2, resistor.sigFigBandTwo, bandImage2)
                StateData.SIGFIG_BAND_TWO_CTV.saveData(this, dropDownBand2.text.toString())
            }

        dropDownBand3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.sigFigBandThree = dropDownBand3.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownBand3, resistor.sigFigBandThree, bandImage3)
                StateData.SIGFIG_BAND_THREE_CTV.saveData(this, dropDownBand3.text.toString())
            }

        dropDownMultiplier.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.multiplierBand = dropDownMultiplier.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownMultiplier, resistor.multiplierBand, bandImage4)
                StateData.MULTIPLIER_BAND_CTV.saveData(this, dropDownMultiplier.text.toString())
            }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.toleranceBand = dropDownTolerance.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownTolerance, resistor.toleranceBand, bandImage5)
                StateData.TOLERANCE_BAND_CTV.saveData(this, dropDownTolerance.text.toString())
            }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.ppmBand = dropDownPPM.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownPPM, resistor.ppmBand, bandImage6)
                StateData.PPM_BAND_CTV.saveData(this, dropDownPPM.text.toString())
            }
    }

    // update the dropdown with the current selection and update the text
    private fun updateDropDownSelection(dropDown: AutoCompleteTextView, color: String, band: ImageView) {
        setDropDownDrawable(dropDown, color)
        setBandColor(band, ColorFinder.textToColor(color))
        updateResistance()
    }

    // update the resistance that displays on the screen
    private fun updateResistance() {
        resistanceText.text = ResistanceFormatter.calculate(resistor)
        StateData.RESISTANCE_CTV.saveData(this, resistanceText.text.toString())
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
}

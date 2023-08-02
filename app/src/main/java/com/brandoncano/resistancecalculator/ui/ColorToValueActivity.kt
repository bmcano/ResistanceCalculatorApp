package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.ImageTextArrayAdapter
import com.brandoncano.resistancecalculator.components.SpinnerArrays
import com.brandoncano.resistancecalculator.components.SpinnerItem
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.resistor.Resistor
import com.brandoncano.resistancecalculator.resistor.ResistorImage
import com.brandoncano.resistancecalculator.util.ActivityNavigation
import com.brandoncano.resistancecalculator.util.EmailFeedback
import com.brandoncano.resistancecalculator.util.ResistanceFormatter
import com.brandoncano.resistancecalculator.util.ShareResistance
import com.brandoncano.resistancecalculator.util.setBandColor
import com.brandoncano.resistancecalculator.util.setDropDownDrawable
import com.brandoncano.resistancecalculator.util.setupActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout

/**
 * Job: Activity for the color to value page.
 */
class ColorToValueActivity : AppCompatActivity() {

    private lateinit var resistanceTextView: TextView
    private lateinit var toggleDropDownThree: TextInputLayout
    private lateinit var toggleDropDownPPM: TextInputLayout
    private lateinit var resistorImage: ResistorImage
    private val resistor: Resistor = Resistor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_to_value)
        setupActionBar(R.string.color_to_value)
    }

    override fun onResume() {
        super.onResume()
        generalSetup()
        dropDownSetup()
        bottomNavigationSetup()
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
                ActivityNavigation.toValueToColor(this)
            }
            R.id.share_item -> ShareResistance.execute(this, resistor, resistanceTextView)
            R.id.feedback -> EmailFeedback.execute(this)
            R.id.clear_selections -> reset()
            R.id.about_item -> ActivityNavigation.toAbout(this)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generalSetup() {
        resistanceTextView = findViewById(R.id.resistance_display_ctv)
        toggleDropDownThree = findViewById(R.id.dropDownSelector3)
        toggleDropDownPPM = findViewById(R.id.dropDownSelector6)
        resistorImage = ResistorImage(
            findViewById(R.id.r_p2_band1),
            findViewById(R.id.r_p4_band2),
            findViewById(R.id.r_p6_band3),
            findViewById(R.id.r_p8_band4),
            findViewById(R.id.r_p10_band5),
            findViewById(R.id.r_p12_band6)
        )

        resistanceTextView.text = StateData.RESISTANCE_CTV.loadData(this)
        if (resistanceTextView.text.isEmpty()) {
            resistanceTextView.text = getString(R.string.default_text)
        }
    }

    private fun dropDownSetup() {
        val dropDownBand1: AutoCompleteTextView = findViewById(R.id.spinner1)
        val dropDownBand2: AutoCompleteTextView = findViewById(R.id.spinner2)
        val dropDownBand3: AutoCompleteTextView = findViewById(R.id.spinner3)
        val dropDownMultiplier: AutoCompleteTextView = findViewById(R.id.spinner4)
        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinner5)
        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinner6)

        resistor.sigFigBandOne = StateData.SIGFIG_BAND_ONE_CTV.loadData(this)
        resistor.sigFigBandTwo = StateData.SIGFIG_BAND_TWO_CTV.loadData(this)
        resistor.sigFigBandThree = StateData.SIGFIG_BAND_THREE_CTV.loadData(this)
        resistor.multiplierBand = StateData.MULTIPLIER_BAND_CTV.loadData(this)
        resistor.toleranceBand = StateData.TOLERANCE_BAND_CTV.loadData(this)
        resistor.ppmBand = StateData.PPM_BAND_CTV.loadData(this)

        dropDownBand1.onItemClickListener = dropDownOnClickListener(
            dropDownBand1, SpinnerArrays.numberArray,
            resistor.sigFigBandOne, resistorImage.band1, StateData.SIGFIG_BAND_ONE_CTV
        )

        dropDownBand2.onItemClickListener = dropDownOnClickListener(
            dropDownBand2, SpinnerArrays.numberArray,
            resistor.sigFigBandTwo, resistorImage.band2, StateData.SIGFIG_BAND_TWO_CTV
        )

        dropDownBand3.onItemClickListener = dropDownOnClickListener(
            dropDownBand3, SpinnerArrays.numberArray,
            resistor.sigFigBandThree, resistorImage.band3, StateData.SIGFIG_BAND_THREE_CTV
        )

        dropDownMultiplier.onItemClickListener = dropDownOnClickListener(
            dropDownMultiplier, SpinnerArrays.multiplierArray,
            resistor.multiplierBand, resistorImage.band4, StateData.MULTIPLIER_BAND_CTV
        )

        dropDownTolerance.onItemClickListener = dropDownOnClickListener(
            dropDownTolerance, SpinnerArrays.getToleranceArray(),
            resistor.toleranceBand, resistorImage.band5, StateData.TOLERANCE_BAND_CTV
        )

        dropDownPPM.onItemClickListener = dropDownOnClickListener(
            dropDownPPM, SpinnerArrays.getPpmArray(),
            resistor.ppmBand, resistorImage.band6, StateData.PPM_BAND_CTV
        )
    }

    private fun dropDownOnClickListener(
        dropDown: AutoCompleteTextView,
        spinnerItemArray: Array<SpinnerItem>,
        dropDownBand: String,
        dropDownImage: ImageView,
        stateData: StateData
    ): AdapterView.OnItemClickListener {
        dropDown.setText(dropDownBand)
        dropDown.setDropDownDrawable(dropDownBand)
        val adapter = ImageTextArrayAdapter(this, spinnerItemArray)
        dropDown.setAdapter(adapter)
        return AdapterView.OnItemClickListener { _, _, position, _ ->
                val selection = dropDown.adapter.getItem(position).toString()
                when (dropDownImage) {
                    resistorImage.band1 -> resistor.sigFigBandOne = selection
                    resistorImage.band2 -> resistor.sigFigBandTwo = selection
                    resistorImage.band3 -> resistor.sigFigBandThree = selection
                    resistorImage.band4 -> resistor.multiplierBand = selection
                    resistorImage.band5 -> resistor.toleranceBand = selection
                    resistorImage.band6 -> resistor.ppmBand = selection
                }
                updateDropDownSelection(dropDown, selection, dropDownImage)
                stateData.saveData(this, dropDown.text.toString())
                dropDown.clearFocus()
            }
    }

    private fun bottomNavigationSetup() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_ctv)
        val buttonSelection = StateData.BUTTON_SELECTION_CTV.loadData(this)
        when (buttonSelection) {
            "4" -> bottomNavigationView.selectedItemId = R.id.selected_four_nav
            "5" -> bottomNavigationView.selectedItemId = R.id.selected_five_nav
            "6" -> bottomNavigationView.selectedItemId = R.id.selected_six_nav
        }
        updateNavigationSelection(buttonSelection.toInt())

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.selected_four_nav -> updateNavigationSelection(4)
                R.id.selected_five_nav -> updateNavigationSelection(5)
                R.id.selected_six_nav -> updateNavigationSelection(6)
                else -> updateNavigationSelection(4)
            }
            true
        }
    }

    private fun updateNavigationSelection(numberOfBands: Int) {
        resistor.setNumberOfBands(numberOfBands)
        resistorImage.setImageColors(this, resistor)
        when (numberOfBands) {
            4 -> {
                toggleDropDownThree.visibility = View.GONE
                toggleDropDownPPM.visibility = View.GONE
            }
            5 -> {
                toggleDropDownThree.visibility = View.VISIBLE
                toggleDropDownPPM.visibility = View.GONE
            }
            6 -> {
                toggleDropDownThree.visibility = View.VISIBLE
                toggleDropDownPPM.visibility = View.VISIBLE
            }
        }
        StateData.BUTTON_SELECTION_CTV.saveData(this, "$numberOfBands")
        updateResistance()
    }

    private fun updateDropDownSelection(dropDown: AutoCompleteTextView, color: String, band: ImageView) {
        dropDown.setDropDownDrawable(color)
        band.setBandColor(this, color)
        updateResistance()
    }

    private fun updateResistance() {
        resistanceTextView.text = ResistanceFormatter.calculate(resistor)
        StateData.RESISTANCE_CTV.saveData(this, resistanceTextView.text.toString())
    }

    private fun reset() {
        StateData.RESISTANCE_CTV.clearData(this)
        StateData.BUTTON_SELECTION_CTV.saveData(this, "${resistor.getNumberOfBands()}")
        resistanceTextView.text = getString(R.string.default_text)
        resistorImage.clearResistor(this)
        dropDownSetup() // resets dropdown and resistor info
    }
}

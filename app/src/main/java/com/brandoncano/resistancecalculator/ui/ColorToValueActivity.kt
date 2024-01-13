package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.R
import com.brandoncano.resistancecalculator.components.SpinnerArrays
import com.brandoncano.resistancecalculator.resistor.ResistorCtV
import com.brandoncano.resistancecalculator.util.EmailFeedback
import com.brandoncano.resistancecalculator.util.createResistorImage
import com.brandoncano.resistancecalculator.util.setDropdownOnClickListener
import com.brandoncano.resistancecalculator.util.setupActionBar
import com.brandoncano.resistancecalculator.util.toAboutActivity
import com.brandoncano.resistancecalculator.util.toValueToColorActivity
import com.brandoncano.resistancecalculator.util.updateNavigationView
import com.brandoncano.resistancecalculator.util.updateResistance
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout

/**
 * Job: Activity for the color to value page.
 */
class ColorToValueActivity : AppCompatActivity() {

    private lateinit var resistanceTextView: TextView
    private lateinit var toggleDropDownThree: TextInputLayout
    private lateinit var toggleDropDownPPM: TextInputLayout
    private lateinit var toggleDropDownTolerance: TextInputLayout
    private val resistorImage by lazy { createResistorImage() }
    private val resistor: ResistorCtV = ResistorCtV(this)

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
                toValueToColorActivity()
            }
//            R.id.share_item -> ShareResistance.execute(this, resistor, resistanceTextView)
            R.id.feedback -> EmailFeedback.execute(this)
            R.id.clear_selections -> reset()
            R.id.about_item -> toAboutActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generalSetup() {
        resistanceTextView = findViewById(R.id.resistance_display_ctv)
        toggleDropDownThree = findViewById(R.id.dropDownSelector3)
        toggleDropDownPPM = findViewById(R.id.dropDownSelector6)
        toggleDropDownTolerance = findViewById(R.id.dropDownSelector5)
        resistor.loadData()
        resistanceTextView.updateResistance(resistor)
    }

    private fun dropDownSetup() {
        val dropDownBand1: AutoCompleteTextView = findViewById(R.id.spinner1)
        val dropDownBand2: AutoCompleteTextView = findViewById(R.id.spinner2)
        val dropDownBand3: AutoCompleteTextView = findViewById(R.id.spinner3)
        val dropDownMultiplier: AutoCompleteTextView = findViewById(R.id.spinner4)
        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinner5)
        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinner6)
        dropDownBand1.setDropdownOnClickListener(
            this, SpinnerArrays.numberArray.drop(1).toTypedArray(), resistor, resistorImage, resistanceTextView
        )
        dropDownBand2.setDropdownOnClickListener(
            this, SpinnerArrays.numberArray, resistor, resistorImage, resistanceTextView
        )
        dropDownBand3.setDropdownOnClickListener(
            this, SpinnerArrays.numberArray, resistor, resistorImage, resistanceTextView
        )
        dropDownMultiplier.setDropdownOnClickListener(
            this, SpinnerArrays.multiplierArray, resistor, resistorImage, resistanceTextView
        )
        dropDownTolerance.setDropdownOnClickListener(
            this, SpinnerArrays.getToleranceArray(), resistor, resistorImage, resistanceTextView
        )
        dropDownPPM.setDropdownOnClickListener(
            this, SpinnerArrays.getPpmArray(), resistor, resistorImage, resistanceTextView
        )
    }

    private fun bottomNavigationSetup() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_ctv)
        val buttonSelection = resistor.loadNumberOfBands()
        bottomNavigationView.updateNavigationView(buttonSelection)
        val buttonNumber = buttonSelection.toIntOrNull() ?: 4
        updateNavigationSelection(buttonNumber)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.selected_three_nav -> updateNavigationSelection(3)
                R.id.selected_five_nav -> updateNavigationSelection(5)
                R.id.selected_six_nav -> updateNavigationSelection(6)
                else -> updateNavigationSelection(4)
            }
            true
        }
    }

    private fun updateNavigationSelection(numberOfBands: Int) {
        resistor.updateNumberOfBands(numberOfBands)
        resistorImage.setImageColors(this, resistor)
        toggleDropDownThree.visibility = if (resistor.isThreeFourBand()) View.GONE else View.VISIBLE
        toggleDropDownPPM.visibility = if (resistor.isSixBand()) View.VISIBLE else View.GONE
        toggleDropDownTolerance.visibility = if (resistor.isThreeBand()) View.INVISIBLE else View.VISIBLE
        resistanceTextView.updateResistance(resistor)
    }

    private fun reset() {
        resistor.updateNumberOfBands(resistor.numberOfBands)
        resistanceTextView.text = getString(R.string.default_text)
        resistorImage.clearResistor(this)
        resistor.clear()
        dropDownSetup() // resets dropdown and resistor info
    }
}

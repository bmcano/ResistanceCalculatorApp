package com.brandoncano.resistancecalculator

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.components.ImageTextArrayAdapter
import com.brandoncano.resistancecalculator.components.SpinnerArrays
import com.brandoncano.resistancecalculator.resistor.ResistorImage
import com.brandoncano.resistancecalculator.util.*
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

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val colorDrawable = ColorDrawable(getColor(R.color.orange_primary))
            actionBar.setBackgroundDrawable(colorDrawable)
            actionBar.title = getString(R.string.color_to_value)
            actionBar.elevation = 4F
        }
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
                val intent = Intent(this, ValueToColorActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            R.id.show_resistor_charts -> {
                ShowResistorChart.execute(this, resistor.getNumberOfBands())
            }
            R.id.share_item -> {
                val intent = ShareResistance.execute(resistor, resistanceTextView)
                startActivity(Intent.createChooser(intent, ""))
            }
            R.id.feedback -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = EmailFeedback.execute()
                startActivity(intent)
            }
            R.id.clear_selections -> {
                reset()
            }
            R.id.about_item -> {
                val intent = Intent(this, AboutActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
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

        dropDownBand1.setText(resistor.sigFigBandOne)
        dropDownBand2.setText(resistor.sigFigBandTwo)
        dropDownBand3.setText(resistor.sigFigBandThree)
        dropDownMultiplier.setText(resistor.multiplierBand)
        dropDownTolerance.setText(resistor.toleranceBand)
        dropDownPPM.setText(resistor.ppmBand)

        dropDownBand1.setDropDownDrawable(resistor.sigFigBandOne)
        dropDownBand2.setDropDownDrawable(resistor.sigFigBandTwo)
        dropDownBand3.setDropDownDrawable(resistor.sigFigBandThree)
        dropDownMultiplier.setDropDownDrawable(resistor.multiplierBand)
        dropDownTolerance.setDropDownDrawable(resistor.toleranceBand)
        dropDownPPM.setDropDownDrawable(resistor.ppmBand)

        val numberAdapter = ImageTextArrayAdapter(this, SpinnerArrays.numberArray)
        val multiplierAdapter = ImageTextArrayAdapter(this, SpinnerArrays.multiplierArray)
        val toleranceAdapter = ImageTextArrayAdapter(this, SpinnerArrays.toleranceArray)
        val ppmAdapter = ImageTextArrayAdapter(this, SpinnerArrays.ppmArray)

        dropDownBand1.setAdapter(numberAdapter)
        dropDownBand2.setAdapter(numberAdapter)
        dropDownBand3.setAdapter(numberAdapter)
        dropDownMultiplier.setAdapter(multiplierAdapter)
        dropDownTolerance.setAdapter(toleranceAdapter)
        dropDownPPM.setAdapter(ppmAdapter)

        dropDownBand1.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.sigFigBandOne = dropDownBand1.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownBand1, resistor.sigFigBandOne, resistorImage.band1)
                StateData.SIGFIG_BAND_ONE_CTV.saveData(this, dropDownBand1.text.toString())
            }

        dropDownBand2.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.sigFigBandTwo = dropDownBand2.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownBand2, resistor.sigFigBandTwo, resistorImage.band2)
                StateData.SIGFIG_BAND_TWO_CTV.saveData(this, dropDownBand2.text.toString())
            }

        dropDownBand3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.sigFigBandThree = dropDownBand3.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownBand3, resistor.sigFigBandThree, resistorImage.band3)
                StateData.SIGFIG_BAND_THREE_CTV.saveData(this, dropDownBand3.text.toString())
            }

        dropDownMultiplier.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.multiplierBand = dropDownMultiplier.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownMultiplier, resistor.multiplierBand, resistorImage.band4)
                StateData.MULTIPLIER_BAND_CTV.saveData(this, dropDownMultiplier.text.toString())
            }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.toleranceBand = dropDownTolerance.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownTolerance, resistor.toleranceBand, resistorImage.band5)
                StateData.TOLERANCE_BAND_CTV.saveData(this, dropDownTolerance.text.toString())
            }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.ppmBand = dropDownPPM.adapter.getItem(position).toString()
                updateDropDownSelection(dropDownPPM, resistor.ppmBand, resistorImage.band6)
                StateData.PPM_BAND_CTV.saveData(this, dropDownPPM.text.toString())
            }
    }

    private fun bottomNavigationSetup() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_ctv)
        when (StateData.BUTTON_SELECTION_CTV.loadData(this)) {
            "4" -> {
                bottomNavigationView.selectedItemId = R.id.selected_four_nav
                updateNavigationSelection(4)
            }
            "5" -> {
                bottomNavigationView.selectedItemId = R.id.selected_five_nav
                updateNavigationSelection(5)
            }
            "6" -> {
                bottomNavigationView.selectedItemId = R.id.selected_six_nav
                updateNavigationSelection(6)
            }
        }

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
        resistorImage.setImageColors(this, resistor, numberOfBands)
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
        resistor.setNumberOfBands(numberOfBands)
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

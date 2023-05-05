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
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.brandoncano.resistancecalculator.components.Resistor
import com.brandoncano.resistancecalculator.components.StateData
import com.brandoncano.resistancecalculator.components.ImageTextArrayAdapter
import com.brandoncano.resistancecalculator.components.SpinnerArrays
import com.brandoncano.resistancecalculator.resistor.ResistorImage
import com.brandoncano.resistancecalculator.util.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout

/**
 * Job: Activity for the value to color page.
 */
class ValueToColorActivity : AppCompatActivity() {

    private lateinit var resistanceTextView: TextView
    private lateinit var inputResistance: EditText
    private lateinit var toggleDropDownPPM: TextInputLayout
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var resistorImage: ResistorImage
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
    }

    override fun onResume() {
        super.onResume()
        generalSetup()
        dropDownSetup()
        bottomNavigationSetup()
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
                val intent = Intent(this, ColorToValueActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            R.id.show_resistor_charts -> {
                ShowResistorChart.execute(this, resistor.getNumberOfBands())
            }
            R.id.share_item -> {
                val intent = ShareResistance.execute(resistor, resistanceTextView, isVtC = true)
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
        resistanceTextView = findViewById(R.id.display_resistance)
        toggleDropDownPPM = findViewById(R.id.dropDownSelectorPPM)
        resistorImage = ResistorImage(
            findViewById(R.id.r_p2_band1),
            findViewById(R.id.r_p4_band2),
            findViewById(R.id.r_p6_band3),
            findViewById(R.id.r_p8_band4),
            findViewById(R.id.r_p10_band5),
            findViewById(R.id.r_p12_band6)
        )

        resistanceTextView.text = StateData.RESISTANCE_VTC.loadData(this)
        if (resistanceTextView.text.isEmpty()) {
            resistanceTextView.text = getString(R.string.enter_value)
        }
    }

    private fun dropDownSetup() {
        textInputLayout = findViewById(R.id.edit_text_outline)
        inputResistance = findViewById(R.id.enter_resistance)
        inputResistance.setText(StateData.USER_INPUT_VTC.loadData(this))
        inputResistance.doOnTextChanged { text, _, _, _ ->
            errorFinder(text.toString())
        }

        val dropDownUnits: AutoCompleteTextView = findViewById(R.id.spinnerUnits)
        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinnerTolerance)
        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinnerPPM)

        // load and set saved data
        resistor.units = StateData.UNITS_DROPDOWN_VTC.loadData(this)
        resistor.toleranceValue = StateData.TOLERANCE_DROPDOWN_VTC.loadData(this)
        resistor.ppmValue = StateData.PPM_DROPDOWN_VTC.loadData(this)

        dropDownUnits.setText(resistor.units)
        dropDownTolerance.setText(resistor.toleranceValue)
        dropDownPPM.setText(resistor.ppmValue)

        dropDownTolerance.setDropDownDrawable(resistor.toleranceValue)
        dropDownPPM.setDropDownDrawable(resistor.ppmValue)

        // create and set dropdown adapters
        ArrayAdapter(
            this, R.layout.spinner_units_layout, SpinnerArrays.unitsArray
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_units_layout)
            dropDownUnits.setAdapter(adapter)
        }

        val toleranceAdapter = ImageTextArrayAdapter(this, SpinnerArrays.toleranceTextArray)
        dropDownTolerance.setAdapter(toleranceAdapter)

        val ppmAdapter = ImageTextArrayAdapter(this, SpinnerArrays.ppmTextArray)
        dropDownPPM.setAdapter(ppmAdapter)

        dropDownUnits.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.units = dropDownUnits.adapter.getItem(position).toString()
                StateData.UNITS_DROPDOWN_VTC.saveData(this, dropDownUnits.text.toString())
                errorFinder(inputResistance.text.toString())
                updateResistorAndText()
            }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.toleranceValue = dropDownTolerance.adapter.getItem(position).toString()
                dropDownTolerance.setDropDownDrawable(resistor.toleranceValue)
                StateData.TOLERANCE_DROPDOWN_VTC.saveData(this, dropDownTolerance.text.toString())
                updateResistorAndText()
            }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                resistor.ppmValue = dropDownPPM.adapter.getItem(position).toString()
                dropDownPPM.setDropDownDrawable(resistor.ppmValue)
                StateData.PPM_DROPDOWN_VTC.saveData(this, dropDownPPM.text.toString())
                updateResistorAndText()
            }
    }

    private fun bottomNavigationSetup() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_vtc)
        when (StateData.BUTTON_SELECTION_VTC.loadData(this)) {
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

        resistor.resistance = StateData.USER_INPUT_VTC.loadData(this)
        // crash fix - leaving this activity with invalid input would cause a crash
        if (!(resistor.resistance == "NotValid" || resistor.resistance.isEmpty())) {
            updateResistorAndText()
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
        toggleDropDownPPM.visibility = if (numberOfBands == 6) View.VISIBLE else View.INVISIBLE
        StateData.BUTTON_SELECTION_VTC.saveData(this, "$numberOfBands")
        resistor.setNumberOfBands(numberOfBands)
        errorFinder(inputResistance.text.toString())
        updateResistorAndText()
    }

    private fun calculateButtonSetup() {
        val calculateButton: Button = findViewById(R.id.calculate)
        calculateButton.setOnClickListener {
            updateResistorAndText()
            closeKeyboard()
        }
    }

    private fun errorFinder(text: String) {
        if (text.isEmpty() || text == ".") {
            textInputLayout.error = null
            resistor.resistance = ""
        } else if (!IsValidResistance.execute(resistor, text)) {
            textInputLayout.error = getString(R.string.invalid_input)
            resistor.resistance = "NotValid"
            StateData.USER_INPUT_VTC.saveData(this, "")
            resistorImage.clearResistor(this)
        } else {
            textInputLayout.error = null
            resistor.resistance = text
        }
    }

    private fun updateResistorAndText() {
        resistanceTextView.text = resistor.getResistanceText()
        ResistorFormatter.generateResistor(resistor)
        resistorImage.setImageColors(this, resistor, resistor.getNumberOfBands(), isVtC = true)

        // handle invalid input
        if (resistor.resistance == "NotValid") {
            resistanceTextView.text = getString(R.string.invalid_input)
            resistorImage.clearResistor(this)
            return
        }
        // handle empty input (no input)
        if (resistor.resistance.isEmpty()) {
            resistanceTextView.text = getString(R.string.enter_value)
            resistorImage.clearResistor(this)
            return
        }

        StateData.USER_INPUT_VTC.saveData(this, resistor.resistance)
        StateData.RESISTANCE_VTC.saveData(this, resistanceTextView.text.toString())
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun reset() {
        StateData.RESISTANCE_VTC.clearData(this)
        StateData.BUTTON_SELECTION_VTC.saveData(this, "${resistor.getNumberOfBands()}")
        resistanceTextView.text = getString(R.string.default_text)
        resistorImage.clearResistor(this)
        dropDownSetup() // resets dropdown and resistor info
        calculateButtonSetup()
    }
}

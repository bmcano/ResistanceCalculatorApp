package com.brandoncano.resistancecalculator.ui
//
//import android.content.Context
//import android.os.Bundle
//import android.view.Menu
//import android.view.MenuItem
//import android.view.View
//import android.view.inputmethod.InputMethodManager
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.widget.doOnTextChanged
//import com.brandoncano.resistancecalculator.R
//import com.brandoncano.resistancecalculator.components.ImageTextArrayAdapter
//import com.brandoncano.resistancecalculator.components.SpinnerArrays
//import com.brandoncano.resistancecalculator.components.SpinnerItem
//import com.brandoncano.resistancecalculator.resistor.ResistorVtC
//import com.brandoncano.resistancecalculator.util.EmailFeedback
//import com.brandoncano.resistancecalculator.util.IsValidResistance
//import com.brandoncano.resistancecalculator.util.ResistorFormatter
//import com.brandoncano.resistancecalculator.util.ShareResistance
//import com.brandoncano.resistancecalculator.util.getNavigationValue
//import com.brandoncano.resistancecalculator.util.setDropDownDrawable
//import com.brandoncano.resistancecalculator.util.updateNavigationView
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.android.material.textfield.TextInputLayout
//
///**
// * Job: Activity for the value to color page.
// *
// * Note: Only keeping in right now for reference purposes as I work on the compose version
// */
//class ValueToColorActivity : AppCompatActivity() {
//
//    private lateinit var resistanceTextView: TextView
//    private lateinit var inputResistance: EditText
//    private lateinit var toggleDropDownPPM: TextInputLayout
//    private lateinit var toggleDropDownTolerance: TextInputLayout
//    private lateinit var userInputLayout: TextInputLayout
////    private val resistorImage by lazy {  }
//    private val resistor: ResistorVtC = ResistorVtC(this)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_value_to_color)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        resistanceTextView = findViewById(R.id.display_resistance)
//        toggleDropDownPPM = findViewById(R.id.dropDownSelectorPPM)
//        toggleDropDownTolerance = findViewById(R.id.dropDownSelectorTolerance)
//        userInputLayout = findViewById(R.id.edit_text_outline)
//        resistor.loadData()
//        resistanceTextView.text = resistor.resistance
//
//        val calculateButton: Button = findViewById(R.id.calculate)
//        calculateButton.setOnClickListener {
//            updateResistorAndText()
//            val rootView: View = findViewById(android.R.id.content)
//            closeKeyboard(rootView)
//            userInputLayout.clearFocus()
//        }
//
//        dropDownSetup()
//        bottomNavigationSetup()
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu_dropdown_vtc, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.color_to_value -> {
//                super.finish()
//            }
//            R.id.share_item -> ShareResistance.execute(this, resistor)
//            R.id.feedback -> EmailFeedback.execute(this)
//            R.id.clear_selections -> reset()
//            R.id.about_item -> {}
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun dropDownSetup() {
//        inputResistance = findViewById(R.id.enter_resistance)
//        inputResistance.setText(resistor.userInput)
//        inputResistance.doOnTextChanged { text, _, _, _ ->
//            validateUserInput(text.toString())
//        }
//
//        val dropDownUnits: AutoCompleteTextView = findViewById(R.id.spinnerUnits)
//        val dropDownTolerance: AutoCompleteTextView = findViewById(R.id.spinnerTolerance)
//        val dropDownPPM: AutoCompleteTextView = findViewById(R.id.spinnerPPM)
//        dropDownUnits.setText(resistor.units)
//        dropDownTolerance.setText(resistor.toleranceValue)
//        dropDownPPM.setText(resistor.ppmValue)
//        dropDownTolerance.setDropDownDrawable(resistor.toleranceValue)
//        dropDownPPM.setDropDownDrawable(resistor.ppmValue)
//
//        // create and set dropdown adapters
//        ArrayAdapter(
//            this, R.layout.spinner_units_layout, SpinnerArrays.unitsArray
//        ).also { adapter ->
//            adapter.setDropDownViewResource(R.layout.spinner_units_layout)
//            dropDownUnits.setAdapter(adapter)
//        }
//
//        val toleranceAdapter = ImageTextArrayAdapter(this, SpinnerArrays.getToleranceArray(true))
//        dropDownTolerance.setAdapter(toleranceAdapter)
//
//        val ppmAdapter = ImageTextArrayAdapter(this, SpinnerArrays.getPpmArray(true))
//        dropDownPPM.setAdapter(ppmAdapter)
//
//        dropDownUnits.onItemClickListener =
//            AdapterView.OnItemClickListener { _, _, position, _ ->
//                resistor.units = dropDownUnits.adapter.getItem(position).toString()
//                resistor.saveDropdownSelections()
//                updateResistorAndText()
//                dropDownUnits.clearFocus()
//            }
//
//        dropDownTolerance.onItemClickListener =
//            AdapterView.OnItemClickListener { _, _, position, _ ->
//                val spinnerItem = dropDownTolerance.adapter.getItem(position) as SpinnerItem
//                resistor.toleranceValue = spinnerItem.toString()
//                dropDownTolerance.setDropDownDrawable(resistor.toleranceValue)
//                resistor.saveDropdownSelections()
//                updateResistorAndText()
//                dropDownTolerance.clearFocus()
//            }
//
//        dropDownPPM.onItemClickListener =
//            AdapterView.OnItemClickListener { _, _, position, _ ->
//                val spinnerItem = dropDownPPM.adapter.getItem(position) as SpinnerItem
//                resistor.ppmValue = spinnerItem.toString()
//                dropDownPPM.setDropDownDrawable(resistor.ppmValue)
//                resistor.saveDropdownSelections()
//                updateResistorAndText()
//                dropDownPPM.clearFocus()
//            }
//    }
//
//    private fun bottomNavigationSetup() {
//        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_vtc)
//        val buttonSelection = resistor.loadNumberOfBands()
//        bottomNavigationView.updateNavigationView(buttonSelection)
//        updateNavigationSelection(buttonSelection)
//        bottomNavigationView.setOnItemSelectedListener {
//            updateNavigationSelection(it.getNavigationValue())
//            true
//        }
//    }
//
//    private fun updateNavigationSelection(numberOfBands: Int) {
//        resistor.saveNumberOfBands(numberOfBands)
//        toggleDropDownPPM.visibility = if (resistor.isSixBand()) View.VISIBLE else View.INVISIBLE
//        toggleDropDownTolerance.visibility = if (resistor.isThreeBand()) View.INVISIBLE else View.VISIBLE
//        validateUserInput(inputResistance.text.toString())
//        updateResistorAndText()
//    }
//
//    private fun validateUserInput(text: String) {
////        if (IsValidResistance.execute(resistor, text)) {
////            userInputLayout.error = null
////            resistor.userInput = text
////        } else {
////            userInputLayout.error = getString(R.string.invalid_input)
////            resistor.userInput = ""
////        }
//    }
//
//    private fun updateResistorAndText() {
//        resistanceTextView.text = resistor.getResistanceText()
////        ResistorFormatter.generateResistor(resistor)
////        resistorImage.setImageColors(this, resistor)
//        if (resistor.userInput.isEmpty()) {
//            resistanceTextView.text = getString(R.string.enter_value)
////            resistorImage.clearResistor(this)
//        }
//
//        resistor.saveUserInput(resistor.userInput)
//        resistor.saveResistance(resistanceTextView.text.toString())
//    }
//
//    private fun closeKeyboard(view: View) {
//        val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        input.hideSoftInputFromWindow(view.windowToken, 0)
//    }
//
//    private fun reset() {
//        resistor.saveNumberOfBands(resistor.numberOfBands)
//        resistanceTextView.text = getString(R.string.enter_value)
////        resistorImage.clearResistor(this)
//        resistor.clear()
//        dropDownSetup() // resets dropdown and resistor info
//    }
//}

package com.example.resistancecalculator

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class ValueToColorActivity : AppCompatActivity() {
    private lateinit var fourBandButton: Button
    private lateinit var fiveBandButton: Button
    private lateinit var sixBandButton: Button
    private lateinit var calculateButton: Button

    private lateinit var toggleDropDown: TextInputLayout

    private lateinit var numberBand1: ImageView
    private lateinit var numberBand2: ImageView
    private lateinit var numberBand3: ImageView
    private lateinit var multiplierBand: ImageView
    private lateinit var toleranceColor: ImageView
    private lateinit var ppmColor: ImageView

    private var toleranceBand: String = ""
    private var ppmBand: String = ""
    private var units: String = ""

    private var imageSelection = 4
    private lateinit var chartDialog: Dialog
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var inputResistance: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_to_color)

        // sets up the action bar
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(R.string.value_to_color)

        idSetup()
        buttonSetup()
        dropDownSetup()
    }

    override fun onResume() {
        super.onResume()
        idSetup()
        buttonSetup()
        dropDownSetup()
    }

    // options menu dropdown in top right corner
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown_2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.color_to_value -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.show_resistor_charts -> {
                chartDialog = Dialog(this)
                chartDialog.setContentView(
                    when(imageSelection) {
                        4 -> R.layout.popup_chart_4
                        5 -> R.layout.popup_chart_5
                        6 -> R.layout.popup_chart_6
                        else -> { R.layout.popup_chart_6 }
                    }
                )
                chartDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                chartDialog.show()
                true
            }

            R.id.feedback -> {
                val intent = Intent(Intent.ACTION_VIEW)
                val data: Uri = Uri.parse(
                    "mailto:brandoncano.development@gmail.com?subject="
                            + Uri.encode("[Feedback] - Resistance Calculator")
                        .toString() + "&body=" + Uri.encode("")
                )
                intent.data = data
                startActivity(intent)
                true
            }

            R.id.about_item -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> { super.onOptionsItemSelected(item) }
        }
    }

    private fun idSetup() {
        numberBand1 = findViewById(R.id.r_band_1)
        numberBand2 = findViewById(R.id.r_band_2)
        numberBand3 = findViewById(R.id.r_band_3)
        multiplierBand = findViewById(R.id.r_band_4)
        toleranceColor = findViewById(R.id.r_band_5)
        ppmColor = findViewById(R.id.r_band_6)
        inputResistance = findViewById(R.id.enter_resistance)
        fourBandButton = findViewById(R.id.four_band)
        fiveBandButton = findViewById(R.id.five_band)
        sixBandButton = findViewById(R.id.six_band)
        calculateButton = findViewById(R.id.calculate)
        toggleDropDown = findViewById(R.id.dropDownSelectorPPM)
        textInputLayout = findViewById(R.id.edit_text_outline)
    }

    private fun buttonSetup() {
        // toggle four band resistor
        fourBandButton.setOnClickListener {
            fourBandButton.setBackgroundColor(getColor(R.color.green_700))

            fiveBandButton.setBackgroundColor(getColor(R.color.green_500))
            sixBandButton.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown.visibility = View.INVISIBLE
            imageSelection = 4
            ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor()))
        }

        // toggle five band resistor
        fiveBandButton.setOnClickListener {
            fiveBandButton.setBackgroundColor(getColor(R.color.green_700))

            fourBandButton.setBackgroundColor(getColor(R.color.green_500))
            sixBandButton.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown.visibility = View.INVISIBLE
            imageSelection = 5
            ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor()))
        }

        // toggle six band resistor
        sixBandButton.setOnClickListener {
            sixBandButton.setBackgroundColor(getColor(R.color.green_700))

            fourBandButton.setBackgroundColor(getColor(R.color.green_500))
            fiveBandButton.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown.visibility = View.VISIBLE
            imageSelection = 6
            ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor(ppmBand)))
        }

        // calculate button and related items
        var resistance = "NotValid"
        inputResistance.doOnTextChanged { text, _, _, _ ->
            if (text.toString() == "" || text.toString() == ".") {
                textInputLayout.error = null
                resistance = "NotValid"
            }else if (!ResistorFormatter.isValidInput(imageSelection, text.toString(), units)) {
                textInputLayout.error = "Invalid Input"
                resistance = "NotValid"
            } else {
                textInputLayout.error = null
                resistance = text.toString()
            }
        }

        // make the resistor
        calculateButton.setOnClickListener {
            val colors: Array<Int> = ResistorFormatter.generateResistor(imageSelection, resistance, units)
            if(colors.isNotEmpty()) {
                numberBand1.setColorFilter(ContextCompat.getColor(this, colors[0]))
                numberBand2.setColorFilter(ContextCompat.getColor(this, colors[1]))
                if (colors[2] != -1) numberBand3.setColorFilter(ContextCompat.getColor(this, colors[2]))
                else numberBand3.setColorFilter(ContextCompat.getColor(this, R.color.resistor_blank))
                multiplierBand.setColorFilter(ContextCompat.getColor(this, colors[3]))
            }
        }
    }

    private fun dropDownSetup() {
        // units
        val dropDownUnits : AutoCompleteTextView = findViewById(R.id.spinnerUnits)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.UNITS.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropDownUnits.setAdapter(adapter)
        }

        dropDownUnits.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                units = dropDownUnits.adapter.getItem(position).toString()
            }

        // tolerance
        val dropDownTolerance : AutoCompleteTextView = findViewById(R.id.spinnerTolerance)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.TOLERANCE_TEXT.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropDownTolerance.setAdapter(adapter)
        }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                toleranceBand = dropDownTolerance.adapter.getItem(position).toString()
                dropDownTolerance.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.toleranceImage(toleranceBand),0,0,0)
                toleranceColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.toleranceColor(toleranceBand)))
            }

        // temperature coefficient
        val dropDownPPM : AutoCompleteTextView = findViewById(R.id.spinnerPPM)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.PPM_TEXT.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropDownPPM.setAdapter(adapter)
        }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                ppmBand = dropDownPPM.adapter.getItem(position).toString()
                dropDownPPM.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.ppmImage(ppmBand),0,0,0)
                ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor(ppmBand)))
            }
    }
}

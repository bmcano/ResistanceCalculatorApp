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
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var calcButton: Button

    private lateinit var toggleDropDown: TextInputLayout

    private lateinit var band1: ImageView
    private lateinit var band2: ImageView
    private lateinit var band3: ImageView
    private lateinit var band4: ImageView
    private lateinit var band5: ImageView
    private lateinit var band6: ImageView

    private var toleranceBand: String = ""
    private var ppmBand: String = ""
    private var units: String = ""


    private lateinit var chartDialog: Dialog
    private var imageSelection = 4
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

    // add onResume when needed

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
        band1 = findViewById(R.id.r_band_1)
        band2 = findViewById(R.id.r_band_2)
        band3 = findViewById(R.id.r_band_3)
        band4 = findViewById(R.id.r_band_4)
        band5 = findViewById(R.id.r_band_5)
        band6 = findViewById(R.id.r_band_6)
        inputResistance = findViewById(R.id.enter_resistance)
        button1 = findViewById(R.id.four_band)
        button2 = findViewById(R.id.five_band)
        button3 = findViewById(R.id.six_band)
        calcButton = findViewById(R.id.calculate)
        toggleDropDown = findViewById(R.id.dropDownSelectorPPM)
        textInputLayout = findViewById(R.id.edit_text_outline)
    }

    // band3.setColorFilter(resources.getColor(ColorFinder.bandColor()))
    private fun buttonSetup() {
        // toggle four band resistor
        button1.setOnClickListener {
            button1.setBackgroundColor(getColor(R.color.green_700))

            button2.setBackgroundColor(getColor(R.color.green_500))
            button3.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown.visibility = View.INVISIBLE
            imageSelection = 4
            band6.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor()))
        }

        // toggle five band resistor
        button2.setOnClickListener {
            button2.setBackgroundColor(getColor(R.color.green_700))

            button1.setBackgroundColor(getColor(R.color.green_500))
            button3.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown.visibility = View.INVISIBLE
            imageSelection = 5
            band6.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor()))
        }

        // toggle six band resistor
        button3.setOnClickListener {
            button3.setBackgroundColor(getColor(R.color.green_700))

            button1.setBackgroundColor(getColor(R.color.green_500))
            button2.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown.visibility = View.VISIBLE
            imageSelection = 6
            band6.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor(ppmBand)))
        }

        // calculate button and related items
        var resistance = "NotValid"
        inputResistance.doOnTextChanged { text, _, _, _ ->
            if (text.toString() == "") {
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
        calcButton.setOnClickListener {
            val colors: Array<Int> = ResistorFormatter.generateResistor(resistance, units, imageSelection)
            if(colors.isNotEmpty()) {
                if (colors[0] != -1) { band1.setColorFilter(ContextCompat.getColor(this, colors[0])) }
                if (colors[1] != -1) { band2.setColorFilter(ContextCompat.getColor(this, colors[1])) }
                if (colors[2] != -1) { band3.setColorFilter(ContextCompat.getColor(this, colors[2])) }
                if (colors[3] != -1) { band4.setColorFilter(ContextCompat.getColor(this, colors[3])) }
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
                band5.setColorFilter(ContextCompat.getColor(this, ColorFinder.toleranceColor(toleranceBand)))
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
                band6.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor(ppmBand)))
            }
    }
}

package com.brandoncano.resistancecalculator

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class ValueToColorActivity : AppCompatActivity() {
    private lateinit var screenText: TextView
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
    private var shareColors: Array<Int> = arrayOf()

    private var imageSelection = 4
    private lateinit var chartDialog: Dialog
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var inputResistance: EditText

    private var buttonCheck = ""
    private var resistance = ""

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
        inflater.inflate(R.menu.menu_dropdown_vtc, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.color_to_value -> {
                super.finish()
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

            R.id.share_item -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "plain/text"

                var nb1 = ""; var nb2 = ""; var nb3 = ""; var multi= ""; var tol = ""; var ppm = ""
                if(shareColors.isNotEmpty()) {
                    nb1 = ColorFinder.idToColorText(shareColors[0])
                    nb2 = ColorFinder.idToColorText(shareColors[1])
                    nb3 = ColorFinder.idToColorText(shareColors[2])
                    multi = ColorFinder.idToColorText(shareColors[3])
                    tol = ColorFinder.idToColorText(ColorFinder.toleranceImage(toleranceBand))
                    ppm = ColorFinder.idToColorText(ColorFinder.ppmImage(ppmBand))
                }

                val text = when (imageSelection) {
                    4 -> "${screenText.text}\n[ $nb1, $nb2, $multi, $tol ]"
                    5 -> "${screenText.text}\n[ $nb1, $nb2, $nb3, $multi, $tol ]"
                    6 -> "${screenText.text}\n[ $nb1, $nb2, $nb3, $multi, $tol, $ppm ]"
                    else -> ""
                }

                intent.putExtra(Intent.EXTRA_TEXT, text)
                startActivity(Intent.createChooser(intent, ""))
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
        screenText = findViewById(R.id.display_resistance)
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
            if (resistance != "") resistance = errorFinder(buttonCheck)
        }

        // toggle five band resistor
        fiveBandButton.setOnClickListener {
            fiveBandButton.setBackgroundColor(getColor(R.color.green_700))

            fourBandButton.setBackgroundColor(getColor(R.color.green_500))
            sixBandButton.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown.visibility = View.INVISIBLE
            imageSelection = 5
            if (resistance != "") resistance = errorFinder(buttonCheck)
        }

        // toggle six band resistor
        sixBandButton.setOnClickListener {
            sixBandButton.setBackgroundColor(getColor(R.color.green_700))

            fourBandButton.setBackgroundColor(getColor(R.color.green_500))
            fiveBandButton.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDown.visibility = View.VISIBLE
            imageSelection = 6
            if (resistance != "") resistance = errorFinder(buttonCheck)
        }

        // calculate button and related items

        inputResistance.doOnTextChanged { text, _, _, _ ->
            resistance = errorFinder(text.toString())
        }

        // make the resistor
        calculateButton.setOnClickListener {
            val colors: Array<Int> = ResistorFormatter.generateResistor(imageSelection, resistance, units)
            shareColors = colors
            if(colors.isNotEmpty()) {
                numberBand1.setColorFilter(ContextCompat.getColor(this, colors[0]))
                numberBand2.setColorFilter(ContextCompat.getColor(this, colors[1]))
                numberBand3.setColorFilter(ContextCompat.getColor(this, colors[2]))
                multiplierBand.setColorFilter(ContextCompat.getColor(this, colors[3]))
                toleranceColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.toleranceColor(toleranceBand)))

                var text = ""
                when (imageSelection) {
                    4 -> {
                        numberBand3.setColorFilter(ContextCompat.getColor(this, R.color.resistor_blank))
                        ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor()))
                        text = "$resistance $units $toleranceBand"
                    }
                    5 -> {
                        numberBand3.setColorFilter(ContextCompat.getColor(this, colors[2]))
                        ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor()))
                        text = "$resistance $units $toleranceBand"
                    }
                    6-> {
                        numberBand3.setColorFilter(ContextCompat.getColor(this, colors[2]))
                        ppmColor.setColorFilter(ContextCompat.getColor(this, ColorFinder.ppmColor(ppmBand)))
                        text = if(ppmBand == "") "$resistance $units $toleranceBand" else "$resistance $units $toleranceBand\n$ppmBand"
                    }
                }

                screenText.text = text
            }

            // close keyboard
            val view = this.currentFocus
            if (view != null) {
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    private fun errorFinder(text: String) : String {
        return if (text == "" || text == ".") {
            textInputLayout.error = null
            buttonCheck = ""
            ""
        } else if (!ResistorFormatter.isValidInput(imageSelection, text, units)) {
            textInputLayout.error = "Invalid Input"
            buttonCheck = text
            "NotValid"
        } else {
            textInputLayout.error = null
            buttonCheck = text
            text
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
                if (resistance != "") resistance = errorFinder(buttonCheck)
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
            }
    }
}

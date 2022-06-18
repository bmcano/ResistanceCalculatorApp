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
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var fourBandButton: Button
    private lateinit var fiveBandButton: Button
    private lateinit var sixBandButton: Button

    private lateinit var chartDialog: Dialog
    private var imageSelection = 4

    private var numberBand1: String = ""
    private var numberBand2: String = ""
    private var numberBand3: String = ""
    private var multiplierBand: String = ""
    private var toleranceBand: String = ""
    private var ppmBand: String = ""

    private lateinit var toggleDropDownNumberBand3: TextInputLayout
    private lateinit var toggleDropDownPPM: TextInputLayout

    private lateinit var band1: ImageView
    private lateinit var band2: ImageView
    private lateinit var band3: ImageView
    private lateinit var band4: ImageView
    private lateinit var band5: ImageView
    private lateinit var band6: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // sets up the action bar
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(R.string.color_to_value)

        dropDownSetup()
        idSetup()
        buttonSetup()
    }

    override fun onResume() {
        super.onResume()
        dropDownSetup()
        idSetup()
        buttonSetup()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_dropdown_1, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.value_to_color -> {
                val intent = Intent(this, ValueToColorActivity::class.java)
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
        textView = findViewById(R.id.resistance_display_new)
        band1 = findViewById(R.id.r_band_1)
        band2 = findViewById(R.id.r_band_2)
        band3 = findViewById(R.id.r_band_3)
        band4 = findViewById(R.id.r_band_4)
        band5 = findViewById(R.id.r_band_5)
        band6 = findViewById(R.id.r_band_6)
        fourBandButton = findViewById(R.id.four_band)
        fiveBandButton = findViewById(R.id.five_band)
        sixBandButton = findViewById(R.id.six_band)
        toggleDropDownNumberBand3 = findViewById(R.id.dropDownSelector3)
        toggleDropDownPPM = findViewById(R.id.dropDownSelector6)
    }

    private fun buttonSetup() {
        // toggle four band resistor
        fourBandButton.setOnClickListener {
            fourBandButton.setBackgroundColor(getColor(R.color.green_700))

            fiveBandButton.setBackgroundColor(getColor(R.color.green_500))
            sixBandButton.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDownNumberBand3.visibility = View.GONE
            toggleDropDownPPM.visibility = View.GONE

            calcResistanceHelper()
            imageSelection = 4

            band3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
            band6.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
        }

        // toggle five band resistor
        fiveBandButton.setOnClickListener {
            fiveBandButton.setBackgroundColor(getColor(R.color.green_700))

            fourBandButton.setBackgroundColor(getColor(R.color.green_500))
            sixBandButton.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDownNumberBand3.visibility = View.VISIBLE
            toggleDropDownPPM.visibility = View.GONE

            calcResistanceHelper()
            imageSelection = 5

            band3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand3)))
            band6.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor()))
        }

        // toggle six band resistor
        sixBandButton.setOnClickListener {
            sixBandButton.setBackgroundColor(getColor(R.color.green_700))

            fourBandButton.setBackgroundColor(getColor(R.color.green_500))
            fiveBandButton.setBackgroundColor(getColor(R.color.green_500))

            toggleDropDownNumberBand3.visibility = View.VISIBLE
            toggleDropDownPPM.visibility = View.VISIBLE

            calcResistanceHelper()
            imageSelection = 6

            band3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand3)))
            band6.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(ppmBand)))
        }
    }

    private fun dropDownSetup() {
        // number band 1
        val dropDownBand1 : AutoCompleteTextView = findViewById(R.id.spinner1)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropDownBand1.setAdapter(adapter)
        }

        dropDownBand1.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand1 = dropDownBand1.adapter.getItem(position).toString()
                dropDownBand1.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand1),0,0,0)
                calcResistanceHelper()
                band1.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand1)))
            }

        // number band 2
        val dropDownBand2 : AutoCompleteTextView = findViewById(R.id.spinner2)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownBand2.setAdapter(adapter)
        }

        dropDownBand2.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand2 = dropDownBand2.adapter.getItem(position).toString()
                dropDownBand2.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand2),0,0,0)
                calcResistanceHelper()
                band2.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand2)))
            }

        // number band 3
        val dropDownBand3 : AutoCompleteTextView = findViewById(R.id.spinner3)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.NUMBER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownBand3.setAdapter(adapter)
        }

        dropDownBand3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                numberBand3 = dropDownBand3.adapter.getItem(position).toString()
                dropDownBand3.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(numberBand3),0,0,0)
                calcResistanceHelper()
                band3.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(numberBand3)))
            }

        // multiplier band
        val dropDownMultiplier : AutoCompleteTextView = findViewById(R.id.spinner4)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.MULTIPLIER.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownMultiplier.setAdapter(adapter)
        }

        dropDownMultiplier.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                multiplierBand = dropDownMultiplier.adapter.getItem(position).toString()
                dropDownMultiplier.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(multiplierBand),0,0,0)
                calcResistanceHelper()
                band4.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(multiplierBand)))
            }

        // tolerance band
        val dropDownTolerance : AutoCompleteTextView = findViewById(R.id.spinner5)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.TOLERANCE.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownTolerance.setAdapter(adapter)
        }

        dropDownTolerance.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                toleranceBand = dropDownTolerance.adapter.getItem(position).toString()
                dropDownTolerance.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(toleranceBand),0,0,0)
                calcResistanceHelper()
                band5.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(toleranceBand)))
            }

        // temperature coefficient band
        val dropDownPPM : AutoCompleteTextView = findViewById(R.id.spinner6)
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            SelectionEnums.PPM.array
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // simple_spinner_item
            dropDownPPM.setAdapter(adapter)
        }

        dropDownPPM.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                ppmBand = dropDownPPM.adapter.getItem(position).toString()
                dropDownPPM.setCompoundDrawablesRelativeWithIntrinsicBounds(ColorFinder.imageColor(ppmBand),0,0,0)
                calcResistanceHelper()
                band6.setColorFilter(ContextCompat.getColor(this, ColorFinder.bandColor(ppmBand)))
            }
    }

    // will determine which calculations to do
    private fun calcResistanceHelper() {
        if(toggleDropDownNumberBand3.visibility == View.GONE && toggleDropDownPPM.visibility == View.GONE) {
            textView.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, multiplierBand, toleranceBand)
        } else if(toggleDropDownPPM.visibility == View.GONE){
            textView.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, numberBand3, multiplierBand, toleranceBand)
        } else {
            textView.text = ResistanceFormatter.calcResistance(numberBand1, numberBand2, numberBand3, multiplierBand, toleranceBand, ppmBand)
        }
    }
}

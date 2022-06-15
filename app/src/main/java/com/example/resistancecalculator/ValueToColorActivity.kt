package com.example.resistancecalculator

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar

class ValueToColorActivity : AppCompatActivity() {
    private lateinit var chartDialog: Dialog
    private var imageSelection = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_to_color)

        // sets up the action bar
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#DDA15E"))
        actionBar!!.setBackgroundDrawable(colorDrawable)
        actionBar.title = getString(R.string.value_to_color)
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
}
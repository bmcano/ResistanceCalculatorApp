package com.brandoncano.resistancecalculator.util.external

import android.app.AlertDialog
import android.content.Context

/**
 * Job: Build an alert dialog for whenever an error occurs
 */
object ErrorDialog {

    fun build(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Close", null)
            .show()
    }
}

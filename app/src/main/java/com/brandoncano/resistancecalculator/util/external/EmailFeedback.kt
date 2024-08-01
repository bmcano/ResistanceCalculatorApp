package com.brandoncano.resistancecalculator.util.external

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Job: Takes the user to compose an email with a predefined subject and empty body.
 */
object EmailFeedback {

    fun execute(context: Context) {
        val uri = Uri.parse("mailto:brandoncano.development@gmail.com?subject=[Feedback] - Resistance Calculator")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        try {
            val title = "Send email"
            context.startActivity(Intent.createChooser(intent, title))
        } catch (ex: Exception) {
            ex.printStackTrace()
            ErrorDialog.build(context, "A problem occurred when trying to send an email.")
        }
    }
}

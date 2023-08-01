package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

/**
 * Job: Takes the user to compose an email with a predefined subject and empty body.
 */
object EmailFeedback {

    fun execute(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("mailto:brandoncano.development@gmail.com?subject="
                + Uri.encode("[Feedback] - Resistance Calculator")
                + "&body="
        )
        startActivity(context, intent, null)
    }
}

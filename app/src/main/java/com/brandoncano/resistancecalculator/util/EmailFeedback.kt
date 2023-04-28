package com.brandoncano.resistancecalculator.util

import android.net.Uri

/**
 * Job: Takes the user to compose an email with a predefined subject and empty body.
 */
object EmailFeedback {

    fun execute(): Uri {
        return Uri.parse("mailto:brandoncano.development@gmail.com?subject="
                + Uri.encode("[Feedback] - Resistance Calculator")
                + "&body="
        )
    }
}

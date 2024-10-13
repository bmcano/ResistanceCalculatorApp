package com.brandoncano.resistancecalculator.util

import android.os.Build

object Sdk {

    fun isAtLeastAndroid7(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }
}

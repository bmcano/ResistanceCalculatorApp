package com.brandoncano.resistancecalculator.model.smd

import com.brandoncano.resistancecalculator.components.SmdMode
import com.brandoncano.resistancecalculator.util.formatResistance

data class SmdResistor(
    var code: String = "",
    var units: String = "",
    var navBarSelection: Int = 0,
) {
    fun isEmpty(): Boolean {
        val length = code.length
        return length < 3 || (getSmdMode() is SmdMode.FourDigit && length < 4)
    }

    fun getSmdMode(): SmdMode {
        return when (navBarSelection) {
            0 -> SmdMode.ThreeDigit
            1 -> SmdMode.FourDigit
            2 -> SmdMode.EIA96
            else -> SmdMode.ThreeDigit
        }
    }

    override fun toString(): String {
        val resistance = this.formatResistance()
        return "Code: $code\nResistance: $resistance"
    }
}

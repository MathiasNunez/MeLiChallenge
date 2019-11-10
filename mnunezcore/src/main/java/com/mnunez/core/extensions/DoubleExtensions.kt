package com.mnunez.core.extensions

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat


fun Double.toCurrencyString(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}

fun Double.roundDecimal(): Double? {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(this).toDouble()
}
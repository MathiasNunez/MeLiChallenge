package com.mnunez.core

import com.mnunez.core.extensions.roundDecimal
import com.mnunez.core.extensions.toCurrencyString
import org.junit.Assert
import org.junit.Test

class ExtensionsTests {

    @Test
    fun test_ToCurrency() {
        val roundTo = 10.0
        val toCurrency = roundTo.toCurrencyString()
        Assert.assertEquals(toCurrency, "UYU10.00")
    }

    @Test
    fun test_Round() {
        val roundTo = 10.4453
        val rounded = roundTo.roundDecimal()
        Assert.assertEquals(rounded, 10.45)
    }

}
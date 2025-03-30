package com.dangrover.unitconverter

import org.junit.Test
import org.junit.Assert.*
import com.dangrover.unitconverter.UnitConverter

// Unit tests for the unit converter class
class UnitConverterUnitTest {
    private val unitConverter = UnitConverter()

    // set of tests for several pairs of common conversions
    // feet to miles (everyone knows 1 mile is 5280 feet)
    @Test
    fun feetToMiles_isCorrect() {
        assertEquals(5280.0, unitConverter.convert(1.0, UnitConverter.Unit.MILE, UnitConverter.Unit.FOOT), 0.01)
    }

    // kilometers to miles with 5k (5k = 3.11 mi)
    @Test
    fun kilometersToMiles_isCorrect() {
        assertEquals(3.107, unitConverter.convert(5.0, UnitConverter.Unit.KILOMETER, UnitConverter.Unit.MILE), 0.01)
    }

    // feet to cm
    @Test
    fun feetToCm_isCorrect() {
        assertEquals(30.48, unitConverter.convert(1.0, UnitConverter.Unit.FOOT, UnitConverter.Unit.CENTIMETER), 0.01)
    }
}
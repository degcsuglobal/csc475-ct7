package com.dangrover.unitconverter

//A class that can convert  between units of length
// of various kinds.

class UnitConverter {

    enum class Unit(val toMeters: Double) {
        METER(1.0),
        KILOMETER(1000.0),
        CENTIMETER(0.01),
        MILLIMETER(0.001),
        MILE(1609.344),
        YARD(0.9144),
        FOOT(0.3048),
        INCH(0.0254)
    }


    fun convert(value: Double, fromUnit: Unit, toUnit: Unit): Double {
        val meters = value * fromUnit.toMeters
        return meters / toUnit.toMeters
    }


    fun getUnitAbbreviation(unit: Unit): String {
        return when (unit) {
            Unit.METER -> "m"
            Unit.KILOMETER -> "km"
            Unit.CENTIMETER -> "cm"
            Unit.MILLIMETER -> "mm"
            Unit.MILE -> "mi"
            Unit.YARD -> "yd"
            Unit.FOOT -> "ft"
            Unit.INCH -> "in"
        }
    }




}
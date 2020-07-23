package com.kata.roman.numerals

fun convertToRomanNumeral(decimal: Int): String {
    val startRomanNumeral = findStartRomanNumber(decimal)
    return when {
        startRomanNumeral.decimalEquivalent == decimal -> startRomanNumeral.toString()
        isRomanNumeralWithSubtraction(decimal, startRomanNumeral.next()) -> buildSubtractionNumber(decimal, startRomanNumeral)
        else -> buildRomanNumberWithRepetition(decimal, startRomanNumeral)
    }
}

private fun findStartRomanNumber(decimal: Int) =
    BasicRomanNumeral.values().last { decimal >= it.decimalEquivalent }

private fun isRomanNumeralWithSubtraction(decimal: Int, referenceRomanNumeral: BasicRomanNumeral) =
    BasicRomanNumeral.values()
        .any { it.subtract && referenceRomanNumeral.decimalEquivalent - decimal == it.decimalEquivalent }

private fun buildSubtractionNumber(decimal: Int, referenceRomanNumeral: BasicRomanNumeral): String {
    val nextRomanNumeral = referenceRomanNumeral.next()
    val subtractRomanNumeral = BasicRomanNumeral.values()
        .first { it.subtract && nextRomanNumeral.decimalEquivalent - decimal == it.decimalEquivalent }
    return subtractRomanNumeral.toString() + nextRomanNumeral.toString()
}

private fun buildRomanNumberWithRepetition(decimal: Int, romanRomanNumeral: BasicRomanNumeral): String {
    val timesRepeatingRoman = decimal / romanRomanNumeral.decimalEquivalent
    val decimalRomanNumeralMod = decimal % romanRomanNumeral.decimalEquivalent
    val romanNumeralPart = romanRomanNumeral.toString().repeat(timesRepeatingRoman)
    return if (decimalRomanNumeralMod > 0)
        romanNumeralPart + buildRomanNumberWithRepetition(decimalRomanNumeralMod, romanRomanNumeral.previous())
        else romanNumeralPart
}

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

private fun buildRomanNumberWithRepetition(decimal: Int, basicRomanNumeral: BasicRomanNumeral): String {
    val rationDecimalRomanNumeral = decimal / basicRomanNumeral.decimalEquivalent
    val decimalRomanNumeralMod = decimal % basicRomanNumeral.decimalEquivalent

    val rationDecimal = rationDecimalRomanNumeral * basicRomanNumeral.decimalEquivalent
    val romanNumeralPart = when {
        rationDecimalRomanNumeral == 0 -> ""
        isRomanNumeralWithSubtraction(rationDecimal, basicRomanNumeral.next()) ->
            buildSubtractionNumber(rationDecimal, basicRomanNumeral)
        else -> basicRomanNumeral.toString().repeat(rationDecimalRomanNumeral)
    }

    return if (decimalRomanNumeralMod > 0)
        romanNumeralPart + buildRomanNumberWithRepetition(decimalRomanNumeralMod, basicRomanNumeral.previous())
        else romanNumeralPart
}

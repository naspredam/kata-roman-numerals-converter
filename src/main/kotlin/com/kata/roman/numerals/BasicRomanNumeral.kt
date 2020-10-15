package com.kata.roman.numerals

enum class BasicRomanNumeral(val decimalEquivalent: Int, val subtract: Boolean) {
    I(1, true),
    V(5, false),
    X(10, true),
    L(50, false),
    C(100, true),
    D(500, false),
    M(1000, false);

    fun next(): BasicRomanNumeral {
        return when (val foundIndexOf = values().indexOf(this)) {
            values().size -> this
            else -> values()[foundIndexOf + 1]
        }
    }

    fun previous(): BasicRomanNumeral {
        val indexOf = values().indexOf(this)
        return values()[indexOf - 1]
    }
}
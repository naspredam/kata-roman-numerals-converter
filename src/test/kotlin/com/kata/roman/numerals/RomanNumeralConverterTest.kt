package com.kata.roman.numerals

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

data class DecimalRomanCase (val decimalNumber: Int, val romanNumeral: String)

class RomanNumeralsTestCases {

    companion object {
        @JvmStatic
        fun cases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1, "I"),
                Arguments.of(2, "II"),
                Arguments.of(3, "III"),
                Arguments.of(4, "IV"),
                Arguments.of(5, "V"),
                Arguments.of(6, "VI"),
                Arguments.of(8, "VIII"),
                Arguments.of(9, "IX"),
                Arguments.of(10, "X"),
                Arguments.of(11, "XI"),
                Arguments.of(20, "XX"),
                Arguments.of(21, "XXI"),
                Arguments.of(25, "XXV"),
                Arguments.of(26, "XXVI"),
                Arguments.of(50, "L"),
                Arguments.of(52, "LII"),
                Arguments.of(100, "C"),
                Arguments.of(500, "D"),
                Arguments.of(1000, "M")
            )
        }
    }
}

class RomanNumeralConverterTest {

    @ParameterizedTest
    @MethodSource("com.kata.roman.numerals.RomanNumeralsTestCases#cases")
    fun shouldConvertToNumeral(decimalNumber: Int, romanNumeral: String) {
        assertThat(convertToRomanNumeral(decimalNumber)).isEqualTo(romanNumeral)
    }

}
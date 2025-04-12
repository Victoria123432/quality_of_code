package edu.vikadem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@author admin
@lab_3_4_test
@class Converter
@since 11.04.2025 - 13.15

*/public class Converter {
    public static String convertToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static int convertToArabic(String input) {
        if (input == null) {
            throw new NullPointerException("Input cannot be null");
        }

        input = input.trim().toUpperCase();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        if (!input.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) {
            throw new IllegalArgumentException(input + " is not a valid Roman numeral");
        }

        int result = 0;
        int i = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        while (input.length() > 0 && i < romanNumerals.size()) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (input.startsWith(symbol.name())) {
                result += symbol.getValue();
                input = input.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        return result;
    }

}

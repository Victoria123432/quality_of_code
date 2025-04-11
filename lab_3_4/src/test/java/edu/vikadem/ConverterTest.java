package edu.vikadem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/*
@author admin
@lab_3_4_test
@class ConverterTest
@since 11.04.2025 - 15.18

*/class ConverterTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void whenArabic_3_ThenRoman_III() {
        Assertions.assertEquals("III", Converter.convertToRoman(3));
    }
    @Test
    void whenArabic_5_ThenRoman_V() {
        Assertions.assertEquals("V", Converter.convertToRoman(5));
    }
    @Test
    void whenArabic_4_ThenRoman_IV() {
        Assertions.assertEquals("IV", Converter.convertToRoman(4));
    }
    @Test
    void whenArabic_8_ThenRoman_VIII() {
        Assertions.assertEquals("VIII", Converter.convertToRoman(8));
    }
    @Test
    void whenArabic_10_ThenRoman_X() {
        Assertions.assertEquals("X", Converter.convertToRoman(10));
    }

    @Test
    void whenArabic_15_ThenRoman_XV() {
        Assertions.assertEquals("XV", Converter.convertToRoman(15));
    }

    @Test
    void whenArabic_18_ThenRoman_XVIII() {
        Assertions.assertEquals("XVIII", Converter.convertToRoman(18));
    }

    @Test
    void whenArabic_20_ThenRoman_XX() {
        Assertions.assertEquals("XX", Converter.convertToRoman(20));
    }

    @Test
    void whenArabic_28_ThenRoman_XXVIII() {
        Assertions.assertEquals("XXVIII", Converter.convertToRoman(28));
    }

    @Test
    void whenArabic_30_ThenRoman_XXX() {
        Assertions.assertEquals("XXX", Converter.convertToRoman(30));
    }

    @Test
    void whenArabic_38_ThenRoman_XXXVIII() {
        Assertions.assertEquals("XXXVIII", Converter.convertToRoman(38));
    }

    @Test
    void whenArabic_40_ThenRoman_XL() {
        Assertions.assertEquals("XL", Converter.convertToRoman(40));
    }

    @Test
    void whenArabic_50_ThenRoman_L() {
        Assertions.assertEquals("L", Converter.convertToRoman(50));
    }

    @Test
    void whenArabic_58_ThenRoman_LVIII() {
        Assertions.assertEquals("LVIII", Converter.convertToRoman(58));
    }

    @Test
    void whenArabic_48_ThenRoman_XLVIII() {
        Assertions.assertEquals("XLVIII", Converter.convertToRoman(48));
    }

    @Test
    void whenArabic_88_ThenRoman_LXXXVIII() {
        Assertions.assertEquals("LXXXVIII", Converter.convertToRoman(88));
    }

    @Test
    void whenArabic_90_ThenRoman_XC() {
        Assertions.assertEquals("XC", Converter.convertToRoman(90));
    }

    @Test
    void whenArabic_98_ThenRoman_XCVIII() {
        Assertions.assertEquals("XCVIII", Converter.convertToRoman(98));
    }

    @Test
    void whenArabic_100_ThenRoman_C() {
        Assertions.assertEquals("C", Converter.convertToRoman(100));
    }

    @Test
    void whenArabic_108_ThenRoman_CVIII() {
        Assertions.assertEquals("CVIII", Converter.convertToRoman(108));
    }

    @Test
    void whenArabic_500_ThenRoman_D() {
        Assertions.assertEquals("D", Converter.convertToRoman(500));
    }

    @Test
    void whenArabic_508_ThenRoman_DVIII() {
        Assertions.assertEquals("DVIII", Converter.convertToRoman(508));
    }

    @Test
    void whenArabic_1000_ThenRoman_M() {
        Assertions.assertEquals("M", Converter.convertToRoman(1000));
    }

    @Test
    void whenArabic_1008_ThenRoman_MVIII() {
        Assertions.assertEquals("MVIII", Converter.convertToRoman(1008));
    }

    @Test
    void whenArabic_2000_ThenRoman_MM() {
        Assertions.assertEquals("MM", Converter.convertToRoman(2000));
    }

    @Test
    void whenRoman_I_ThenArabic_1() {
        Assertions.assertEquals(1, Converter.convertToArabic("I"));
    }

    @Test
    void whenRoman_IV_ThenArabic_4() {
        Assertions.assertEquals(4, Converter.convertToArabic("IV"));
    }

    @Test
    void whenRoman_IX_ThenArabic_9() {
        Assertions.assertEquals(9, Converter.convertToArabic("IX"));
    }

    @Test
    void whenRoman_XLII_ThenArabic_42() {
        Assertions.assertEquals(42, Converter.convertToArabic("XLII"));
    }

    @Test
    void whenRoman_LXXX_ThenArabic_80() {
        Assertions.assertEquals(80, Converter.convertToArabic("LXXX"));
    }

    @Test
    void whenRoman_XC_ThenArabic_90() {
        Assertions.assertEquals(90, Converter.convertToArabic("XC"));
    }

    @Test
    void whenRoman_C_ThenArabic_100() {
        Assertions.assertEquals(100, Converter.convertToArabic("C"));
    }

    @Test
    void whenRoman_DCCVII_ThenArabic_707() {
        Assertions.assertEquals(707, Converter.convertToArabic("DCCVII"));
    }

    @Test
    void whenRoman_MCMXC_ThenArabic_1990() {
        Assertions.assertEquals(1990, Converter.convertToArabic("MCMXC"));
    }

    @Test
    void whenRoman_MMMCMXCIX_ThenArabic_3999() {
        Assertions.assertEquals(3999, Converter.convertToArabic("MMMCMXCIX"));
    }




}
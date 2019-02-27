package ru.scratty.translator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class NumberTranslatorTest {

    private static final String THOUSAND = "thousand";
    private static final String HUNDRED = "hundred";

    private static final Map<Integer, String> UNITS = new HashMap<Integer, String>() {{
        put(0, "zero");
        put(1, "one");
        put(2, "two");
        put(3, "three");
        put(4, "four");
        put(5, "five");
        put(6, "six");
        put(7, "seven");
        put(8, "eight");
        put(9, "nine");
    }};

    private static final Map<Integer, String> FROM_10_TO_19 = new HashMap<Integer, String>() {{
        put(10, "ten");
        put(11, "eleven");
        put(12, "twelve");
        put(13, "thirteen");
        put(14, "fourteen");
        put(15, "fifteen");
        put(16, "sixteen");
        put(17, "seventeen");
        put(18, "eighteen");
        put(19, "nineteen");
    }};

    private static final Map<Integer, String> TENS = new HashMap<Integer, String>() {{
        put(20, "twenty");
        put(30, "thirty");
        put(40, "forty");
        put(50, "fifty");
        put(60, "sixty");
        put(70, "seventy");
        put(80, "eighty");
        put(90, "ninety");
    }};

    @Test
    public void translate() {
        NumberTranslator translator = new NumberTranslator();

        for (int i = 0; i < 10000; i++) {
            int translated = translator.translate(generateNumber(i));
            Assert.assertEquals(translated, i);
        }
    }

    private String generateNumber(int number) {
        StringBuilder sb = new StringBuilder();

        if (number >= 10000) {
            throw new RuntimeException("Число должно быть от 0 до 9999");
        }

        if (number > 999) {
            int num = number / 1000;
            sb.append(UNITS.get(num)).append(" ").append(THOUSAND).append(" ");
            number -= num * 1000;
        }

        if (number > 99) {
            int num = number / 100;
            sb.append(UNITS.get(num)).append(" ").append(HUNDRED).append(" ");
            number -= num * 100;
        }

        if (number > 19) {
            int num = (number / 10) * 10;
            sb.append(TENS.get(num)).append(" ");
            number -= num;
        }

        if (number > 9) {
            sb.append(FROM_10_TO_19.get(number));
        } else if (number >= 0){
            sb.append(UNITS.get(number));
        }

        return sb.toString().trim();
    }
}
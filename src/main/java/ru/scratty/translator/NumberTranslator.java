package ru.scratty.translator;

import java.util.HashMap;
import java.util.Map;

public class NumberTranslator {

    private static final String THOUSAND = "thousand";
    private static final String HUNDRED = "hundred";
    private static final String TEN = "ten";

    private static final Map<String, Integer> UNITS = new HashMap<String, Integer>() {{
        put("zero", 0);
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
    }};

    private static final Map<String, Integer> FROM_11_TO_19 = new HashMap<String, Integer>() {{
        put("eleven", 11);
        put("twelve", 12);
        put("thirteen", 13);
        put("fourteen", 14);
        put("fifteen", 15);
        put("sixteen", 16);
        put("seventeen", 17);
        put("eighteen", 18);
        put("nineteen", 19);
    }};

    private static final Map<String, Integer> TENS = new HashMap<String, Integer>() {{
        put("twenty", 20);
        put("thirty", 30);
        put("forty", 40);
        put("fifty", 50);
        put("sixty", 60);
        put("seventy", 70);
        put("eighty", 80);
        put("ninety", 90);
    }};

    public int translate(String text) {
        String[] words = text
                .toLowerCase()
                .trim()
                .split(" ");

        int number = 0;

        for (int i = 0; i < words.length; i++) {
            WordType type = getType(words[i]);

            if (type == WordType.UNKNOWN) {
                System.out.println("Неизвестное число (" + words[i] + ")");
                return -1;
            }

            int num = getNumber(type, words[i]);
            if (i + 1 < words.length) {
                WordType nextType = getType(words[i + 1]);

                if (nextType == WordType.THOUSAND) {
                    num *= 1000;
                } else if (nextType == WordType.HUNDRED) {
                    num *= 100;
                }
            }

            number += num;
        }

        return number;
    }

    private WordType getType(String word) {
        if (THOUSAND.equals(word)) {
            return WordType.THOUSAND;
        }
        if (HUNDRED.equals(word)) {
            return WordType.HUNDRED;
        }
        if (TEN.equals(word)) {
            return WordType.TEN;
        }
        if (UNITS.containsKey(word)) {
            return WordType.UNITS;
        }
        if (FROM_11_TO_19.containsKey(word)) {
            return WordType.FROM_10_TO_19;
        }
        if (TENS.containsKey(word)) {
            return WordType.TENS;
        }

        return WordType.UNKNOWN;
    }

    private int getNumber(WordType type, String word) {
        switch (type) {
            case UNITS:
                return UNITS.get(word);
            case FROM_10_TO_19:
                return FROM_11_TO_19.get(word);
            case TEN:
                return 10;
            case TENS:
                return TENS.get(word);
            default:
                return 0;
        }
    }
}
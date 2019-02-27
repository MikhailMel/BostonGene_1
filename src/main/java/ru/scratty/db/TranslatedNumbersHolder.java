package ru.scratty.db;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TranslatedNumbersHolder {

    private final SortedSet<TranslatedNumber> numbers = new TreeSet<>(Comparator.comparingInt(TranslatedNumber::getNumber));

    public void add(TranslatedNumber translatedNumber) {
        numbers.add(translatedNumber);
    }

    public TranslatedNumber getMinAndDelete() {
        if (numbers.isEmpty()) {
            return null;
        }

        TranslatedNumber num = numbers.first();
        numbers.remove(num);

        return num;
    }

    public int size() {
        return numbers.size();
    }
}

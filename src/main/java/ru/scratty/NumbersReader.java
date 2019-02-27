package ru.scratty;

import ru.scratty.db.TranslatedNumber;
import ru.scratty.db.TranslatedNumbersHolder;
import ru.scratty.translator.NumberTranslator;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class NumbersReader {

    private Semaphore semaphore;

    private TranslatedNumbersHolder holder;

    private Scanner scanner;

    private NumberTranslator translator;

    public NumbersReader(Semaphore semaphore, TranslatedNumbersHolder holder) {
        this.semaphore = semaphore;
        this.holder = holder;

        scanner = new Scanner(System.in);
        translator = new NumberTranslator();
    }

    public void observe() {
        String line = scanner.nextLine();
        while (!line.equals("exit")) {
            if (!line.isEmpty()) {
                handle(line);
            }

            line = scanner.nextLine();
        }
    }

    private void handle(String line) {
        int number = translator.translate(line);

        if (number != -1) {
            TranslatedNumber translatedNumber = new TranslatedNumber(number, line);

            try {
                semaphore.acquire();
                holder.add(translatedNumber);

                System.out.println("Добавлено число " + line + " (" + number + ")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}

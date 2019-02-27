package ru.scratty;

import ru.scratty.db.TranslatedNumbersHolder;

import java.util.Timer;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        TranslatedNumbersHolder holder = new TranslatedNumbersHolder();

        Timer deleteNumbersTimer = new Timer();
        deleteNumbersTimer.schedule(new DeleteNumberTask(semaphore, holder), 0, 5000);

        new NumbersReader(semaphore, holder).observe();
    }
}

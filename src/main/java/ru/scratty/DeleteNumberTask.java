package ru.scratty;

import ru.scratty.db.TranslatedNumber;
import ru.scratty.db.TranslatedNumbersHolder;

import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class DeleteNumberTask extends TimerTask {

    private Semaphore semaphore;

    private TranslatedNumbersHolder holder;

    public DeleteNumberTask(Semaphore semaphore, TranslatedNumbersHolder holder) {
        this.semaphore = semaphore;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            if (holder.size() > 0) {
                TranslatedNumber number = holder.getMinAndDelete();
                System.out.println("Удалено число " + number.getString() + " (" + number.getNumber() + ")");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

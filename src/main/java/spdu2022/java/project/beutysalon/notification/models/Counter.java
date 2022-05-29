package spdu2022.java.project.beutysalon.notification.models;

import java.util.concurrent.atomic.AtomicInteger;

//BEST PRACTICE
public class Counter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public int getCount() {
        return counter.get();
    }

    public void incrementCount() {
        while (true) {
            int existingValue = getCount();
            int newValue = existingValue + 1;
            if (counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
}
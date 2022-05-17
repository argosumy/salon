package spdu2022.java.project.beutysalon.notification.models;

public class Counter {
    private int count;
    private final Object lock = new Object();

    public int getCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
    }

    public void incrementCount() {
        synchronized (lock) {
            count++;
        }
    }
}

package spdu2022.java.project.beutysalon.notification.services;


import spdu2022.java.project.beutysalon.notification.models.Counter;
import spdu2022.java.project.beutysalon.notification.models.Notification;

public class NotificationRunnable implements Runnable {
    private final NotificationService notificationService;
    private final Notification notification;
    private final Counter counter;


    public NotificationRunnable(NotificationService notificationService, Notification notification, Counter counter) {
        this.notificationService = notificationService;
        this.notification = notification;
        this.counter = counter;

    }

    @Override
    public void run() {
        notificationService.notificationSendingToUser(notification);
        counter.incrementCount();
    }
}
package spdu2022.java.project.beutysalon.notification.services;

import spdu2022.java.project.beutysalon.notification.models.Notification;

public class NotificationRunnable implements Runnable {
    private final NotificationServices notificationServices;
    private final Notification notification;

    public NotificationRunnable(NotificationServices notificationServices, Notification notification) {
        this.notificationServices = notificationServices;
        this.notification = notification;
    }

    @Override
    public void run() {
        notificationServices.usersNotification(notification);
    }
}
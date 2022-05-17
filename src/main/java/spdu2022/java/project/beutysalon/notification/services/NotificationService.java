package spdu2022.java.project.beutysalon.notification.services;

import spdu2022.java.project.beutysalon.notification.models.Notification;

public interface NotificationService {
    boolean notificationSendingToUser(Notification notification);
}
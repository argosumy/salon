package spdu2022.java.project.beutysalon.notification.services.creators_notifications;

import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.models.NotificationTypes;

import java.util.List;

public interface CreateNotificationsService {
    List<Notification> createNotifications(UsersNotificationBySalonIdDTO dto);
    NotificationTypes getNotificationsType();
}

package spdu2022.java.project.beutysalon.notification.services.creators_notifications;

import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.models.NotificationType;

import java.util.Set;

public interface CreateNotificationsService {
    Set<Notification> createNotifications(UsersNotificationBySalonIdDTO dto);
    NotificationType getNotificationsType();
}

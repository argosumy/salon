package spdu2022.java.project.beutysalon.notification.services;

import spdu2022.java.project.beutysalon.entities.NotificationTypes;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;

public interface NotificationServices {
    void usersNotification(NotificationTypes types, UsersNotificationBySalonIdDTO dto);
}

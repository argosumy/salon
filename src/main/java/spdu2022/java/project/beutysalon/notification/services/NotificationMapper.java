package spdu2022.java.project.beutysalon.notification.services;

import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;

public class NotificationMapper {

    public static Notification mapBookedService(BookedService bookedService, UsersNotificationBySalonIdDTO dto) {
        Notification notification = new Notification(dto.getTypeNotification());
        notification.setContent(dto.getContentNotification());
        notification.setFrom(bookedService.getSalon());
        notification.setToUser(bookedService.getUser());
        return notification;
    }
}

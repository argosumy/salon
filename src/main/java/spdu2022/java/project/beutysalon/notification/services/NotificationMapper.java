package spdu2022.java.project.beutysalon.notification.services;

import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.models.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    public List<Notification> mapToNotifications(List<BookedService> bookedServices, UsersNotificationBySalonIdDTO dto) {
        return bookedServices.stream().map(x -> mapToNotification(x, dto)).collect(Collectors.toList());
    }

    private Notification mapToNotification(BookedService bookedService, UsersNotificationBySalonIdDTO dto) {
        Notification notification = new Notification(dto.getTypeNotification());
        notification.setContent(dto.getContentNotification());
        notification.setFrom(bookedService.getSalon());
        notification.setToUser(bookedService.getUser());
        return notification;
    }

}
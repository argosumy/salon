package spdu2022.java.project.beutysalon.notification.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.NotificationTypes;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;

import java.util.List;

@Service
public class UsersNotificationService {
    private final List<NotificationServices> resources;

    public UsersNotificationService(List<NotificationServices> resources) {
        this.resources = resources;
    }

    public void notificationUsersBySalonId(NotificationTypes types, UsersNotificationBySalonIdDTO dto) {
        for(NotificationServices services : resources) {
            services.usersNotification(types, dto);
        }
    }




}

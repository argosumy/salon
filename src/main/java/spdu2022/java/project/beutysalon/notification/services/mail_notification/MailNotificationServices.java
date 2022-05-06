package spdu2022.java.project.beutysalon.notification.services.mail_notification;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.NotificationTypes;
import spdu2022.java.project.beutysalon.notification.services.NotificationServices;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;

@Service
public class MailNotificationServices implements NotificationServices {
    @Override
    public void usersNotification(NotificationTypes types, UsersNotificationBySalonIdDTO dto) {
        System.out.println("All users is notification by salon id using email");
        System.out.println("Subject: " + types.name());
    }
}

package spdu2022.java.project.beutysalon.notification.services.mail_notification;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.services.NotificationServices;

@Service
public class MailNotificationServices implements NotificationServices {
    private final MailSenderService emailSender;


    public MailNotificationServices(MailSenderService emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public boolean usersNotification(Notification notification) {
        return emailSender.send(notification);
    }


}

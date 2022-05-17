package spdu2022.java.project.beutysalon.notification.services.mail_notification;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.notification.models.Notification;
import spdu2022.java.project.beutysalon.notification.services.NotificationService;

@Service
public class MailNotificationService implements NotificationService {
    private final MailSenderService emailSender;

    public MailNotificationService(MailSenderService emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public boolean notificationSendingToUser(Notification notification) {
        return emailSender.send(notification);
    }


}

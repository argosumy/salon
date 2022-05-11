package spdu2022.java.project.beutysalon.notification.services.mail_notification;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.notification.models.mail.EMail;
import spdu2022.java.project.beutysalon.notification.models.NotificationTypes;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.entities.User;
import spdu2022.java.project.beutysalon.notification.controllers.dto.UsersNotificationBySalonIdDTO;
import spdu2022.java.project.beutysalon.notification.services.NotificationServices;

@Service
public class MailNotificationServices implements NotificationServices {
    private final MailSenderService emailSender;


    public MailNotificationServices(MailSenderService emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void usersNotification(NotificationTypes types, UsersNotificationBySalonIdDTO dto) {
        Salon salon = new Salon();
        salon.setPhone("+380994869938");
        salon.setSalonName("Ludmila");
        User user = new User();
        user.setLastName("Lozinskiy");
        user.setFirstName("Valeriy");
        user.setEmail("argosumy@gmail.com");
        EMail eMail = new EMail(types);
        eMail.setContent(dto.getContentNotification());
        eMail.setToUser(user);
        eMail.setFrom(salon);

        emailSender.send(eMail);
    }


}

package spdu2022.java.project.beutysalon.notification.services.mail_notification;


import spdu2022.java.project.beutysalon.entities.EMail;
import spdu2022.java.project.beutysalon.entities.User;

public class CreatorEmail {
    private User toUser;
    private String from;
    private String subject;
    private String content;

    public CreatorEmail() {
    }

    public CreatorEmail setToUser(User user){
        this.toUser = user;
        return this;
    }

    public CreatorEmail setFrom(String from) {
        this.from = from;
        return this;
    }

    public CreatorEmail setSubjectNotification(String subject) {
        this.subject = subject;
        return this;
    }

    public CreatorEmail setContentNotification(String content) {
        this.content = content;
        return this;
    }

    public EMail build() {
        EMail eMail = new EMail();
        eMail.setToUser(toUser);
        eMail.setContent(content);
        eMail.setSubject(subject);
        eMail.setFrom(from);
        return eMail;
    }
}

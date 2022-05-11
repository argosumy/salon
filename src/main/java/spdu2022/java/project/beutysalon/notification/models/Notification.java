package spdu2022.java.project.beutysalon.notification.models;

import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.entities.User;

public class Notification {
    private User toUser;
    private Salon fromSalon;
    private final String subject;
    private String content;
    private final NotificationTypes types;


    public Notification(NotificationTypes types) {
        this.subject = types.getSubject();
        this.types = types;
    }

    public Notification(NotificationTypes types, User toUser, Salon fromSalon, String content) {
        this.toUser = toUser;
        this.fromSalon = fromSalon;
        this.subject = types.getSubject();
        this.content = content;
        this.types = types;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Salon getFrom() {
        return fromSalon;
    }

    public void setFrom(Salon fromSalon) {
        this.fromSalon = fromSalon;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotificationTypes getTypes() {
        return types;
    }

    public String getNameNotificationTypes() {
        return this.types.name();
    }
}

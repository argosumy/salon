package spdu2022.java.project.beutysalon.notification.models;

import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.entities.User;

import java.util.Objects;

public class Notification {
    private User toUser;
    private Salon fromSalon;
    private final String subject;
    private String content;
    private final NotificationType types;

    public Notification(NotificationType types) {
        this.subject = types.getSubject();
        this.types = types;
    }

    public Notification(NotificationType types, User toUser, Salon fromSalon, String content) {
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

    public NotificationType getTypes() {
        return types;
    }

    public String getNameNotificationTypes() {
        return this.types.name();
    }

    @Override
    public int hashCode() {
        return Objects.hash(toUser.getEmail(), toUser.getPhone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification notification = (Notification) o;
        return this.getToUser().getEmail().equals(notification.getToUser().getEmail())
                && this.getToUser().getPhone().equals(notification.getToUser().getPhone());
    }
}
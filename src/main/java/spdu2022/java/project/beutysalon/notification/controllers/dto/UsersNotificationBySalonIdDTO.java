package spdu2022.java.project.beutysalon.notification.controllers.dto;

import spdu2022.java.project.beutysalon.notification.models.NotificationTypes;

public class UsersNotificationBySalonIdDTO {
    private long salonId;
    private NotificationTypes typeNotification;
    private String contentNotification;

    public UsersNotificationBySalonIdDTO() {
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public String getContentNotification() {
        return contentNotification;
    }

    public void setContentNotification(String contentNotification) {
        this.contentNotification = contentNotification;
    }

    public NotificationTypes getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(NotificationTypes typeNotification) {
        this.typeNotification = typeNotification;
    }
}

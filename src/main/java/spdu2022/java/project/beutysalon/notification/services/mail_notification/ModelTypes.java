package spdu2022.java.project.beutysalon.notification.services.mail_notification;

import spdu2022.java.project.beutysalon.notification.models.Notification;

import java.util.Map;

public enum ModelTypes {
    CANCELING_BOOKING("cancelling_booking_notification.html") {
        @Override
        public Map<String, Object> getModel(Notification notification) {
            return Map.of("firstName", notification.getToUser().getFirstName(),
                    "lastName", notification.getToUser().getLastName(),
                    "content", notification.getContent(),
                    "salonName", notification.getFrom().getSalonName(),
                    "salonPhone", notification.getFrom().getPhone());
        }
    };

    private final String template;

    ModelTypes(String template) {
        this.template = template;
    }
    public abstract Map<String, Object> getModel(Notification notification);
    public String getTemplate() {
        return this.template;
    }
}

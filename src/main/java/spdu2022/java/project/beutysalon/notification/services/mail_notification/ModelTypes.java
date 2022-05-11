package spdu2022.java.project.beutysalon.notification.services.mail_notification;

import spdu2022.java.project.beutysalon.notification.models.mail.EMail;

import java.util.Map;

public enum ModelTypes {
    CANCELING_BOOKING("cancelling_booking_notification.html") {
        @Override
        public Map<String, Object> getModel(EMail eMail) {
            return Map.of("firstName", eMail.getToUser().getFirstName(),
                    "lastName",eMail.getToUser().getLastName(),
                    "content", eMail.getContent(),
                    "salonName", eMail.getFrom().getSalonName(),
                    "salonPhone", eMail.getFrom().getPhone());
        }
    };
    private final String template;

    ModelTypes(String template) {
        this.template = template;
    }
    public abstract Map<String, Object> getModel(EMail eMail);
    public String getTemplate() {
        return this.template;
    }
}

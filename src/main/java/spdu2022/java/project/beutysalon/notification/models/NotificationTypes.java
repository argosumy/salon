package spdu2022.java.project.beutysalon.notification.models;

import org.springframework.stereotype.Component;
@Component
public enum NotificationTypes {
    CANCELING_BOOKING {
        @Override
        public String getSubject() {
            return "CANCELING OF BOOKING";
        }
    };

    public abstract String getSubject();

}

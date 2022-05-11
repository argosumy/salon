package spdu2022.java.project.beutysalon.notification.models;

public enum NotificationTypes {
    CANCELING_BOOKING {
        @Override
        public String getSubject() {
            return "CANCELING OF BOOKING";
        }
    };

    public abstract String getSubject();

}

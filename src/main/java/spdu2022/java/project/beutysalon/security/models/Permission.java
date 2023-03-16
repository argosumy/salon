package spdu2022.java.project.beutysalon.security.models;

public enum Permission {
    READ("read"),
    UPDATE("update"),
    ADMIN_UPDATE("admin_update");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

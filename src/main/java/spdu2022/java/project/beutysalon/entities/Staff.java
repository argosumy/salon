package spdu2022.java.project.beutysalon.entities;

import java.util.Objects;

public class Staff {
    private long id;
    private long userId;
    private long salonId;
    private String linkPhoto;

    public Staff() {
    }

    public Staff(long userId, long salonId, String linkPhoto) {
        this.userId = userId;
        this.salonId = salonId;
        this.linkPhoto = linkPhoto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSalonId() {
        return salonId;
    }

    public void setSalonId(long salonId) {
        this.salonId = salonId;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id &&
                userId == staff.userId &&
                salonId == staff.salonId &&
                Objects.equals(linkPhoto, staff.linkPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, salonId);
    }
}

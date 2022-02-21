package spdu2022.java.project.beutysalon.entities;

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
}

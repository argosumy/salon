package spdu2022.java.project.beutysalon.staff_registration.controllers.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StaffDTOLight {
    private long id;
    @Min(1)
    private long userId;
    @Min(1)
    private long salonId;
    @NotNull
    private String linkPhoto;

    public StaffDTOLight() {
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

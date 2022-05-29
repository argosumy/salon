package spdu2022.java.project.beutysalon.staffregistration.controllers.dto;

import javax.validation.constraints.Min;

public class StaffModificationDTO {
    private long id;
    @Min(1)
    private long userId;
    @Min(1)
    private long salonId;

    public StaffModificationDTO() {
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
}
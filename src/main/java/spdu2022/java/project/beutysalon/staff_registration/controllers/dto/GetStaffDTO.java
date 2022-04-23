package spdu2022.java.project.beutysalon.staff_registration.controllers.dto;

import spdu2022.java.project.beutysalon.entities.Staff;

public class GetStaffDTO {
    private Staff staff;

    public GetStaffDTO() {
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}

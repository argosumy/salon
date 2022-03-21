package spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto;

import java.util.List;

public class StaffWorkingModeDto {
    private long staffId;

    private List<String> dates;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}

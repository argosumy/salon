package spdu2022.java.project.beutysalon.entities;

import java.time.LocalDate;
import java.util.List;

public class StaffWorkingMode {
    private long staffId;
    private List<LocalDate> dates;

    public StaffWorkingMode() {
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public void setDates(List<LocalDate> dates) {
        this.dates = dates;
    }
}

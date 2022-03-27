package spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto;

import org.springframework.format.annotation.DateTimeFormat;
import spdu2022.java.project.beutysalon.constraints_validaion.annotations.DateValid;

import java.time.LocalDate;
import java.util.List;

public class StaffWorkingModeDto {
    private long staffId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<@DateValid LocalDate> dates;

    public StaffWorkingModeDto() {
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

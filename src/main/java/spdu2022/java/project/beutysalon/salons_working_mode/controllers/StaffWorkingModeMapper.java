package spdu2022.java.project.beutysalon.salons_working_mode.controllers;

import spdu2022.java.project.beutysalon.entities.StaffWorkingMode;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.StaffWorkingModeDto;

public class StaffWorkingModeMapper {
    public StaffWorkingMode convertStaffWorkingModeDtoToStaffWorkingMode(StaffWorkingModeDto dto) {
        StaffWorkingMode staffWorkingMode = new StaffWorkingMode();
        staffWorkingMode.setStaffId(dto.getStaffId());
        staffWorkingMode.setDates(dto.getDates());
        return staffWorkingMode;
    }
}

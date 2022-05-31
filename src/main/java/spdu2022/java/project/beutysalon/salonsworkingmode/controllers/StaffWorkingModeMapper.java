package spdu2022.java.project.beutysalon.salonsworkingmode.controllers;

import spdu2022.java.project.beutysalon.entities.StaffWorkingMode;
import spdu2022.java.project.beutysalon.salonsworkingmode.controllers.dto.StaffWorkingModeDto;

public class StaffWorkingModeMapper {
    public StaffWorkingMode convertStaffWorkingModeDtoToStaffWorkingMode(StaffWorkingModeDto dto) {
        StaffWorkingMode staffWorkingMode = new StaffWorkingMode();
        staffWorkingMode.setStaffId(dto.getStaffId());
        staffWorkingMode.setDates(dto.getDates());
        return staffWorkingMode;
    }
}

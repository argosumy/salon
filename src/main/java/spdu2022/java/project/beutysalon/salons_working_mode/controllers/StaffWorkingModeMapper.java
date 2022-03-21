package spdu2022.java.project.beutysalon.salons_working_mode.controllers;

import spdu2022.java.project.beutysalon.entities.StaffWorkingMode;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.StaffWorkingModeDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class StaffWorkingModeMapper {
    public StaffWorkingMode convertStaffWorkingModeDtoToStaffWorkingMode(StaffWorkingModeDto dto) {
        StaffWorkingMode staffWorkingMode = new StaffWorkingMode();
        staffWorkingMode.setStaffId(dto.getStaffId());
        staffWorkingMode.setDates(dto.getDates()
                .stream()
                .map(x -> LocalDate.parse(x, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .collect(Collectors.toList()));
        return staffWorkingMode;
    }
}

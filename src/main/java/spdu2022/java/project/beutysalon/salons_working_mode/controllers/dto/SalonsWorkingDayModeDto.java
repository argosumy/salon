package spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto;

import java.util.List;

public class SalonsWorkingDayModeDto {
    private List<SalonWorkingDayModeDto> salonsWorkingMode;

    public List<SalonWorkingDayModeDto> getSalonsWorkingMode() {
        return salonsWorkingMode;
    }

    public void setSalonsWorkingMode(List<SalonWorkingDayModeDto> salonsWorkingMode) {
        this.salonsWorkingMode = salonsWorkingMode;
    }
}

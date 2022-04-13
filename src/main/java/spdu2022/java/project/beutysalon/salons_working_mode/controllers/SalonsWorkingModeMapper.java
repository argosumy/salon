package spdu2022.java.project.beutysalon.salons_working_mode.controllers;

import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDay;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.SalonWorkingDayModeDto;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.SalonWorkingModeForWeekDto;

public class SalonsWorkingModeMapper {

    protected SalonWorkingMode convertDtoWorkingDayToSalonWorkingMode(SalonWorkingDayModeDto dto) {
        SalonWorkingMode result = new SalonWorkingMode();
        result.setSalonId(dto.getSalonId());
        for(WorkingDay workingPeriodDto : dto.getSalonWorkingPeriodList()) {
           result.addWorkingDay(workingPeriodDto);
        }
        return result;
    }

    protected SalonWorkingDayModeDto convertSalonWorkingModeToDto(SalonWorkingMode entity) {
        SalonWorkingDayModeDto result = new SalonWorkingDayModeDto();
        result.setSalonId(entity.getSalonId());
        result.setSalonWorkingPeriodList(entity.getSalonWorkingModeUniq());
        return result;
    }

    protected SalonWorkingMode convertDtoWorkingDayOfWeekToSalonWorkingMode(SalonWorkingModeForWeekDto dto) {
        SalonWorkingMode result = new SalonWorkingMode();
        for(WorkingDayOfWeek dayOfWeek : dto.getWeekDtoList()) {
            result.addWorkingDayOfWeek(dayOfWeek);
        }
        return result;
    }

    protected SalonWorkingModeForWeekDto convertSalonWorkingModeToDtoWorkingDayOfWeek(SalonWorkingMode salonWorkingMode) {
        SalonWorkingModeForWeekDto result = new SalonWorkingModeForWeekDto();
        result.setSalonId(salonWorkingMode.getSalonId());
        result.setWeekDtoList(salonWorkingMode.getSalonsWorkingDaysOfWeek());
        return result;
    }
}

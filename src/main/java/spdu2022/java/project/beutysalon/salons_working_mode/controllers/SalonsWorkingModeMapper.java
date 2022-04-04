package spdu2022.java.project.beutysalon.salons_working_mode.controllers;

import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDay;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.entities.WorkingMode;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.SalonWorkingDayModeDto;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.SalonWorkingModeForWeekDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SalonsWorkingModeMapper {

    protected SalonWorkingMode convertDtoWorkingDayToSalonWorkingMode(SalonWorkingDayModeDto dto) {
        SalonWorkingMode result = new SalonWorkingMode();
        result.setSalonId(dto.getSalonId());

        for(WorkingDay workingPeriodDto : dto.getSalonWorkingPeriodList()) {
            WorkingDay workingPeriod = new WorkingDay(workingPeriodDto.getWorkingDay());
            workingPeriod.getWorkingTimePeriod().setStartWorking(workingPeriodDto.getWorkingTimePeriod().getStartWorking());
            workingPeriod.getWorkingTimePeriod().setEndWorking(workingPeriodDto.getWorkingTimePeriod().getEndWorking());
            result.getSalonWorkingMode().add(workingPeriod);
        }
        return result;
    }

    protected SalonWorkingDayModeDto convertSalonWorkingModeToDto(SalonWorkingMode entity) {
        List<WorkingMode> dayPeriodList = entity.getSalonWorkingMode();
        SalonWorkingDayModeDto result = new SalonWorkingDayModeDto();
        result.setSalonId(entity.getSalonId());
        List<WorkingDay> workingPeriodDtoList = result.getSalonWorkingPeriodList();
        for(WorkingMode workingPeriodEntity : dayPeriodList) {
            WorkingDay period = (WorkingDay) workingPeriodEntity;
            WorkingDay workingPeriodDto = new WorkingDay(period.getWorkingDay());
            workingPeriodDto.getWorkingTimePeriod().setStartWorking(period.getWorkingTimePeriod().getStartWorking());
            workingPeriodDto.getWorkingTimePeriod().setEndWorking(period.getWorkingTimePeriod().getEndWorking());
            workingPeriodDtoList.add(workingPeriodDto);
        }
        return result;
    }

    protected SalonWorkingMode convertDtoWorkingDayOfWeekToSalonWorkingMode(SalonWorkingModeForWeekDto dto) {
        SalonWorkingMode result = new SalonWorkingMode();
        Set<WorkingDayOfWeek> workingPeriods = dto.getWeekDtoList();
        for(WorkingDayOfWeek period : workingPeriods) {
            result.getSalonWorkingMode().add(period);
        }
        return result;
    }

    protected SalonWorkingModeForWeekDto convertSalonWorkingModeToDtoWorkingDayOfWeek(SalonWorkingMode salonWorkingMode) {
        SalonWorkingModeForWeekDto result = new SalonWorkingModeForWeekDto();
        result.setSalonId(salonWorkingMode.getSalonId());
        Set<WorkingDayOfWeek> workingDayOfWeekList = new HashSet<>();
        for(WorkingMode workingMode : salonWorkingMode.getSalonWorkingMode()) {
            WorkingDayOfWeek workingDayOfWeek = (WorkingDayOfWeek)workingMode;
            workingDayOfWeekList.add(workingDayOfWeek);
        }
        result.setWeekDtoList(workingDayOfWeekList);
        return result;
    }


}

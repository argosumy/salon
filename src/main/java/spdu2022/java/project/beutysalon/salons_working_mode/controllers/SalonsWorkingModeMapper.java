package spdu2022.java.project.beutysalon.salons_working_mode.controllers;

import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.entities.WorkingDayPeriod;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.SalonWorkingDayModeDto;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.SalonWorkingModeForWeekDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SalonsWorkingModeMapper {

    protected SalonWorkingMode convertDtoWorkingDayToSalonWorkingMode(SalonWorkingDayModeDto dto) {
        SalonWorkingMode result = new SalonWorkingMode();
        result.setSalonId(dto.getSalonId());

        for(WorkingDayPeriod workingPeriodDto : dto.getSalonWorkingPeriodList()) {
            WorkingDayPeriod workingPeriod = new WorkingDayPeriod();
            workingPeriod.setWorkingDay(workingPeriodDto.getWorkingDay());
            workingPeriod.setStartWorking(workingPeriodDto.getStartWorking());
            workingPeriod.setEndWorking(workingPeriodDto.getEndWorking());
            result.getSalonWorkingPeriods().add(workingPeriod);
        }
        return result;
    }

    protected SalonWorkingDayModeDto convertSalonWorkingModeToDto(SalonWorkingMode entity) {
        List<WorkingPeriod> dayPeriodList = entity.getSalonWorkingPeriods();
        SalonWorkingDayModeDto result = new SalonWorkingDayModeDto();
        result.setSalonId(entity.getSalonId());
        List<WorkingDayPeriod> workingPeriodDtoList = result.getSalonWorkingPeriodList();
        for(WorkingPeriod workingPeriodEntity : dayPeriodList) {
            WorkingDayPeriod period = (WorkingDayPeriod) workingPeriodEntity;
            WorkingDayPeriod workingPeriodDto = new WorkingDayPeriod();
            workingPeriodDto.setWorkingDay(period.getWorkingDay());
            workingPeriodDto.setStartWorking(period.getStartWorking());
            workingPeriodDto.setEndWorking(period.getEndWorking());
            workingPeriodDtoList.add(workingPeriodDto);
        }
        return result;
    }

    protected SalonWorkingMode convertDtoWorkingDayOfWeekToSalonWorkingMode(SalonWorkingModeForWeekDto dto) {
        SalonWorkingMode result = new SalonWorkingMode();
        Set<WorkingDayOfWeek> workingPeriods = dto.getWeekDtoList();
        for(WorkingDayOfWeek period : workingPeriods) {
            result.getSalonWorkingPeriods().add(period);
        }
        return result;
    }

    protected SalonWorkingModeForWeekDto convertSalonWorkingModeToDtoWorkingDayOfWeek(SalonWorkingMode workingMode) {
        SalonWorkingModeForWeekDto result = new SalonWorkingModeForWeekDto();
        result.setSalonId(workingMode.getSalonId());
        Set<WorkingDayOfWeek> workingDayOfWeekList = new HashSet<>();
        for(WorkingPeriod workingPeriod : workingMode.getSalonWorkingPeriods()) {
            WorkingDayOfWeek workingDayOfWeek = (WorkingDayOfWeek) workingPeriod;
            workingDayOfWeekList.add(workingDayOfWeek);
        }
        result.setWeekDtoList(workingDayOfWeekList);
        return result;
    }


}

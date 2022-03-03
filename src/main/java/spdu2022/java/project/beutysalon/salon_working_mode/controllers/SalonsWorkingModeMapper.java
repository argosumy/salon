package spdu2022.java.project.beutysalon.salon_working_mode.controllers;

import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.salon_working_mode.controllers.dto.SalonWorkingModeDto;

import java.util.List;

public class SalonsWorkingModeMapper {

    protected SalonWorkingMode convertDtoToSalonWorkingMode(SalonWorkingModeDto dto) {
        SalonWorkingMode entity = new SalonWorkingMode();
        entity.setSalonId(dto.getSalonId());

        List<SalonWorkingMode.WorkingPeriod> workingPeriodList = entity.getSalonWorkingPeriods();
        for(SalonWorkingModeDto.WorkingPeriod workingPeriodDto : dto.getSalonWorkingPeriodList()) {
            SalonWorkingMode.WorkingPeriod workingPeriod = new SalonWorkingMode.WorkingPeriod();
            workingPeriod.setDate(workingPeriodDto.getDate());
            workingPeriod.setStartWorking(workingPeriodDto.getStartWorking());
            workingPeriod.setEndWorking(workingPeriodDto.getEndWorking());
            workingPeriodList.add(workingPeriod);
        }

        return entity;
    }

    protected SalonWorkingModeDto convertSalonWorkingModeToDto(SalonWorkingMode entity) {
        SalonWorkingModeDto dto = new SalonWorkingModeDto();
        dto.setSalonId(entity.getSalonId());

        List<SalonWorkingModeDto.WorkingPeriod> workingPeriodDtoList = dto.getSalonWorkingPeriodList();
        for(SalonWorkingMode.WorkingPeriod workingPeriodEntity : entity.getSalonWorkingPeriods()) {
            SalonWorkingModeDto.WorkingPeriod workingPeriodDto = new SalonWorkingModeDto.WorkingPeriod();
            workingPeriodDto.setDate(workingPeriodEntity.getDate());
            workingPeriodDto.setStartWorking(workingPeriodEntity.getStartWorking());
            workingPeriodDto.setEndWorking(workingPeriodEntity.getEndWorking());
            workingPeriodDtoList.add(workingPeriodDto);
        }

        return dto;
    }
}

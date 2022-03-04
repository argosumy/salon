package spdu2022.java.project.beutysalon.salons_working_mode.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.SalonWorkingDayModeDto;
import spdu2022.java.project.beutysalon.salons_working_mode.controllers.dto.SalonWorkingModeForWeekDto;
import spdu2022.java.project.beutysalon.salons_working_mode.services.SalonsWorkingModeModificationService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/working-mode/salons/{salonId}")
@Validated
public class SalonsWorkingModeController {
    private final SalonsWorkingModeModificationService salonsWorkingModificationService;

    public SalonsWorkingModeController(SalonsWorkingModeModificationService salonsWorkingModificationService) {
        this.salonsWorkingModificationService = salonsWorkingModificationService;
    }

    @PostMapping("/days")
    @ResponseStatus(HttpStatus.CREATED)
    public SalonWorkingDayModeDto createWorkingMode(@Valid @RequestBody SalonWorkingDayModeDto workingModeDto, @PathVariable("salonId") long salonId) {
        SalonsWorkingModeMapper modeMapper = new SalonsWorkingModeMapper();
        SalonWorkingMode salonWorkingMode = modeMapper.convertDtoWorkingDayToSalonWorkingMode(workingModeDto);
        salonWorkingMode.setSalonId(salonId);
        salonsWorkingModificationService.addNewWorkingPeriod(salonWorkingMode);
        return modeMapper.convertSalonWorkingModeToDto(salonWorkingMode);
    }

    @PostMapping("/days-week")
    @ResponseStatus(HttpStatus.CREATED)
    public SalonWorkingModeForWeekDto createWorkingModeForWeek(@Valid @RequestBody SalonWorkingModeForWeekDto workingModeDto, @PathVariable("salonId") long salonId) {
        SalonsWorkingModeMapper modeMapper = new SalonsWorkingModeMapper();
        SalonWorkingMode workingMode = modeMapper.convertDtoWorkingDayOfWeekToSalonWorkingMode(workingModeDto);
        workingMode.setSalonId(salonId);
        salonsWorkingModificationService.addNewWorkingPeriod(workingMode);
        return modeMapper.convertSalonWorkingModeToDtoWorkingDayOfWeek(workingMode);
    }
}

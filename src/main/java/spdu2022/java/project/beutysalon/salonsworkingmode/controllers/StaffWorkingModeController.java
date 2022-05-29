package spdu2022.java.project.beutysalon.salonsworkingmode.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.StaffWorkingMode;
import spdu2022.java.project.beutysalon.salonsworkingmode.controllers.dto.StaffWorkingModeDto;
import spdu2022.java.project.beutysalon.salonsworkingmode.services.StaffWorkingModeModificationService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/working-mode/staff")
@Validated
public class StaffWorkingModeController {
    private final StaffWorkingModeModificationService staffWorkingModeModificationService;

    public StaffWorkingModeController(StaffWorkingModeModificationService staffWorkingModeModificationService) {
        this.staffWorkingModeModificationService = staffWorkingModeModificationService;
    }

    @PostMapping("/{staffId}")
    @ResponseStatus(HttpStatus.CREATED)
    public long createStaffWorkingModeByStaffId(@Valid @RequestBody StaffWorkingModeDto staffWorkingModeDto, @PathVariable("staffId") long staffId) {
        StaffWorkingModeMapper mapper = new StaffWorkingModeMapper();
        StaffWorkingMode staffWorkingMode = mapper.convertStaffWorkingModeDtoToStaffWorkingMode(staffWorkingModeDto);
        staffWorkingMode.setStaffId(staffId);
        return staffWorkingModeModificationService.addNewWorkingMode(staffWorkingMode);
    }
}

package spdu2022.java.project.beutysalon.staffregistration.controllers;

import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staffregistration.controllers.dto.StaffModificationDTO;
import spdu2022.java.project.beutysalon.staffregistration.services.StaffModificationService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    private final StaffModificationService staffModificationService;
    private final StaffModificationMapper staffModificationMapper;

    public StaffController(StaffModificationService staffModificationService, StaffModificationMapper staffModificationMapper) {
        this.staffModificationService = staffModificationService;
        this.staffModificationMapper = staffModificationMapper;
    }

    @PostMapping
    public StaffModificationDTO addNewStaff(@Valid @RequestBody StaffModificationDTO staffModificationDTO) {
        Staff staff = staffModificationService.insertNewStaff(staffModificationMapper.convertStaffDTOLightToStaff(staffModificationDTO));
        return staffModificationMapper.convertStaffToStaffDtoLight(staff);
    }
}
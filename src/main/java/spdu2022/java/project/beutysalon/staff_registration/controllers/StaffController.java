package spdu2022.java.project.beutysalon.staff_registration.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staff_registration.controllers.dto.StaffDTOLight;
import spdu2022.java.project.beutysalon.staff_registration.services.StaffModificationService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    private final StaffModificationService staffModificationService;
    private final StaffMapper staffMapper;

    public StaffController(StaffModificationService staffModificationService, StaffMapper staffMapper) {
        this.staffModificationService = staffModificationService;
        this.staffMapper = staffMapper;
    }

    @PostMapping
    public StaffDTOLight addNewStaff(@Valid @RequestBody StaffDTOLight staffDTOLight) {
        Staff staff = staffModificationService.insertNewStaff(staffMapper.convertStaffDTOLightToStaff(staffDTOLight));
        return staffMapper.convertStaffToStaffDtoLight(staff);
    }

}

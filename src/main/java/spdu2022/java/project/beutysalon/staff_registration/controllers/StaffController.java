package spdu2022.java.project.beutysalon.staff_registration.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staff_registration.controllers.dto.StaffDTO;
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
    public StaffDTO addNewStaff(@Valid @RequestBody StaffDTO staffDTO) {
        Staff staff = staffModificationService.insertNewStaff(staffMapper.convertStaffDTOLightToStaff(staffDTO));
        return staffMapper.convertStaffToStaffDtoLight(staff);
    }

}

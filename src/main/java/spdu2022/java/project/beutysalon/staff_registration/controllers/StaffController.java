package spdu2022.java.project.beutysalon.staff_registration.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.exeptions.Error;
import spdu2022.java.project.beutysalon.staff_registration.controllers.dto.StaffDTOLight;
import spdu2022.java.project.beutysalon.staff_registration.services.StaffModificationService;

import javax.validation.Valid;
import java.sql.SQLException;

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
    public StaffDTOLight addNewStaff(@Valid @RequestBody StaffDTOLight staffDTOLight) throws SQLException {
        Staff staff = staffModificationService.insertNewStaff(staffMapper.convertStaffDTOLightToStaff(staffDTOLight));
        return staffMapper.convertStaffToStaffDtoLight(staff);
    }

    @ExceptionHandler(EntityNotUniqException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Error> notUniqStaff(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
    }

}

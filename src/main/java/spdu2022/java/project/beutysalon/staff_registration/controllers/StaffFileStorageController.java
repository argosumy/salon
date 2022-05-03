package spdu2022.java.project.beutysalon.staff_registration.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.staff_registration.services.StaffFileStorage;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("api/v1/staff/{staffId}/file")
@Validated
public class StaffFileStorageController {
    private final StaffFileStorage staffFileStorage;

    public StaffFileStorageController(StaffFileStorage staffFileStorage) {
        this.staffFileStorage = staffFileStorage;
    }

    @PostMapping
    public ResponseEntity<String> saveAvatar(@RequestParam("file") MultipartFile file, @Min(value = 1, message = "id must be  > 0") @PathVariable("staffId") long staffId) {
        String link = staffFileStorage.saveAvatarOfStaff(staffId, file);
        return new ResponseEntity<>(link, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAvatar(@PathVariable("staffId") @Min(value = 1, message = "id must be  > 0")long staffId) {
        staffFileStorage.deleteAvatarOfStaff(staffId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package spdu2022.java.project.beutysalon.staff_registration.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.staff_registration.services.StaffFileStorage;

@RestController
@RequestMapping("api/v1/staff/{staffId}/file")
public class StaffFileStorageController {
    private final StaffFileStorage staffFileStorage;

    public StaffFileStorageController(StaffFileStorage staffFileStorage) {
        this.staffFileStorage = staffFileStorage;
    }

    @PostMapping
    public ResponseEntity<String> saveAvatar(@RequestParam("file") MultipartFile file, @PathVariable("staffId") long staffId) {
        return new ResponseEntity<>(staffFileStorage.saveAvatarOfStaff(staffId, file), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAvatar(@PathVariable("staffId") long staffId) {
        staffFileStorage.deleteAvatarOfStaff(staffId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

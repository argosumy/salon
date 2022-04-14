package spdu2022.java.project.beutysalon.aws.s3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.aws.s3.services.S3Service;

@RestController
@RequestMapping("api/v1/staff")
public class S3Controller {
    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/{staffId}/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("staffId") long staffId) {
        return new ResponseEntity<>(s3Service.uploadFile(staffId, file), HttpStatus.OK);
    }
}

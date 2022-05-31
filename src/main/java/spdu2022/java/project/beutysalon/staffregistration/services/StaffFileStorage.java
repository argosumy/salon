package spdu2022.java.project.beutysalon.staffregistration.services;

import org.springframework.web.multipart.MultipartFile;

public interface StaffFileStorage {
    String saveAvatarOfStaff(long staffId, MultipartFile multipartFile);

    void deleteAvatarOfStaff(long staffId);
}

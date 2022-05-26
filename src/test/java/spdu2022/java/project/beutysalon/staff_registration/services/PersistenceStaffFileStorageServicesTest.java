package spdu2022.java.project.beutysalon.staff_registration.services;

import com.amazonaws.util.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.FileStorageException;
import spdu2022.java.project.beutysalon.exeptions.NotFoundException;
import spdu2022.java.project.beutysalon.file_storage.FileStorageServices;
import spdu2022.java.project.beutysalon.staff_registration.persistence.repositories.StaffRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Staff file storage services")
class PersistenceStaffFileStorageServicesTest {
    @Mock
    private FileStorageServices fileStorageServices;
    @Mock
    private StaffRepository staffRepository;
    @InjectMocks
    private PersistenceStaffFileStorageServices staffFileStorageServices;

    @Test
    @DisplayName("Save avatar for staff by StaffId. Staff not exist -> throw NotFoundException.")
    void saveAvatarStaffNotExist() {
        int staffId = 100;
        when(staffRepository.findById(staffId)).thenReturn(new Staff());
        assertThrows(NotFoundException.class, () -> staffFileStorageServices.saveAvatarOfStaff(staffId, null));
    }

    @Test
    @DisplayName("Save avatar for staff by StaffId. Must return correct file name with it path")
    void saveAvatarOfStaff() throws IOException {
        int staffId = 1;
        when(staffRepository.findById(staffId)).thenReturn(getStaff(staffId));
        when(fileStorageServices.saveFile(anyString(), any(MultipartFile.class))).thenReturn(true);
        String fileName = staffFileStorageServices.saveAvatarOfStaff(staffId, getMultipartFile());
        assertEquals("staff/avatar/staff_id_1_avatar_avatar", fileName);
    }

    @Test
    @DisplayName("Save avatar for staff by StaffId. Method must throw exception if not be upload file to storage")
    void saveAvatarOfStaffException() {
        int staffId = 1;
        when(staffRepository.findById(staffId)).thenReturn(getStaff(staffId));
        when(fileStorageServices.saveFile(anyString(), any(MultipartFile.class))).thenReturn(false);
        assertThrows(FileStorageException.class, () -> staffFileStorageServices.saveAvatarOfStaff(staffId, getMultipartFile()));
    }



    @Test
    @DisplayName("Delete avatar by staffId. Staff not exist -> throw NotFoundException.")
    void deleteAvatarOfStaffNotExist() {
        int staffId = 100;
        when(staffRepository.findById(staffId)).thenReturn(new Staff());
        assertThrows(NotFoundException.class, () -> staffFileStorageServices.deleteAvatarOfStaff(staffId));
    }

    private Staff getStaff(int staffId) {
        Staff staff = new Staff();
        staff.setId(staffId);
        staff.setSalonId(1);
        staff.setUserId(2);
        staff.setLinkPhoto("");
        return staff;
    }

    private MultipartFile getMultipartFile() throws IOException {
        File file = new File("src/test/resources/file/avatar");
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file",
                file.getName(), "image/jpeg", IOUtils.toByteArray(input));
    }
}
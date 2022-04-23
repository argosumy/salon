package spdu2022.java.project.beutysalon.staff_registration.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.FileStorageException;
import spdu2022.java.project.beutysalon.file_storage.FileStorageServices;
import spdu2022.java.project.beutysalon.staff_registration.persistence.repositories.StaffRepository;

@Service
public class PersistenceStaffFileStorageServices implements StaffFileStorage{
    private final FileStorageServices fileStorageServices;
    private final StaffRepository staffRepository;

    public PersistenceStaffFileStorageServices(FileStorageServices fileStorageServices, StaffRepository staffRepository) {
        this.fileStorageServices = fileStorageServices;
        this.staffRepository = staffRepository;
    }

    @Override
    @Transactional
    public String saveAvatarOfStaff(long staffId, MultipartFile multipartFile) {
        final Staff staffDB = staffRepository.findById(staffId);
        final String newAvatarLink = saveFile(staffId, multipartFile);
        if(staffDB.getLinkPhoto() != null && !staffDB.getLinkPhoto().isEmpty()) {
            String fileName = deleteFile(staffDB.getLinkPhoto());
            System.out.println(fileName);
        }
        staffDB.setLinkPhoto(newAvatarLink);
        staffRepository.updateStaff(staffDB);
        return newAvatarLink;
    }

    @Override
    @Transactional
    public String deleteAvatarOfStaff(long staffId) {
        final Staff staffDB = staffRepository.findById(staffId);
        String fileNameDelete = "";
        if(staffDB.getLinkPhoto() != null && !staffDB.getLinkPhoto().isEmpty()) {
            fileNameDelete = deleteFile(staffDB.getLinkPhoto());
            System.out.println(fileNameDelete);
            staffDB.setLinkPhoto("");
            staffRepository.updateStaff(staffDB);
        }
        return fileNameDelete;
    }

    private String deleteFile(String fileName) {
        return fileStorageServices.deleteFile(fileName);
    }

    private String saveFile(long staffId, MultipartFile file) throws FileStorageException {
        String fileName = createFilePath(staffId, file.getOriginalFilename());
        if(!fileStorageServices.saveFile(fileName, file)) {
            throw new FileStorageException("File dont save");
        }
        return  fileName;
    }

    private String createFilePath(long staffId, String originalFileName) {
        final String FOLDER = "staff/";
        final String STAFF_AVATAR_FILE_NAME_TEMPLATE = "%sstaff_id_%d_avatar_%s";
        return String.format(STAFF_AVATAR_FILE_NAME_TEMPLATE, FOLDER, staffId, originalFileName);
    }
}

package spdu2022.java.project.beutysalon.staff_registration.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.FileStorageException;
import spdu2022.java.project.beutysalon.exeptions.NotFoundException;
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
    public String saveAvatarOfStaff(long staffId, MultipartFile multipartFile) {
        final Staff staffDB = staffRepository.findById(staffId);
        if(staffDB.getUserId() == 0) {
            throw new NotFoundException(String.format("staff by id = %d not exist", staffId));
        }

        final String newAvatarLink = saveFile(staffId, multipartFile);

        if(staffDB.getLinkPhoto() != null && !staffDB.getLinkPhoto().isEmpty()) {
            fileStorageServices.deleteFile(staffDB.getLinkPhoto());
        }
        staffDB.setLinkPhoto(newAvatarLink);
        staffRepository.updateStaff(staffDB);

        return newAvatarLink;
    }

    @Override
    public void deleteAvatarOfStaff(long staffId) {
        final Staff staffDB = staffRepository.findById(staffId);
        if(staffDB.getId() == 0) {
            throw new NotFoundException(String.format("Staff by id = %s not exist or link photo not exist", staffId));
        }
        if(staffDB.getLinkPhoto() != null && !staffDB.getLinkPhoto().isEmpty()) {
            fileStorageServices.deleteFile(staffDB.getLinkPhoto());
            staffDB.setLinkPhoto("");
            staffRepository.updateStaff(staffDB);
        }
    }

    private String saveFile(long staffId, MultipartFile file) throws FileStorageException {
        String fileName = createFilePath(staffId, file.getOriginalFilename());
        if(!fileStorageServices.saveFile(fileName, file)) {
            throw new FileStorageException("File dont save");
        }
        return  fileName;
    }

    private String createFilePath(long staffId, String originalFileName) {
        final String FOLDER = "staff/avatar/";
        final String STAFF_AVATAR_FILE_NAME_TEMPLATE = "%sstaff_id_%d_avatar_%s";
        return String.format(STAFF_AVATAR_FILE_NAME_TEMPLATE, FOLDER, staffId, originalFileName);
    }
}

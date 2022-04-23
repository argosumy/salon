package spdu2022.java.project.beutysalon.staff_registration.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.staff_registration.persistence.repositories.StaffRepository;

@Service
public class PersistenceStaffModificationService implements StaffModificationService {
    private final StaffRepository staffRepository;

    public PersistenceStaffModificationService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff insertNewStaff(Staff staff) {
        int countStaff = staffRepository.getCountStaffByUserId(staff);
        if(countStaff > 0) {
            throw new EntityNotUniqException("Staff with User_ID " + staff.getUserId() + " already exist");
        }
        return staffRepository.insertNewStaff(staff);
    }

    @Override
    public boolean deleteStaffById(long id) {
        return false;
    }

    //without update avatar of staff
    @Override
    public Staff updateStaff(Staff staffUpdate) {
        final Staff staffDb = staffRepository.findById(staffUpdate.getId());
        staffUpdate.setLinkPhoto(staffDb.getLinkPhoto());
        staffRepository.updateStaff(staffUpdate);
        return staffUpdate;
    }

//    @Override
//    @Transactional
//    public String saveAvatarOfStaff(long staffId, MultipartFile multipartFile) throws Exception {
//        final Staff staffDB = staffRepository.findById(staffId);
//        final String newAvatarLink = fileStorageServices.saveFile(staffId, multipartFile);
//        if(staffDB.getLinkPhoto() != null && !staffDB.getLinkPhoto().isEmpty()) {
//            String fileName = fileStorageServices.deleteFile(staffDB.getLinkPhoto());
//            System.out.println(fileName);
//        }
//        staffDB.setLinkPhoto(newAvatarLink);
//        staffRepository.updateStaff(staffDB);
//        return newAvatarLink;
//    }
//
//    @Override
//    @Transactional
//    public String deleteAvatarOfStaff(long staffId) {
//        final Staff staffDB = staffRepository.findById(staffId);
//        String fileNameDelete = "";
//        if(staffDB.getLinkPhoto() != null && !staffDB.getLinkPhoto().isEmpty()) {
//            fileNameDelete = fileStorageServices.deleteFile(staffDB.getLinkPhoto());
//            System.out.println(fileNameDelete);
//            staffDB.setLinkPhoto("");
//            staffRepository.updateStaff(staffDB);
//        }
//        return fileNameDelete;
//    }
}

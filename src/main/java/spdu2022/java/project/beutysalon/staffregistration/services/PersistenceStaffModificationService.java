package spdu2022.java.project.beutysalon.staffregistration.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.staffregistration.persistence.repositories.StaffRepository;

@Service
public class PersistenceStaffModificationService implements StaffModificationService {
    private final StaffRepository staffRepository;

    public PersistenceStaffModificationService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff insertNewStaff(Staff staff) {
        int countStaff = staffRepository.getCountStaffByUserId(staff);
        if (countStaff > 0) {
            throw new EntityNotUniqException("Staff with User_ID " + staff.getUserId() + " already exist");
        }
        return staffRepository.insertNewStaff(staff);
    }
}
package spdu2022.java.project.beutysalon.staff_registration.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.Staff;
import spdu2022.java.project.beutysalon.staff_registration.persistence.repositories.PersistenceStaffRepository;

import java.sql.SQLException;

@Service
public class PersistenceStaffModificationService implements StaffModificationService{
    private final PersistenceStaffRepository persistenceStaffRepository;

    public PersistenceStaffModificationService(PersistenceStaffRepository persistenceStaffRepository) {
        this.persistenceStaffRepository = persistenceStaffRepository;
    }

    @Override
    public Staff insertNewStaff(Staff staff) throws SQLException {
        return persistenceStaffRepository.insertNewStaff(staff);
    }

    @Override
    public boolean deleteStaffById(long id) {
        return false;
    }

    @Override
    public Staff updateStaff(Staff staffUpdate) {
        return null;
    }
}

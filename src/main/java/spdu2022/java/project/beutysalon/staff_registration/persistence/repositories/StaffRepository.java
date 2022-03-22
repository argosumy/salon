package spdu2022.java.project.beutysalon.staff_registration.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffRepository {
    Optional<Staff> findById(long id);
    List<Staff> getAllSalonsFromCity(String city);
    Staff insertNewStaff(Staff newStaff);
    boolean deleteStaffById(long id);
    Staff updateStaff(Staff staffUpdate);
    int getCountStaffByUserId(Staff staff);
}

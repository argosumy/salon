package spdu2022.java.project.beutysalon.staff_registration.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.Staff;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StaffRepository {
    Optional<Staff> findById(long id) throws SQLException;
    List<Staff> getAllSalonsFromCity(String city);
    Staff insertNewStaff(Staff newStaff) throws SQLException;
    boolean deleteStaffById(long id);
    Staff updateStaff(Staff staffUpdate);
}

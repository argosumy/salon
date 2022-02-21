package spdu2022.java.project.beutysalon.staff_registration.services;

import spdu2022.java.project.beutysalon.entities.Staff;

import java.sql.SQLException;

public interface StaffModificationService {
    Staff insertNewStaff(Staff staff) throws SQLException;
    boolean deleteStaffById(long id);
    Staff updateStaff(Staff staffUpdate);
}

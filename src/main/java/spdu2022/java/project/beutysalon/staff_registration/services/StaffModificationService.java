package spdu2022.java.project.beutysalon.staff_registration.services;

import spdu2022.java.project.beutysalon.entities.Staff;

public interface StaffModificationService {
    Staff insertNewStaff(Staff staff);
    boolean deleteStaffById(long id);
    Staff updateStaff(Staff staffUpdate);
}

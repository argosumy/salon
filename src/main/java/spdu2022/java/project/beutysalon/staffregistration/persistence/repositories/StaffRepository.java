package spdu2022.java.project.beutysalon.staffregistration.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.Staff;

import java.util.List;

public interface StaffRepository {
    Staff findById(long id);

    List<Staff> getAllSalonsFromCity(String city);

    Staff insertNewStaff(Staff newStaff);

    boolean deleteStaffById(long id);

    void updateStaff(Staff staff);

    int getCountStaffByUserId(Staff staff);
}
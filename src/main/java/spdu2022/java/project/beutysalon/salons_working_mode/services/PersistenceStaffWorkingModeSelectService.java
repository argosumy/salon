package spdu2022.java.project.beutysalon.salons_working_mode.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.Staff;

import java.time.LocalDate;

@Service
public class PersistenceStaffWorkingModeSelectService implements StaffWorkingModeSelectService{
    @Override
    public Staff findStaffBySalonIdAndDay(long salonId, LocalDate date) {
        return null;
    }
}

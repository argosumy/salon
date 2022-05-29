package spdu2022.java.project.beutysalon.salonsworkingmode.services;

import spdu2022.java.project.beutysalon.entities.Staff;

import java.time.LocalDate;

public interface StaffWorkingModeSelectService {
    Staff findStaffBySalonIdAndDay(long salonId, LocalDate date);
}
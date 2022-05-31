package spdu2022.java.project.beutysalon.salonsworkingmode.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDay;

public interface SalonWorkingDayModeRepository {
    SalonWorkingMode findPeriodBySalonId(long salonId);

    long addNewWorkingPeriodBySalonId(long salonId, WorkingDay workingDay);

    long updateWorkingPeriodBySalonId(long salonId, WorkingDay workingDay);
}
package spdu2022.java.project.beutysalon.salonsworkingmode.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;

public interface SalonWorkingDayOfWeekRepository {
    SalonWorkingMode findPeriodBySalonId(long salonId);

    long addNewWorkingPeriodBySalonId(long salonId, WorkingDayOfWeek workingDayOfWeek);

    long updateWorkingPeriodBySalonId(long salonId, WorkingDayOfWeek workingDayOfWeek);
}
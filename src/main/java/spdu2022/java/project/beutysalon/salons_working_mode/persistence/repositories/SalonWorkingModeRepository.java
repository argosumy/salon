package spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;

public interface SalonWorkingModeRepository {
    SalonWorkingMode findPeriodBySalonId(long salonId);
    long addNewWorkingPeriodBySalonId(long salonId, WorkingPeriod period);
    long updateWorkingPeriodBySalonId(long salonId, WorkingPeriod period);


}

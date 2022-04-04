package spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories;

import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingMode;

public interface SalonWorkingModeRepository {
    SalonWorkingMode findPeriodBySalonId(long salonId);
    long addNewWorkingPeriodBySalonId(long salonId, WorkingMode workingMode);
    long updateWorkingPeriodBySalonId(long salonId, WorkingMode workingMode);


}

package spdu2022.java.project.beutysalon.salons_working_mode.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.entities.WorkingDayPeriod;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories.SalonWorkingModeRepository;

@Service
public class PersistenceSalonWorkingModeModificationService implements SalonsWorkingModeModificationService {
    private final SalonWorkingModeRepository repository;

    public PersistenceSalonWorkingModeModificationService(SalonWorkingModeRepository repository) {
        this.repository = repository;
    }

    @Override
    public int addNewWorkingPeriod(SalonWorkingMode salonWorkingMode) {
        if(!salonWorkingMode.getSalonWorkingPeriods().isEmpty()
                && salonWorkingMode.getSalonWorkingPeriods().get(0) instanceof WorkingDayOfWeek) {
            return addNewDaysOfWeekPeriod(salonWorkingMode);
        }
        if(!salonWorkingMode.getSalonWorkingPeriods().isEmpty()
                && salonWorkingMode.getSalonWorkingPeriods().get(0) instanceof WorkingDayPeriod) {
            return addNewDaysPeriod(salonWorkingMode);
        }
        return 0;
    }

    private int addNewDaysOfWeekPeriod(SalonWorkingMode salonWorkingMode) {
        return 1;
    }

    private int addNewDaysPeriod(SalonWorkingMode salonWorkingMode) {
        return 10;
    }
}

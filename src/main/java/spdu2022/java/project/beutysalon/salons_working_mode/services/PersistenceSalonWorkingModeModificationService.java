package spdu2022.java.project.beutysalon.salons_working_mode.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeekPeriod;
import spdu2022.java.project.beutysalon.entities.WorkingDayPeriod;
import spdu2022.java.project.beutysalon.entities.WorkingPeriod;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories.SalonWorkingModeRepository;

import java.util.List;

@Service
public class PersistenceSalonWorkingModeModificationService implements SalonsWorkingModeModificationService {
    private final SalonWorkingModeRepository repositoryWeekPeriod;
    private final SalonWorkingModeRepository repositoryDaysPeriod;

    public PersistenceSalonWorkingModeModificationService(@Qualifier(value = "weekPeriod") SalonWorkingModeRepository repositoryWeekPeriod, @Qualifier(value = "daysPeriod") SalonWorkingModeRepository repositoryDaysPeriod) {
        this.repositoryWeekPeriod = repositoryWeekPeriod;
        this.repositoryDaysPeriod = repositoryDaysPeriod;
    }

    @Override
    public int addNewWorkingPeriod(SalonWorkingMode salonWorkingMode) {
        if(!salonWorkingMode.getSalonWorkingPeriods().isEmpty()
                && salonWorkingMode.getSalonWorkingPeriods().get(0) instanceof WorkingDayOfWeekPeriod) {
            return addNewPeriod(salonWorkingMode, repositoryWeekPeriod);
        }
        if(!salonWorkingMode.getSalonWorkingPeriods().isEmpty()
                && salonWorkingMode.getSalonWorkingPeriods().get(0) instanceof WorkingDayPeriod) {
            return addNewPeriod(salonWorkingMode, repositoryDaysPeriod);
        }
        return 0;
    }

    private int addNewPeriod(SalonWorkingMode salonWorkingMode, SalonWorkingModeRepository repository) {
        long salonId = salonWorkingMode.getSalonId();
        List<WorkingPeriod> weekPeriodList = salonWorkingMode.getSalonWorkingPeriods();
        List<WorkingPeriod> periodsInDb = repository.findPeriodBySalonId(salonId).getSalonWorkingPeriods();
        for(WorkingPeriod workingPeriod : weekPeriodList) {
            WorkingPeriod duplicate = getDuplicate(workingPeriod, periodsInDb);
            if(duplicate == null) {
                repository.addNewWorkingPeriodBySalonId(salonId, workingPeriod);
            } else {
                repository.updateWorkingPeriodBySalonId(salonId, workingPeriod);
            }
        }
        return 1;
    }

    private WorkingPeriod getDuplicate(WorkingPeriod period, List<WorkingPeriod> periodFromDbList) {
        for (WorkingPeriod periodDB : periodFromDbList) {
            if(period.equals(periodDB)) {
                return periodDB;
            }
        }
        return null;
    }
}

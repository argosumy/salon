package spdu2022.java.project.beutysalon.salons_working_mode.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.*;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
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
        if(!salonWorkingMode.getSalonWorkingMode().isEmpty()
                && salonWorkingMode.getSalonWorkingMode().get(0) instanceof WorkingDayOfWeek) {
            return addNewPeriod(salonWorkingMode, repositoryWeekPeriod);
        }
        if(!salonWorkingMode.getSalonWorkingMode().isEmpty()
                && salonWorkingMode.getSalonWorkingMode().get(0) instanceof WorkingDay) {
            return addNewPeriod(salonWorkingMode, repositoryDaysPeriod);
        }
        return 0;
    }

    private int addNewPeriod(SalonWorkingMode salonWorkingMode, SalonWorkingModeRepository repository) {
        int count = 0;
        long salonId = salonWorkingMode.getSalonId();
        List<WorkingMode> weekPeriodList = salonWorkingMode.getSalonWorkingMode();
        List<WorkingMode> periodsInDb = repository.findPeriodBySalonId(salonId).getSalonWorkingMode();
        for(WorkingMode workingPeriod : weekPeriodList) {
            WorkingMode duplicate = getDuplicate(workingPeriod, periodsInDb);
            if(duplicate == null) {
                count += repository.addNewWorkingPeriodBySalonId(salonId, workingPeriod);

            } else {
//                count += repository.updateWorkingPeriodBySalonId(salonId, workingPeriod);
                throw new EntityNotUniqException("Period " + workingPeriod + " is not uniq.");
            }
        }
        return count;
    }

    private WorkingMode getDuplicate(WorkingMode period, List<WorkingMode> periodFromDbList) {
        for (WorkingMode periodDB : periodFromDbList) {
            if(period.equals(periodDB)) {
                return periodDB;
            }
        }
        return null;
    }
}

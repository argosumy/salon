package spdu2022.java.project.beutysalon.salons_working_mode.services;

import org.springframework.stereotype.Service;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDay;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories.SalonWorkingDayModeRepository;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories.SalonWorkingDayOfWeekRepository;

import java.util.Set;

@Service
public class PersistenceSalonWorkingModeModificationService implements SalonsWorkingModeModificationService {
    private final SalonWorkingDayOfWeekRepository repositoryWeekPeriod;
    private final SalonWorkingDayModeRepository repositoryDaysPeriod;

    public PersistenceSalonWorkingModeModificationService(SalonWorkingDayOfWeekRepository repositoryWeekPeriod,
                                                          SalonWorkingDayModeRepository repositoryDaysPeriod) {
        this.repositoryWeekPeriod = repositoryWeekPeriod;
        this.repositoryDaysPeriod = repositoryDaysPeriod;
    }

    @Override
    public int addNewWorkingPeriod(SalonWorkingMode salonWorkingMode) {
        int count = 0;
        count += addNewUniqPeriod(salonWorkingMode);
        count += addNewDayOfWeekPeriod(salonWorkingMode);
        return count;
    }

    private int addNewUniqPeriod(SalonWorkingMode salonWorkingMode) {
        int count = 0;
        long salonId = salonWorkingMode.getSalonId();
        Set<WorkingDay> workingDays = salonWorkingMode.getSalonWorkingModeUniq();
        Set<WorkingDay> workingDaysDB = repositoryDaysPeriod.findPeriodBySalonId(salonId).getSalonWorkingModeUniq();
        for(WorkingDay workingDay : workingDays) {
            if(workingDaysDB.contains(workingDay)) {
                count += repositoryDaysPeriod.updateWorkingPeriodBySalonId(salonId, workingDay);
            } else {
                count += repositoryDaysPeriod.addNewWorkingPeriodBySalonId(salonId, workingDay);
            }
        }
        return count;
    }

    private int addNewDayOfWeekPeriod(SalonWorkingMode salonWorkingMode) {
        int count = 0;
        long salonId = salonWorkingMode.getSalonId();
        Set<WorkingDayOfWeek> weekDays = salonWorkingMode.getSalonsWorkingDaysOfWeek();
        Set<WorkingDayOfWeek> weekDaysDB = repositoryWeekPeriod.findPeriodBySalonId(salonId).getSalonsWorkingDaysOfWeek();
        for(WorkingDayOfWeek dayOfWeek : weekDays) {
            if(weekDaysDB.contains(dayOfWeek)) {
                count += repositoryWeekPeriod.updateWorkingPeriodBySalonId(salonId, dayOfWeek);
            } else {
                count += repositoryWeekPeriod.addNewWorkingPeriodBySalonId(salonId, dayOfWeek);
            }
        }
        return count;
    }
}

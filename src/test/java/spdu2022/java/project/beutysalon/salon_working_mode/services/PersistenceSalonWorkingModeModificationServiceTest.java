package spdu2022.java.project.beutysalon.salon_working_mode.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spdu2022.java.project.beutysalon.entities.DaysOfWeek;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.salons_working_mode.services.SalonsWorkingModeModificationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PersistenceSalonWorkingModeModificationServiceTest {
//    @Mock
//    private SalonWorkingModeRepository repository;
//
//    @InjectMocks
    @Autowired
    private SalonsWorkingModeModificationService serviceModification;

    @Test
    void addNewWorkingPeriod() {
        SalonWorkingMode salonWorkingMode = buildWorkingDaysOfWeekMode();
        int count = serviceModification.addNewWorkingPeriod(salonWorkingMode);
        assertEquals(7, count,"");
        assertThrows(EntityNotUniqException.class, () -> serviceModification.addNewWorkingPeriod(salonWorkingMode), "Entity salonWorkingMode mast be is uniq for DB");
    }

    private SalonWorkingMode buildWorkingDaysMode() {

        return null;
    }

    private SalonWorkingMode buildWorkingDaysOfWeekMode() {
        SalonWorkingMode salonWorkingMode = new SalonWorkingMode();
        salonWorkingMode.setSalonId(2);
        for(DaysOfWeek dayOfWeek : DaysOfWeek.values()) {
            WorkingDayOfWeek dayOfWeekPeriod = new WorkingDayOfWeek();
            dayOfWeekPeriod.setDaysOfWeek(dayOfWeek);
            dayOfWeekPeriod.setStartWorking("09:00");
            dayOfWeekPeriod.setEndWorking("18:00");
            salonWorkingMode.getSalonWorkingPeriods().add(dayOfWeekPeriod);
        }
        return salonWorkingMode;
    }
}
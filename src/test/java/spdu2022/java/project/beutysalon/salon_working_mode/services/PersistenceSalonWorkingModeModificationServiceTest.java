package spdu2022.java.project.beutysalon.salon_working_mode.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeekPeriod;
import spdu2022.java.project.beutysalon.exeptions.EntityNotUniqException;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories.SalonWorkingModeRepository;
import spdu2022.java.project.beutysalon.salons_working_mode.services.PersistenceSalonWorkingModeModificationService;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersistenceSalonWorkingModeModificationServiceTest {
    @Mock
    private SalonWorkingModeRepository repository;

    @InjectMocks
    private PersistenceSalonWorkingModeModificationService serviceModification;

    @Test
    void addNewWorkingPeriodForWeek() {
        when(repository.findPeriodBySalonId(2)).thenReturn(buildWorkingDaysOfWeekMode());
        SalonWorkingMode salonWorkingMode = buildWorkingDaysOfWeekMode();
        assertThrows(EntityNotUniqException.class, () -> serviceModification.addNewWorkingPeriod(salonWorkingMode), "Entity salonWorkingMode mast be is uniq for DB");

        when(repository.findPeriodBySalonId(2)).thenReturn(new SalonWorkingMode());
        when(repository.addNewWorkingPeriodBySalonId(2, buildWorkingDaysOfWeekMode().getSalonWorkingPeriods().get(0))).thenReturn(1L);
        long count = serviceModification.addNewWorkingPeriod(buildWorkingDaysOfWeekMode());
        assertEquals(1, count);
    }

    private SalonWorkingMode buildWorkingDaysOfWeekMode() {
        SalonWorkingMode salonWorkingMode = new SalonWorkingMode();
        salonWorkingMode.setSalonId(2);
        WorkingDayOfWeekPeriod dayOfWeekPeriod = new WorkingDayOfWeekPeriod();
        dayOfWeekPeriod.setDayOfWeek(DayOfWeek.MONDAY);
        dayOfWeekPeriod.setStartWorking(LocalTime.parse("09:00"));
        dayOfWeekPeriod.setEndWorking(LocalTime.parse("18:00"));
        salonWorkingMode.getSalonWorkingPeriods().add(dayOfWeekPeriod);
        return salonWorkingMode;
    }
}
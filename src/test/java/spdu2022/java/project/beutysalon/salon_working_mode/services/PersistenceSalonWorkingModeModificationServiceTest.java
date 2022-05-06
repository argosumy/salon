package spdu2022.java.project.beutysalon.salon_working_mode.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories.SalonWorkingDayModeRepository;
import spdu2022.java.project.beutysalon.salons_working_mode.persistence.repositories.SalonWorkingDayOfWeekRepository;
import spdu2022.java.project.beutysalon.salons_working_mode.services.PersistenceSalonWorkingModeModificationService;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersistenceSalonWorkingModeModificationServiceTest {
    @Mock
    private SalonWorkingDayOfWeekRepository weekRepository;
    @Mock
    private SalonWorkingDayModeRepository daysRepository;

    @InjectMocks
    private PersistenceSalonWorkingModeModificationService serviceModification;

    @Test
    void addNewWorkingPeriodForWeek() {
        SalonWorkingMode salonWorkingMode = buildWorkingDaysOfWeekMode();
        when(daysRepository.findPeriodBySalonId(2)).thenReturn(new SalonWorkingMode());
        when(weekRepository.findPeriodBySalonId(2)).thenReturn(new SalonWorkingMode());
        when(weekRepository.addNewWorkingPeriodBySalonId(salonWorkingMode.getSalonId(), new ArrayList<>(salonWorkingMode.getSalonsWorkingDaysOfWeek()).get(0)))
                .thenReturn(1L);
        long count = serviceModification.addNewWorkingPeriod(buildWorkingDaysOfWeekMode());
        assertEquals(1, count);
    }

    private SalonWorkingMode buildWorkingDaysOfWeekMode() {
        SalonWorkingMode salonWorkingMode = new SalonWorkingMode();
        salonWorkingMode.setSalonId(2);
        WorkingDayOfWeek dayOfWeekPeriod = new WorkingDayOfWeek(DayOfWeek.MONDAY);
        dayOfWeekPeriod.addWorkingTimePeriod(LocalTime.parse("09:00"), LocalTime.parse("18:00"));
        salonWorkingMode.addWorkingDayOfWeek(dayOfWeekPeriod);
        return salonWorkingMode;
    }
}
package spdu2022.java.project.beutysalon.salonsworkingmode.persistence.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.testcontainers.junit.jupiter.Testcontainers;
import spdu2022.java.project.beutysalon.entities.SalonWorkingMode;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.entities.WorkingTimePeriod;
import spdu2022.java.project.beutysalon.filestorage.FileStorageServices;
import spdu2022.java.project.beutysalon.notification.controllers.NotificationController;
import spdu2022.java.project.beutysalon.notification.services.UsersNotificationService;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers")
class PersistenceSalonsWorkingModeForWeekRepositoryTest {
    @Autowired
    private PersistenceSalonsWorkingModeForWeekRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @MockBean
    private FileStorageServices fileStorageServices;
    @MockBean
    private NotificationController notificationController;
    @MockBean
    private UsersNotificationService usersNotificationService;
    @MockBean
    private JavaMailSender emailSender;
    @MockBean
    private FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean;

    @Test
    @DisplayName("Method must return working mode for salon by day of week.")
    void findPeriodBySalonId() {
        final SalonWorkingMode salonWorkingMode = repository.findPeriodBySalonId(1);
        final Set<WorkingDayOfWeek> daysOfWeek = salonWorkingMode.getSalonsWorkingDaysOfWeek();
        assertEquals(7, daysOfWeek.size());
    }

    @Test
    @DisplayName("Method must add new working mode. Working mode for day of week")
    void addNewWorkingPeriodBySalonId() {
        int salonId = 3;
        SalonWorkingMode salonWorkingMode = repository.findPeriodBySalonId(salonId);
        List<WorkingDayOfWeek> daysOfWeek = List.copyOf(salonWorkingMode.getSalonsWorkingDaysOfWeek());
        int sizeBefore = daysOfWeek.size();
        assertEquals(sizeBefore, daysOfWeek.size());
        repository.addNewWorkingPeriodBySalonId(salonId, getWorkingDayOfWeek(DayOfWeek.FRIDAY));
        salonWorkingMode = repository.findPeriodBySalonId(salonId);
        daysOfWeek = List.copyOf(salonWorkingMode.getSalonsWorkingDaysOfWeek());
        assertEquals(++sizeBefore, daysOfWeek.size());
    }

    @Test
    @DisplayName("Method must update working mode. Working mode for day of week")
    void updateWorkingPeriodBySalonId() {
        int salonId = 1;
        final SalonWorkingMode salonWorkingMode = repository.findPeriodBySalonId(salonId);
        final List<WorkingDayOfWeek> daysOfWeek = List.copyOf(salonWorkingMode.getSalonsWorkingDaysOfWeek());
        final WorkingDayOfWeek workingDayOfWeek = daysOfWeek.get(0);
        final DayOfWeek dayOfWeek = workingDayOfWeek.getDayOfWeek();
        final WorkingTimePeriod workingTimePeriodBefore = workingDayOfWeek.getWorkingTimePeriod();

        repository.updateWorkingPeriodBySalonId(salonId, getUpdateWorkingDayOfWeek(workingDayOfWeek));

        final SalonWorkingMode salonWorkingModeAfterUpdate = repository.findPeriodBySalonId(salonId);
        final WorkingDayOfWeek workingDayOfWeekAfterUpdate = salonWorkingModeAfterUpdate.getSalonsWorkingDaysOfWeek()
                .stream()
                .filter(x -> x.getDayOfWeek().equals(dayOfWeek))
                .findFirst()
                .orElse(new WorkingDayOfWeek());

        assertEquals(workingTimePeriodBefore.getStartWorking().minusHours(1), workingDayOfWeekAfterUpdate.getWorkingTimePeriod().getStartWorking());
        assertEquals(workingTimePeriodBefore.getEndWorking().plusHours(1), workingDayOfWeekAfterUpdate.getWorkingTimePeriod().getEndWorking());
    }

    private WorkingDayOfWeek getWorkingDayOfWeek(DayOfWeek dayOfWeek) {
        WorkingDayOfWeek workingDayOfWeek = new WorkingDayOfWeek(dayOfWeek);
        workingDayOfWeek.addWorkingTimePeriod(LocalTime.of(11, 0), LocalTime.of(15, 0));
        return workingDayOfWeek;
    }

    private WorkingDayOfWeek getUpdateWorkingDayOfWeek(WorkingDayOfWeek workingDayOfWeek) {
        WorkingDayOfWeek workingDayOfWeekUpdate = new WorkingDayOfWeek(workingDayOfWeek.getDayOfWeek());
        WorkingTimePeriod workingTimePeriod = workingDayOfWeek.getWorkingTimePeriod();
        workingDayOfWeekUpdate.addWorkingTimePeriod(workingTimePeriod.getStartWorking().minusHours(1), workingTimePeriod.getEndWorking().plusHours(1));
        return workingDayOfWeekUpdate;
    }
}
package spdu2022.java.project.beutysalon.log_book_services.persistence.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import spdu2022.java.project.beutysalon.entities.BookedService;
import spdu2022.java.project.beutysalon.entities.Salon;
import spdu2022.java.project.beutysalon.entities.WorkingDay;
import spdu2022.java.project.beutysalon.entities.WorkingDayOfWeek;
import spdu2022.java.project.beutysalon.file_storage.FileStorageServices;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers")
class PersistenceLogBookRepositoryTest {
    @Autowired
    private PersistenceLogBookRepository repository;

    @MockBean
    private FileStorageServices fileStorageServices;

    @Test
    @DisplayName("Method must return List of booking services for the city by date period.")
    void getLogServiceByCityAndPeriod() {
        List<BookedService> res = repository.getLogServiceByCityAndPeriod("Sumy", LocalDate.parse("2023-05-01"), LocalDate.parse("2023-05-01"));
        assertEquals(2, res.size());
        assertTrue(res.stream().anyMatch(x -> x.getSalonId() == 1L));

        res = repository.getLogServiceByCityAndPeriod("Kiev", LocalDate.parse("2023-05-01"), LocalDate.parse("2023-05-01"));
        assertEquals(0, res.size());
    }

    @Test
    @DisplayName("Method must return List of booking services for the salonId by date period.")
    void getLogServiceBySalonIdAndPeriod() {
        List<BookedService> res = repository.getLogServiceBySalonIdAndPeriod(1, LocalDate.parse("2023-05-01"), LocalDate.parse("2023-05-02"));
        assertEquals(2, res.size());

        res = repository.getLogServiceBySalonIdAndPeriod(2, LocalDate.parse("2023-05-01"), LocalDate.parse("2023-05-01"));
        assertEquals(0, res.size());
    }

    @Test
    @DisplayName("Method must return Map. Key is a staffId. Value is a unique working mode for the salonId by date. " +
            "Map size must be less than count of salon's staff")
    void getUniqWorkingMode() {
        Map<Long, WorkingDay> res = repository.getUniqWorkingMode(1, LocalDate.parse("2023-05-01"));
        assertTrue(res.containsKey(1L));
        assertTrue(res.containsKey(2L));

        res = repository.getUniqWorkingMode(2, LocalDate.parse("2023-05-01"));
        assertEquals(0, res.size());
    }

    @Test
    @DisplayName("Method must return working mode for salon by day of week")
    void findWorkingWeekPeriodBySalonIdAndDayOfWeek() {
        WorkingDayOfWeek res = repository.findWorkingWeekPeriodBySalonIdAndDayOfWeek(1, DayOfWeek.FRIDAY.name());
        assertEquals(DayOfWeek.FRIDAY, res.getDayOfWeek());
        res = repository.findWorkingWeekPeriodBySalonIdAndDayOfWeek(3, DayOfWeek.FRIDAY.name());
        assertNull(res);
    }

    @Test
    @DisplayName("Method must return working time for salon by date.")
    void findWorkingDayPeriodBySalonIdAndDate() {
        WorkingDay workingDay = repository.findWorkingDayPeriodBySalonIdAndDate(1, LocalDate.parse("2023-05-01"));
        LocalDate workingDate = workingDay.getWorkingDay();
        LocalTime startTime = workingDay.getWorkingTimePeriod().getStartWorking();
        LocalTime endTime = workingDay.getWorkingTimePeriod().getEndWorking();
        assertEquals(LocalDate.parse("2023-05-01"), workingDate);
        assertEquals(LocalTime.parse("09:00"), startTime);
        assertEquals(LocalTime.parse("15:00"), endTime);
    }

    @Test
    @DisplayName("Method must return salon list by City")
    void getSalonsByCity() {
        List<Salon> result = repository.getSalonsByCity("Sumy");
        assertEquals(2, result.size());
        result = repository.getSalonsByCity("Kiev");
        assertEquals(1, result.size());
    }
}